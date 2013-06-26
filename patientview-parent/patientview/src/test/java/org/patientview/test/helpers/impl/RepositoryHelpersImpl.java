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

package org.patientview.test.helpers.impl;

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.repository.SpecialtyDao;
import org.patientview.repository.SpecialtyUserRoleDao;
import org.patientview.repository.UserDao;
import org.patientview.repository.UserMappingDao;
import org.patientview.repository.messaging.ConversationDao;
import org.patientview.repository.messaging.MessageDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 *
 */
@Repository(value = "repositoryHelpers")
public class RepositoryHelpersImpl implements RepositoryHelpers {

    @Inject
    private SpecialtyDao specialtyDao;

    @Inject
    private UserDao userDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Inject
    private SpecialtyUserRoleDao specialtyUserRoleDao;

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private MessageDao messageDao;

    @Override
    public User createUser(String username, String email, String password, String name) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        userDao.save(user);

        return user;
    }

    @Override
    public User createUserWithMapping(String username, String email, String password, String name,
                                      String unitcode, String nhsno, Specialty specialty) {

        User user = createUser(username, email, password, name);

        UserMapping userMapping = new UserMapping();
        userMapping.setSpecialty(specialty);
        userMapping.setNhsno(nhsno);
        userMapping.setUnitcode(unitcode);
        userMapping.setUsername(username);

        userMappingDao.save(userMapping);

        return user;
    }

    @Override
    public Specialty createSpecialty(String name, String context, String description) {

        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setContext(context);
        specialty.setDescription(description);

        specialtyDao.save(specialty);

        return specialty;
    }

    @Override
    public SpecialtyUserRole createSpecialtyUserRole(Specialty specialty, User user, String role) {

        SpecialtyUserRole specialtyUserRole1 = new SpecialtyUserRole();
        specialtyUserRole1.setRole(role);
        specialtyUserRole1.setSpecialty(specialty);
        specialtyUserRole1.setUser(user);
        specialtyUserRoleDao.save(specialtyUserRole1);

        return specialtyUserRole1;
    }

    @Override
    public Conversation createConversation(String subject, User participant1, User participant2, boolean store) {
        Conversation conversation = new Conversation();

        conversation.setParticipant1(participant1);
        conversation.setParticipant2(participant2);
        conversation.setSubject(subject);

        if (store) {
            conversationDao.save(conversation);
        }

        return conversation;
    }

    public Message createMessage(Conversation conversation, User sender, User recipient, String content,
                                 boolean store) {
        Message message = new Message();

        message.setConversation(conversation);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);

        if (store) {
            messageDao.save(message);
        }

        return message;
    }
}
