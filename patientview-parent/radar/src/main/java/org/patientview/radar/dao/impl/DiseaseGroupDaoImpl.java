/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.dao.impl;


import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DiseaseGroupDaoImpl extends BaseDaoImpl implements DiseaseGroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiseaseGroupDaoImpl.class);

    public List<DiseaseGroup> getAll() {
         List<DiseaseGroup> diseaseGroups =
                 jdbcTemplate.query("SELECT * FROM unit WHERE sourceType='radargroup' ORDER BY name",
                new DiseaseGroupRowMapper());
        Collections.sort(diseaseGroups);
        return diseaseGroups;
    }

    public DiseaseGroup getById(String id) {
        List<DiseaseGroup> list =
                jdbcTemplate.query("SELECT * FROM unit WHERE sourceType='radargroup' AND unitcode = ?",
                new Object[]{id}, new DiseaseGroupRowMapper());
        return CollectionUtils.isEmpty(list)?null:list.get(0);
   }

    private class DiseaseGroupRowMapper implements RowMapper<DiseaseGroup> {
        public DiseaseGroup mapRow(ResultSet resultSet, int i) throws SQLException {
            DiseaseGroup diseaseGroup = new DiseaseGroup();
            diseaseGroup.setId(resultSet.getString("unitcode"));
            diseaseGroup.setName(resultSet.getString("name"));
            diseaseGroup.setShortName(resultSet.getString("shortName"));
            return diseaseGroup;
        }
    }
}
