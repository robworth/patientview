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

package org.patientview.test.repository;

import org.junit.Test;
import org.patientview.model.patientview.UserLog;
import org.patientview.repository.UserLogDao;

import javax.inject.Inject;

import java.util.Calendar;

import static org.junit.Assert.*;

public class UserLogDaoTest extends BaseDaoTest {

    @Inject
    private UserLogDao userLogDao;

    @Test
    public void testAddGetUserLog() throws Exception {

        Calendar calendar = Calendar.getInstance();
        UserLog userLog = new UserLog();
        userLog.setNhsno("9876543210");
        userLog.setUnitcode("testCode");
        userLog.setLastdatadate(calendar);

        userLogDao.save(userLog);

        assertTrue("Invalid id for new UserLog", userLog.getId() > 0);

        UserLog checkUserLog = userLogDao.get(userLog.getNhsno());

        assertNotNull(checkUserLog);
        assertEquals("Nhs number not persisted", checkUserLog.getNhsno(), userLog.getNhsno());
        assertEquals("unit code not persisted", checkUserLog.getUnitcode(), userLog.getUnitcode());
        assertEquals("Last date not persisted", checkUserLog.getLastdatadate(), userLog.getLastdatadate());
    }
}
