package com.worthsoln.service;

import com.worthsoln.ibd.model.enums.XmlImportNotification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface AdminNotificationManager {

    List<String> getEmailAddresses(XmlImportNotification xmlImportNotification);

    String getSupportEmailAddress(ServletContext context);

}
