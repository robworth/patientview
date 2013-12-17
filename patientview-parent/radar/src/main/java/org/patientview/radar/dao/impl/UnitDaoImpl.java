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
