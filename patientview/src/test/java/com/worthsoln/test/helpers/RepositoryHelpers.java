package com.worthsoln.test.helpers;

import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Conversation;

/**
 *
 */
public interface RepositoryHelpers {

    User createUser(String username, String email, String password, String name);

    User createUserWithMapping(String username, String email, String password, String name,
                               String unitcode, String nhsno, Specialty specialty);

    Specialty createSpecialty(String name, String context, String description);

    SpecialtyUserRole createSpecialtyUserRole(Specialty specialty, User user, String role);

    Conversation createConversation(String subject, User participant1, User participant2, boolean store);

    Message createMessage(Conversation conversation, User sender, User recipient, String content, boolean store);
}
