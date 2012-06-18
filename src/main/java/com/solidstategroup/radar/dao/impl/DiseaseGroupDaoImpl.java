package com.solidstategroup.radar.dao.impl;


import com.solidstategroup.radar.dao.generic.DiseaseGroupDao;
import com.solidstategroup.radar.model.generic.DiseaseGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DiseaseGroupDaoImpl extends BaseDaoImpl implements DiseaseGroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiseaseGroupDaoImpl.class);

    public List<DiseaseGroup> getAll() {
        return jdbcTemplate.query("SELECT * FROM rdr_disease_group",
                new DiseaseGroupRowMapper());
    }

    public DiseaseGroup getById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM rdr_disease_group WHERE id = ?", new Object[]{id},
                new DiseaseGroupRowMapper());
    }

    private class DiseaseGroupRowMapper implements RowMapper<DiseaseGroup> {
        public DiseaseGroup mapRow(ResultSet resultSet, int i) throws SQLException {
            DiseaseGroup diseaseGroup = new DiseaseGroup();
            diseaseGroup.setId(resultSet.getLong("id"));
            diseaseGroup.setName(resultSet.getString("name"));
            return diseaseGroup;
        }
    }
}
