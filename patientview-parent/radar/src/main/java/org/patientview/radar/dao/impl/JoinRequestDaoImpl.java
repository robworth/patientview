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
