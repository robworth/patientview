package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.PlasmapheresisDao;
import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlasmapheresisDaoImpl extends BaseDaoImpl implements PlasmapheresisDao {

    public Plasmapheresis getPlasmapheresis(long id) {

        // The old SQL statement was as follows

        // SELECT tbl_RRT_PLASMA.plID, tbl_RRT_PLASMA.DATE_START_PLASMAPH, tbl_RRT_PLASMA.DATE_STOP_PLASMAPH,
        // tbl_RRT_PLASMA.DUR_PLASMAPH, tbl_RRT_PLASMA.RESPONSE_TO_PLASMA, tbl_RRT_PLASMA_LU.exDesc
        // FROM tbl_RRT_PLASMA
        // INNER JOIN tbl_RRT_PLASMA_LU ON tbl_RRT_PLASMA.NO_EXCH_PLASMAPH = tbl_RRT_PLASMA_LU.exID
        // WHERE (tbl_RRT_PLASMA.RADAR_NO = @RADAR_NO) ORDER BY tbl_RRT_PLASMA.DATE_START_PLASMAPH DESC

        // Rather than joins lets do n+1 tho, slight performance hit means better testing, more reusable code

        return jdbcTemplate.queryForObject("SELECT * FROM tbl_RRT_PLASMA WHERE plID = ?", new Object[]{id},
                new PlasmapheresisRowMapper());
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
