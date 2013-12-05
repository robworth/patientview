package org.patientview.radar.dao.impl;

import org.patientview.model.Unit;
import org.patientview.radar.dao.UnitDao;
import org.patientview.radar.model.user.User;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This is in Patient View but functionality needs to be copied to Radar. These used to be called Centre in
 * Radar.
 *
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 16:55
 */
public class UnitDaoImpl extends BaseDaoImpl implements UnitDao {

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    public List<Unit> getUnits(User user) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT  * ");
        query.append("FROM    unit ");
        query.append("WHERE   unitcode IN (SELECT unitcode ");
        query.append("                     FROM   usermapping ");
        query.append("                     WHERE  username = ?  )");

        return jdbcTemplate.query(query.toString(), new Object[]{user.getUsername()}, new UnitRowMapper());


    }


    public List<String> getUnitCodes(User user) {

        return jdbcTemplate.queryForList("SELECT DISTINCT unitcode FROM usermapping WHERE username = ?",
                new Object[]{user.getUsername()}, String.class);
    }


    public List<String> getAllUnitCodes() {
        return jdbcTemplate.queryForList("SELECT DISTINCT unitcode FROM unit",
                new Object[]{}, String.class);
    }

    private class UnitRowMapper implements RowMapper<Unit> {

        public Unit mapRow(ResultSet resultSet, int i) throws SQLException {
            // Only basic fields required for Radar
            Unit unit = new Unit();
            unit.setId(resultSet.getLong("id"));
            unit.setUnitcode(resultSet.getString("unitCode"));
            unit.setSourceType(resultSet.getString("sourceType"));
            unit.setName(resultSet.getString("name"));
            unit.setShortname(resultSet.getString("shortName"));
            return unit;
        }

    }

}
