package com.worthsoln.repository;

import com.worthsoln.ibd.model.AdminNotification;
import com.worthsoln.patientview.model.enums.XmlImportNotification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface AdminNotificationDao {

    List<AdminNotification> getAll();

    List<String> getEmailAddresses(XmlImportNotification xmlImportNotification);

    String getSupportEmail();

}
