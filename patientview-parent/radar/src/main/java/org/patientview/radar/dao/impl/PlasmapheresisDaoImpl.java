package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.PlasmapheresisDao;
import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.patientview.radar.model.enums.RemissionAchieved;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlasmapheresisDaoImpl extends BaseDaoImpl implements PlasmapheresisDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlasmapheresisDaoImpl.class);
    private SimpleJdbcInsert plasmapheresisInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        plasmapheresisInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_rrt_plasma")
                .usingGeneratedKeyColumns("plID").usingColumns("RADAR_NO", "PLASMAPH", "DATE_START_PLASMAPH",
                        "DATE_STOP_PLASMAPH", "NO_EXCH_PLASMAPH", "DUR_PLASMAPH", "RESPONSE_TO_PLASMA");
    }

    public void savePlasmapheresis(final Plasmapheresis plasmapheresis) {
        Map<String, Object> plasmapheresisMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", plasmapheresis.getRadarNumber());
                put("PLASMAPH", null);
                put("DATE_START_PLASMAPH", plasmapheresis.getStartDate());
                put("DATE_STOP_PLASMAPH", plasmapheresis.getEndDate());
                put("NO_EXCH_PLASMAPH", plasmapheresis.getPlasmapheresisExchanges() != null ?
                        plasmapheresis.getPlasmapheresisExchanges().getId() : null);
                put("DUR_PLASMAPH", null);
                put("RESPONSE_TO_PLASMA", plasmapheresis.getResponse() != null ? plasmapheresis.getResponse().getId() :
                        null);
            }
        };

        if (plasmapheresis.hasValidId()) {
            plasmapheresisMap.put("plID", plasmapheresis.getId());
            namedParameterJdbcTemplate.update("UPDATE tbl_rrt_plasma " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "PLASMAPH = :PLASMAPH, " +
                    "DATE_START_PLASMAPH = :DATE_START_PLASMAPH, " +
                    "DATE_STOP_PLASMAPH = :DATE_STOP_PLASMAPH, " +
                    "NO_EXCH_PLASMAPH = :NO_EXCH_PLASMAPH, " +
                    "DUR_PLASMAPH = :DUR_PLASMAPH, " +
                    "RESPONSE_TO_PLASMA = :RESPONSE_TO_PLASMA " +
                    " WHERE plID = :plID;", plasmapheresisMap);

        } else {
            Number id = plasmapheresisInsert.executeAndReturnKey(plasmapheresisMap);
            plasmapheresis.setId(id.longValue());
        }
    }

    public void deletePlasmaPheresis(Plasmapheresis plasmapheresis) {
        Map<String, Object> plasmapheresisMap = new HashMap<String, Object>();
        plasmapheresisMap.put("plID", plasmapheresis.getId());
        namedParameterJdbcTemplate.update("DELETE FROM tbl_rrt_plasma " +
                "WHERE plID = :plID;", plasmapheresisMap);
    }

    public Plasmapheresis getPlasmapheresis(long id) {

        // The old SQL statement was as follows

        // SELECT tbl_RRT_PLASMA.plID, tbl_RRT_PLASMA.DATE_START_PLASMAPH, tbl_RRT_PLASMA.DATE_STOP_PLASMAPH,
        // tbl_RRT_PLASMA.DUR_PLASMAPH, tbl_RRT_PLASMA.RESPONSE_TO_PLASMA, tbl_RRT_PLASMA_LU.exDesc
        // FROM tbl_RRT_PLASMA
        // INNER JOIN tbl_RRT_PLASMA_LU ON tbl_RRT_PLASMA.NO_EXCH_PLASMAPH = tbl_RRT_PLASMA_LU.exID
        // WHERE (tbl_RRT_PLASMA.RADAR_NO = @RADAR_NO) ORDER BY tbl_RRT_PLASMA.DATE_START_PLASMAPH DESC

        // Rather than joins lets do n+1 tho, slight performance hit means better testing, more reusable code
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_RRT_PLASMA WHERE plID = ?", new Object[]{id},
                    new PlasmapheresisRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not get result for tbl_rrt_plasmawith ID {}", id);
            return null;
        }
    }

    public List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_RRT_PLASMA WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new PlasmapheresisRowMapper());
    }

    public PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_RRT_PLASMA_LU WHERE exID = ?", new Object[]{id},
                new PlasmapheresisExchangeUnitRowMapper());
    }

    public List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits() {
        return jdbcTemplate.query("SELECT * FROM tbl_RRT_PLASMA_LU", new PlasmapheresisExchangeUnitRowMapper());
    }

    private class PlasmapheresisRowMapper implements RowMapper<Plasmapheresis> {
        public Plasmapheresis mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct the object and set the values
            Plasmapheresis plasmapheresis = new Plasmapheresis();
            plasmapheresis.setId(resultSet.getLong("plID"));
            plasmapheresis.setRadarNumber(resultSet.getLong("RADAR_NO"));

            // The value is a varchar within the database, but we'll just pull out a long as its an ID
            plasmapheresis.setPlasmapheresisExchanges(getPlasmapheresisExchangeUnit(
                    resultSet.getLong("NO_EXCH_PLASMAPH")));

            // Dates
            plasmapheresis.setStartDate(resultSet.getDate("DATE_START_PLASMAPH"));
            plasmapheresis.setEndDate(resultSet.getDate("DATE_STOP_PLASMAPH"));

            plasmapheresis.setResponse(
                    BaseDaoImpl.getEnumValue(RemissionAchieved.class, resultSet.getInt("RESPONSE_TO_PLASMA")));

            // There is a PLASMAPH varchar(20) field but it doesn't appear to be used anywhere
            // It's set to "8P" in the test data, who knows...

            // There is also a  DUR_PLASMAPH int field that doesn't appear to be used

            return plasmapheresis;
        }
    }

    private class PlasmapheresisExchangeUnitRowMapper implements RowMapper<PlasmapheresisExchangeUnit> {
        public PlasmapheresisExchangeUnit mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct the object and set the fields
            PlasmapheresisExchangeUnit plasmapheresisExchangeUnit = new PlasmapheresisExchangeUnit();
            plasmapheresisExchangeUnit.setId(resultSet.getLong("exID"));
            plasmapheresisExchangeUnit.setName(resultSet.getString("exDesc"));
            return plasmapheresisExchangeUnit;
        }
    }
}
