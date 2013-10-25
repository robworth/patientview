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

package org.patientview.test.patientview.repository;

import org.junit.Before;
import org.patientview.model.AdminNotification;
import org.patientview.model.enums.XmlImportNotification;
import org.patientview.repository.radar.AdminNotificationDao;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AdminNotificationDaoTest extends BaseDaoTest {

    @Inject
    private AdminNotificationDao adminNotificationDao;

    @Before
    public void setup() {
        AdminNotification adminNotification = new AdminNotification();
        adminNotification.setEmail("test@test.com");
        adminNotification.setXmlImportNotification(XmlImportNotification.FAILED_IMPORT);
        adminNotificationDao.save(adminNotification);
    }

    @Test
    public void testGetAllAdminNotifications() throws Exception {

        List<AdminNotification> adminNotifications = adminNotificationDao.getAll();
        assertNotNull(adminNotifications);
        assertTrue("Should be some AdminNotifications", adminNotifications.size() > 0);
    }

    @Test
    public void testGetEmails() throws Exception {
        List<String> emails = adminNotificationDao.getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        assertTrue("Wrong size of email", emails.size() > 0);
    }

}
