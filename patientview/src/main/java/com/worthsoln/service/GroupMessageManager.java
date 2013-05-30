package com.worthsoln.service;

import com.worthsoln.patientview.logon.PatientLogon;
import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.GroupMessage;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.enums.GroupEnum;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface GroupMessageManager {

    void markGroupMessageAsReadForConversation(User recipient, Conversation conversation);

    GroupMessage get(Long recipientId, Conversation conversation);
}
