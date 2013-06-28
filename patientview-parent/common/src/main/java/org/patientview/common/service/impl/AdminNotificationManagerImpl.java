package org.patientview.common.service.impl;

import org.patientview.common.model.enums.XmlImportNotification;
import org.patientview.common.repository.AdminNotificationDao;
import org.patientview.common.service.AdminNotificationManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.util.List;

@Service(value = "adminNotificationManager")
public class AdminNotificationManagerImpl implements AdminNotificationManager {

    @Inject
    private AdminNotificationDao adminNotificationDao;

    @Override
    public List<String> getEmailAddresses(XmlImportNotification xmlImportNotification) {
        return adminNotificationDao.getEmailAddresses(xmlImportNotification);
    }

    @Override
    public String getSupportEmailAddress(ServletContext context) {
        String supportEmailAddress = adminNotificationDao.getSupportEmail();

        if (StringUtils.isBlank(supportEmailAddress)) {
            return context.getInitParameter("support.email");
        } else {
            return supportEmailAddress;
        }
    }
}
