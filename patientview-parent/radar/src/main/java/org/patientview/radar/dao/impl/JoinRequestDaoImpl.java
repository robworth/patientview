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

import org.patientview.radar.dao.JoinRequestDao;
import org.patientview.radar.model.JoinRequest;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * This needs to be replaced by the DAO from Patient View
 *
 * User: james@solidstategroup.com
 * Date: 11/11/13
 * Time: 14:40
 */
public class JoinRequestDaoImpl extends BaseDaoImpl implements JoinRequestDao {


    private SimpleJdbcInsert patientJoinInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        patientJoinInsert = new SimpleJdbcInsert(dataSource).withTableName("pv_patientjoin_request")
                .usingGeneratedKeyColumns("id").usingColumns("firstname", "lastname", "dateOfbirth", "email",
                        "unitcode", "nhsNo", "dateOfRequest");
    }

    public void saveJoinRequest(final JoinRequest joinRequest) {
        Map<String, Object> joinRequestParameters = new HashMap<String, Object>() {
            {
                put("firstname", joinRequest.getFirstName());
                put("lastname", joinRequest.getLastName());
                put("dateOfBirth", joinRequest.getDateOfBirth());
                put("email", joinRequest.getEmail());
                put("unitcode", joinRequest.getUnitcode());
                put("nhsNo", joinRequest.getNhsNo());
                put("dateOfRequest", joinRequest.getDateOfRequest());

            };
        };
        if (joinRequest.hasValidId()) {
            joinRequestParameters.put("id", joinRequest.getId());
            namedParameterJdbcTemplate.update("UPDATE pv_patientjoin_request "
                    + "SET "
                    + "firstname = :firstname, "
                    + "lastname = :lastname, "
                    + "dateOfBirth = :dateOfBirth, "
                    + "email = :email, "
                    + "unitcode = :unitcode, "
                    + "nhsNo = :nhsNo, "
                    + "dateOfRequest = :dateOfRequest "
                    +  " WHERE labID = :labID; ", joinRequestParameters);


        }    else {
            Number id = patientJoinInsert.executeAndReturnKey(joinRequestParameters);
            joinRequest.setId(id.longValue());
        }
    }
}
