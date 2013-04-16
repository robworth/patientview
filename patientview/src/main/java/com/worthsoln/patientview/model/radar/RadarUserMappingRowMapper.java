package com.worthsoln.patientview.model.radar;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RadarUserMappingRowMapper implements RowMapper<RadarUserMapping> {
    public RadarUserMapping mapRow(ResultSet resultSet, int i) throws SQLException {
        RadarUserMapping radarUserMapping = new RadarUserMapping();

        radarUserMapping.setUserId(resultSet.getLong(Radar.USER_MAPPING_USER_ID_FIELD_NAME));
        radarUserMapping.setRadarId(resultSet.getLong(Radar.USER_MAPPING_RADAR_USER_ID_FIELD_NAME));
        radarUserMapping.setRole(resultSet.getString(Radar.USER_MAPPING_ROLE_FIELD_NAME));

        return radarUserMapping;
    }
}
