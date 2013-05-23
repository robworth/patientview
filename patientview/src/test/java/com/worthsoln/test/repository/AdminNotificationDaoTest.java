package com.worthsoln.test.repository;

import com.worthsoln.ibd.model.AdminNotification;
import com.worthsoln.ibd.model.enums.XmlImportNotification;
import com.worthsoln.repository.AdminNotificationDao;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AdminNotificationDaoTest extends BaseDaoTest {

    @Inject
    private AdminNotificationDao adminNotificationDao;

    @Test
    public void testGetAllAdminNotifications() throws Exception {
        List<AdminNotification> adminNotifications = adminNotificationDao.getAll();

        assertNotNull(adminNotifications);
        assertTrue(adminNotifications.size() > 0);
    }

    @Test
    public void testGetEmails() throws Exception {
        List<String> emails = adminNotificationDao.getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        assertTrue(emails.size() > 0);
    }

}
