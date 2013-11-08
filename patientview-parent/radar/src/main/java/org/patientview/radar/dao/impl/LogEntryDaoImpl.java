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


import org.patientview.radar.dao.LogEntryDao;
import org.patientview.radar.model.LogEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogEntryDaoImpl extends BaseDaoImpl implements LogEntryDao {

    private SimpleJdbcInsert logEntryInsert;
    private final static String DATE = "date";
    private final static String ACTOR = "actor";
    private final static String ACTION = "action";
    private final static String NHSNO = "nhsno";
    private final static String USER = "user";
    private final static String UNITCODE = "unitcode";
    private final static String EXTRAINFO = "extrainfo";
    private final static String SPECIALTY_ID = "specialty_id";

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the log id
        logEntryInsert = new SimpleJdbcInsert(dataSource).withTableName("log")
                .usingGeneratedKeyColumns("id").usingColumns("date", "actor", "action", "nhsno", "user",
                        "unitcode", "extrainfo", "specialty_id");
    }


    public void save(LogEntry logEntry) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(DATE, logEntry.getDate());
        map.put(ACTOR, logEntry.getActor());
        map.put(ACTION, logEntry.getAction());
        map.put(NHSNO, logEntry.getNhsno());
        map.put(USER, logEntry.getUser());
        map.put(UNITCODE, logEntry.getUnitcode());
        map.put(EXTRAINFO, logEntry.getExtrainfo());
        map.put(SPECIALTY_ID, logEntry.getSpecialty());

        Number id = logEntryInsert.executeAndReturnKey(map);
        logEntry.setId(id.longValue());
    }

    public LogEntry get(Long id) {
        List<LogEntry> logEntries = jdbcTemplate.query("SELECT * FROM log WHERE id = ?",
                new Object[]{id}, new LogEntryRowMapper());

        if (logEntries != null && !logEntries.isEmpty()) {
            return logEntries.get(0);
        }

        return null;
    }

    private class LogEntryRowMapper implements RowMapper<LogEntry> {
        public LogEntry mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a lab data object and set all the fields
            LogEntry logEntry = new LogEntry();

            logEntry.setId(resultSet.getLong("id"));
            logEntry.setAction(resultSet.getString("action"));
            logEntry.setDate(resultSet.getDate("date"));
            logEntry.setSpecialty(resultSet.getInt("specialty_id"));
            logEntry.setActor(resultSet.getString("actor"));
            logEntry.setExtrainfo(resultSet.getString("extrainfo"));
            logEntry.setNhsno(resultSet.getString("nhsno"));
            logEntry.setUser(resultSet.getString("user"));

            return logEntry;
        }
    }

}