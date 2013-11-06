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

import java.util.HashMap;
import java.util.Map;

public class LogEntryDaoImpl extends BaseDaoImpl implements LogEntryDao {

    private final static String DATE = "date";
    private final static String ACTOR = "actor";
    private final static String ACTION = "action";
    private final static String NHSNO = "nhsno";
    private final static String USER = "user";
    private final static String UNITCODE = "unitcode";
    private final static String EXTRAINFO = "extrainfo";
    private final static String SPECIALTY_ID = "specialty_id";


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

        String sql = "insert into log(date,actor,action,nhsno,user,unitcode,extrainfo,specialty_id)" +
                " values (:date, :actor, :action, :nhsno, :user, :unitcode, :extrainfo, :specialty_id)";

        namedParameterJdbcTemplate.update(sql, map);
    }


}
