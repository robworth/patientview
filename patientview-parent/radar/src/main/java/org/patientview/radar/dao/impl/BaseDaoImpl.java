package org.patientview.radar.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static <T> T getEnumValue(Class<T> enumClass, Integer id) {
        try {
            // Assume we've been supplied an enum with an ID field, so get the accessor method
            Method getId = enumClass.getMethod("getId");
            // All enums have a static values method to get an array of the possible values
            Method method = enumClass.getMethod("values");

            // Use the static method to get an array of all the possible values
            T[] values = (T[]) method.invoke(null);
            for (T t : values) {
                // Get the ID field value
                Integer thisId = (Integer) getId.invoke(t);
                if (thisId.equals(id)) {
                    return t;
                }
            }

            // If we looped all the ID's and didn't find a value then return null
            LOGGER.debug("Could not find matching value for enum {} with ID {}", enumClass, id);
            return null;

        } catch (Exception e) {
            // This is pretty bad so lets throw a runtime exception
            LOGGER.error("Could not get values for enum {}", enumClass);
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * GetXWithNullCheck methods added because ResultSet.getInt or ResultSet.getDouble will return
     * 0 if null in db but we want it to return null
     */

    public Boolean getBooleanWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        boolean value = resultSet.getBoolean(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    public Double getDoubleWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        double value = resultSet.getDouble(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    public Integer getIntegerWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        int value = resultSet.getInt(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    public Long getLongWithNullCheck(String column, ResultSet resultSet) throws SQLException {
        long value = resultSet.getLong(column);
        if (resultSet.wasNull()) {
            return null;
        }
        return value;
    }

    /**
     * Build an update query for a database record
     * @param tableName table to update
     * @param idFieldName the field in the table which is used as the identifier for the record to update
     * @param paramMap List of values to update on the table and the field they should update
     * @return String
     */
    public String buildUpdateQuery(String tableName, String idFieldName, Map<String, Object> paramMap) {
        String updateSql = "UPDATE " + tableName + " SET ";

        // get the id value from the map
        Object idValue = paramMap.get(idFieldName);

        // remove the id temp so we can loop through it as we dont want that in the update part
        paramMap.remove(idFieldName);

        // loop through all params and add them in
        int count = 1;
        for (String field : paramMap.keySet()) {
            updateSql += " " + field + " = :" + field;

            if (count < paramMap.size()) {
                updateSql += ", ";
            }

            count++;
        }

        // add the where clause with the id
        updateSql += " WHERE " + idFieldName + " = :" + idFieldName;

        // add it back into map
        paramMap.put(idFieldName, idValue);

        return updateSql;
    }

    /**
     * Will build the order by statement on the specified field and what directon its to be returned in
     * @param sortField Database field name
     * @param reverse whether its ASC or DESC
     * @return String
     */
    public String buildOrderQuery(String sortField, boolean reverse) {
        return "ORDER BY " + sortField + " " + (reverse ? "ASC" : "DESC");       
    }

    /**
     * Will simple take two values to start the record set from and end it
     * @param page Start record
     * @param numberPerPage Number of records to return
     * @param paramList List of params for current query
     * @return String
     */
    public String buildLimitQuery(int page, int numberPerPage, List<Object> paramList) {
        if (page > 0 && numberPerPage > 0) {
            // work out the row to start from
            int start = ((page * numberPerPage) - numberPerPage);

            paramList.add(start);
            paramList.add(numberPerPage);

            return "LIMIT ?, ?";
        }

        return "";
    }

    public String buildWhereQuery(Map<String, String> searchMap, boolean and, List<Object> paramList) {
        return buildWhereQuery(true, searchMap, and, paramList);
    }

    /**
     * Will build a where query based on search values in a map
     * Key in the map is the database field name and the value is the text to search for
     * @param searchMap Map<String, String> fieldName, searchValue
     * @param and If the where clause should AND or OR the search values
     * @param paramList List of params for current query
     * @return String
     */
    public String buildWhereQuery(boolean includeWhere, Map<String, String> searchMap, boolean and,
                                  List<Object> paramList) {
        if (searchMap != null && !searchMap.isEmpty()) {
            List<String> searchQueries = new ArrayList<String>();

            // if there a search fields in the filter then create where clause

            // the start is optional as some statement may already have the where part and only require all the clauses
            if (includeWhere) {
                searchQueries.add("WHERE");
            }

            // parse the search map as one key can be multiple fields in a table
            searchMap = parseSearchMap(searchMap);
            
            int count = 1;
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                // converting the field values to uppercase so I dont have to faff around
                // probably bite me in the ass at some point
                searchQueries.add("UPPER(" + entry.getKey() + ") LIKE ?");
                paramList.add("%" + entry.getValue().toUpperCase() + "%");

                // if there are more than one field being search AND them
                if (count < searchMap.size()) {
                    searchQueries.add((and ? "AND" : "OR"));
                }

                count++;
            }

            return StringUtils.join(searchQueries.toArray(), " ");
        }

        return "";
    }

    /**
     * Will take a Map<String, String> and check to see if the key is a multi part value seperated by a , indicating
     * that the search value should be used across multiple database fields
     * If it is it will split and add in a key for each all using the same search value
     * @param searchMap Map<String, String>
     * @return Map<String, String>
     */
    private Map<String, String> parseSearchMap(Map<String, String> searchMap) {
        Map<String, String> newSearchMap = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : searchMap.entrySet()) {
            String searchValue = entry.getValue().trim();

            if (entry.getValue().length() > 0) {
                if (entry.getKey().indexOf(",") > -1) {
                    String[] fields = entry.getKey().split(",");

                    for (String s : fields) {
                        newSearchMap.put(s.trim(), searchValue);
                    }
                } else {
                    newSearchMap.put(entry.getKey().trim(), searchValue);
                }
            }
        }

        return newSearchMap;
    }
}
