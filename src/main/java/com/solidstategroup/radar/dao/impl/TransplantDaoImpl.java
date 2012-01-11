package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.TransplantDao;
import com.solidstategroup.radar.model.Transplant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransplantDaoImpl extends BaseDaoImpl implements TransplantDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransplantDaoImpl.class);

    public Transplant getTransplant(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Transplant WHERE trID = ?", new Object[]{id},
                    new TransplantRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No transplant found for ID {}", id);
            return null;
        }
    }

    public List<Transplant> getTransplantsByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Transplant", new TransplantRowMapper());
    }

    private class TransplantRowMapper implements RowMapper<Transplant> {
        public Transplant mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct transplant object and set fields
            Transplant transplant = new Transplant();

            // IDs
            transplant.setId(resultSet.getLong("trID"));
            transplant.setRadarNumber(resultSet.getLong("RADAR_NO"));

            transplant.setDate(resultSet.getDate("DATE_TRANSPLANT"));
            transplant.setType(resultSet.getString("TRANS_TYPE"));

            transplant.setCounter(resultSet.getInt("TRANSPLANT_COUNTER"));

            // Recurr stuff and dates
            transplant.setRecurr(resultSet.getBoolean("TRANS_RECURR"));
            transplant.setDateRecurr(resultSet.getDate("DATE_RECURR_TXK"));
            transplant.setDateRejected(resultSet.getDate("DATE_TX_REJECT"));
            transplant.setDateBiopsy(resultSet.getDate("DATE_BX_TXK"));

            // Todo: DATE_NEPHRECT datetime,

            return transplant;
        }
    }
}
