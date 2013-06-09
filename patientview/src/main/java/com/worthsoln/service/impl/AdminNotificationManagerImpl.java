package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.enums.XmlImportNotification;
import com.worthsoln.repository.AdminNotificationDao;
import com.worthsoln.service.AdminNotificationManager;
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
