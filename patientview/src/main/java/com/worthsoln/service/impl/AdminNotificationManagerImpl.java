package com.worthsoln.service.impl;

import com.worthsoln.ibd.model.enums.XmlImportNotification;
import com.worthsoln.repository.AdminNotificationDao;
import com.worthsoln.service.AdminNotificationManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(value = "adminNotificationManager")
public class AdminNotificationManagerImpl implements AdminNotificationManager {

    @Inject
    private AdminNotificationDao adminNotificationDao;

    @Override
    public List<String> getEmailAddresses(XmlImportNotification xmlImportNotification) {
        return adminNotificationDao.getEmailAddresses(xmlImportNotification);
    }
}
