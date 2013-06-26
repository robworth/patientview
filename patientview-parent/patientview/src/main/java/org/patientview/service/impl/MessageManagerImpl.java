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

package org.patientview.service.impl;

import org.patientview.patientview.logon.UnitAdmin;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.MessageRecipient;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.job.JobDao;
import org.patientview.repository.messaging.ConversationDao;
import org.patientview.repository.messaging.MessageDao;
import org.patientview.service.EmailManager;
import org.patientview.service.FeatureManager;
import org.patientview.service.MessageManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.patientview.service.GroupMessageManager;
import org.patientview.service.SecurityUserManager;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "messageManager")
public class MessageManagerImpl implements MessageManager {

    // TODO: could build the string to format based on if its more than 1 day more than 1 year etc
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yy HH:mm");
    private static final SimpleDateFormat SHORT_DAY_FORMAT = new SimpleDateFormat("HH:mm");

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private FeatureManager featureManager;

    @Inject
    private MessageDao messageDao;

    @Inject
    private JobDao jobDao;

    @Inject
    private EmailManager emailManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Inject
    private GroupMessageManager groupMessageManager;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public Conversation getConversation(Long conversationId) {
        return conversationDao.get(conversationId);
    }

    @Override
    public Conversation getConversationForUser(Long conversationId, Long participantId) {
        Conversation conversation = conversationDao.get(conversationId);

        if (conversation.getType() == null) {
            if (!userHasAccessToConversation(conversation, participantId)) {
                return null;
            }
        }


        populateConversation(conversation, participantId);
        return conversation;
    }

    @Override
    public List<Conversation> getConversations(Long participantId) {
        GroupEnum groupEnum = GroupEnum.ALL_ADMINS;
        if (securityUserManager.isRolePresent("unitadmin")) {
            groupEnum = GroupEnum.ALL_ADMINS;
        } else if (securityUserManager.isRolePresent("unitstaff")) {
            groupEnum = GroupEnum.ALL_STAFF;
        } else if (securityUserManager.isRolePresent("patient")) {
            groupEnum = GroupEnum.ALL_PATIENTS;
        } else {
            groupEnum = null;
        }

        List<Conversation> conversations = conversationDao.getConversations(participantId, groupEnum);
        conversations = this.canIncludeInConversations(conversations, participantId);
        populateConversations(conversations, participantId);

        // conversations need to be ordered by last activity which means when the last message in the thread was sent
        Collections.sort(conversations, new Comparator<Conversation>() {
            @Override
            public int compare(Conversation o1, Conversation o2) {
                return o1.getLatestMessageDate().compareTo(o2.getLatestMessageDate()) * -1;
            }
        });

        return conversations;
    }

    public List<Conversation> canIncludeInConversations(List<Conversation> conversations, Long participantId) {
        List<Conversation> conversationList = new ArrayList<Conversation>();
        List<Message> messages;
        User user = userManager.get(participantId);
        List<Unit> units = unitManager.getUsersUnits(user);

        if (conversations != null) {
            for (Conversation conversation : conversations) {

                if (StringUtils.hasLength(conversation.getType())) {
                    messages = getMessages(conversation.getId());

                    if (messages != null && !messages.isEmpty()) {
                        for (Unit unit : units) {
                            Unit messageUnit = messages.get(0).getUnit();
                            if (unit != null && messageUnit != null && unit.getId().equals(messageUnit.getId())) {
                                conversationList.add(conversation);
                                break;
                            }
                        }
                    }
                } else {
                    conversationList.add(conversation);
                }
            }
        }

        return conversationList;
    }

    @Override
    public void deleteConversation(Long conversationId) {
        deleteConversation(conversationDao.get(conversationId));
    }

    @Override
    public void deleteConversation(Conversation conversation) {
        if (conversation != null) {
            conversationDao.delete(conversation);
        }
    }

    @Override
    public List<Message> getMessages(Long conversationId) {
        List<Message> messages = messageDao.getMessages(conversationId);

        // go through and set any values the manager creates and return
        for (Message message : messages) {
            message.setFriendlyDate(getFriendlyDateTime(message.getDate()));
        }

        return messages;
    }

    @Override
    public Message createMessage(ServletContext context, String subject, String content, User sender, User recipient) {
        if (!StringUtils.hasText(subject)) {
            throw new IllegalArgumentException("Invalid required parameter subject");
        }

        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("Invalid required parameter content");
        }

        if (sender == null || !sender.hasValidId()) {
            throw new IllegalArgumentException("Invalid required parameter sender");
        }

        if (recipient == null || !recipient.hasValidId()) {
            throw new IllegalArgumentException("Invalid required parameter recipient");
        }

        Conversation conversation = new Conversation();
        conversation.setParticipant1(sender);
        conversation.setParticipant2(recipient);
        conversation.setSubject(subject);
        conversationDao.save(conversation);

        return sendMessage(context, conversation, sender, recipient, content);
    }

    @Override
    public Message createGroupMessage(ServletContext context, String subject, String content, User sender,
                                      String groupName, String type, Unit unit) throws Exception {
        if (!StringUtils.hasText(subject)) {
            throw new IllegalArgumentException("Invalid required parameter subject");
        }

        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("Invalid required parameter content");
        }

        if (sender == null || !sender.hasValidId()) {
            throw new IllegalArgumentException("Invalid required  parameter sender");
        }

        if (unit == null || !unit.hasValidId()) {
            throw new IllegalArgumentException("Invalid required  parameter unit");
        }

        Conversation conversation = new Conversation();
        Message message = new Message();
        Job job = new Job();

        conversation.setParticipant1(sender);
        conversation.setParticipant2(sender);
        conversation.setSubject(subject);
        conversation.setType(type);

        if (groupName != null) {
            if ("allAdmins".equals(groupName)) {
                conversation.setGroupEnum(GroupEnum.ALL_ADMINS);
                message.setGroupEnum(GroupEnum.ALL_ADMINS);
                job.setGroupEnum(GroupEnum.ALL_ADMINS);
            }

            if ("allPatients".equals(groupName)) {
                conversation.setGroupEnum(GroupEnum.ALL_PATIENTS);
                message.setGroupEnum(GroupEnum.ALL_PATIENTS);
                job.setGroupEnum(GroupEnum.ALL_PATIENTS);
            }

            if ("allStaff".equals(groupName)) {
                conversation.setGroupEnum(GroupEnum.ALL_STAFF);
                message.setGroupEnum(GroupEnum.ALL_STAFF);
                job.setGroupEnum(GroupEnum.ALL_STAFF);
            }
        }

        conversationDao.save(conversation);

        // add group message
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);
        message.setType(type);
        message.setUnit(unit);
        messageDao.save(message);

        // add a Job
        job.setStatus(SendEmailEnum.PENDING);
        job.setCreator(sender);
        job.setMessage(message);
        job.setSpecialty(securityUserManager.getLoggedInSpecialty());
        jobDao.save(job);

        return message;
    }

    @Override
    public Message replyToMessage(ServletContext context, String content, Long conversationId, User sender)
            throws Exception {
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("Invalid required parameter content");
        }

        if (conversationId == null || conversationId <= 0) {
            throw new IllegalArgumentException("Invalid required parameter conversationId");
        }

        if (sender == null || !sender.hasValidId()) {
            throw new IllegalArgumentException("Invalid required parameter sender");
        }

        // check for the conversation
        Conversation conversation = getConversation(conversationId);

        if (conversation == null) {
            throw new IllegalArgumentException("Invalid conversation");
        }

        return sendMessage(context, conversation, sender, getOtherUser(conversation, sender.getId()), content);
    }

    @Override
    public int getTotalNumberUnreadMessages(Long recipientId) {
        int total = 0;

        List<Conversation> conversations = getConversations(recipientId);

        for (Conversation conversation : conversations) {
            total += conversation.getNumberUnread();
        }

        return total;
    }

    @Override
    public void markMessagesAsReadForConversation(Long loggedInUserId, Long conversationId) {
        List<Message> unreadMessages = messageDao.getUnreadMessages(loggedInUserId, conversationId);

        for (Message message : unreadMessages) {
            // check if the logged in user is the recipient
            if (message.getRecipient().getId().equals(loggedInUserId)) {
                message.setHasRead(true);
                messageDao.save(message);
            }
        }
    }

    @Override
    public List<MessageRecipient> getUnitAdminRecipients(List<Unit> units, User requestingUser) {
        List<MessageRecipient> unitAdminRecipients = new ArrayList<MessageRecipient>();

        if (units != null) {
            List<Unit> messagingEnabledUnits = featureManager.getUnitsForFeature("messaging");

            units.retainAll(messagingEnabledUnits);

            for (Unit unit : units) {
                for (User user : getUnitAdminRecipients(unit, requestingUser)) {
                    unitAdminRecipients.add(new MessageRecipient(user, unit));
                }
            }
        }

        Collections.sort(unitAdminRecipients, MessageRecipient.Order.ByUsername.ascending());

        return unitAdminRecipients;
    }

    @Override
    public List<User> getUnitAdminRecipients(Unit unit, User requestingUser) {
        List<User> unitAdminRecipients = new ArrayList<User>();

        if (unit != null) {
            unitAdminRecipients = getUnitAdminOrStaffRecipients("unitadmin", unit, requestingUser);
        }

        // sort by name
        Collections.sort(unitAdminRecipients, new UserComparator());

        return unitAdminRecipients;
    }

    @Override
    public List<MessageRecipient> getUnitStaffRecipients(List<Unit> units, User requestingUser) {
        List<MessageRecipient> unitStaffRecipients = new ArrayList<MessageRecipient>();

        if (units != null) {
            List<Unit> messagingEnabledUnits = featureManager.getUnitsForFeature("messaging");

            units.retainAll(messagingEnabledUnits);

            for (Unit unit : units) {
                for (User user : getUnitStaffRecipients(unit, requestingUser)) {
                    unitStaffRecipients.add(new MessageRecipient(user, unit));
                }
            }
        }

        Collections.sort(unitStaffRecipients, MessageRecipient.Order.ByUsername.ascending());

        return unitStaffRecipients;
    }

    @Override
    public List<User> getUnitStaffRecipients(Unit unit, User requestingUser) {
        List<User> unitStaffRecipients = new ArrayList<User>();

        if (unit != null) {
            unitStaffRecipients = getUnitAdminOrStaffRecipients("unitstaff", unit, requestingUser);
        }

        // sort by name
        Collections.sort(unitStaffRecipients, new UserComparator());

        return unitStaffRecipients;
    }

    @Override
    public List<MessageRecipient> getUnitPatientRecipients(List<Unit> units, User requestingUser) {
        List<MessageRecipient> unitPatientRecipients = new ArrayList<MessageRecipient>();

        if (units != null) {
            List<Unit> messagingEnabledUnits = featureManager.getUnitsForFeature("messaging");

            units.retainAll(messagingEnabledUnits);

            for (Unit unit : units) {
                for (User user : getUnitPatientRecipients(unit, requestingUser)) {
                    unitPatientRecipients.add(new MessageRecipient(user, unit));
                }
            }
        }

        Collections.sort(unitPatientRecipients, MessageRecipient.Order.ByUsername.ascending());

        return unitPatientRecipients;
    }

    @Override
    public List<User> getUnitPatientRecipients(Unit unit, User requestingUser) {
        List<User> unitPatientRecipients = new ArrayList<User>();

        if (unit != null) {
            if (!unit.getUnitcode().equalsIgnoreCase("patient")) {
                List<User> unitPatients = unitManager.getUnitPatientUsers(unit.getUnitcode(),
                        unit.getSpecialty());

                for (User unitPatient : unitPatients) {
                    if (canIncludePatient(unitPatient)) {
                        unitPatientRecipients.add(unitPatient);
                    }
                }
            }
        }

        // sort by name
        Collections.sort(unitPatientRecipients, new UserComparator());

        return unitPatientRecipients;
    }

    @Override
    public List<Unit> getMessagingEnabledUnitsForLoggedInUser() {
        List<Unit> loggedInUsersUnits = unitManager.getLoggedInUsersUnits();

        List<Unit> messagingEnabledUnits = featureManager.getUnitsForFeature("messaging");

        loggedInUsersUnits.retainAll(messagingEnabledUnits);

        return loggedInUsersUnits;
    }

    private List<User> getUnitAdminOrStaffRecipients(String adminOrStaff, Unit unit, User requestingUser) {
        List<User> unitAdminRecipients = new ArrayList<User>();

        if (unit != null) {
            if (!unit.getUnitcode().equalsIgnoreCase("patient")) {
                List<UnitAdmin> unitAdmins = unitManager.getUnitUsers(unit.getUnitcode());

                for (UnitAdmin unitAdmin : unitAdmins) {
                    User unitUser = userManager.get(unitAdmin.getUsername());

                    if (!unitUser.equals(requestingUser)) {
                        if (unitAdmin.getRole().equals(adminOrStaff) && unitUser.isIsrecipient()) {
                            unitAdminRecipients.add(unitUser);
                        }
                    }
                }
            }
        }

        return unitAdminRecipients;
    }

    /**
     * exclude patients that have no got an email set
     * exlude patients with '-gp' or 'dummy' in the name
     */
    private boolean canIncludePatient(User patient) {
        return patient.getName() != null
                && !patient.getName().toLowerCase().contains("-gp")
                && !patient.isDummypatient();
    }

    private Message sendMessage(ServletContext context, Conversation conversation, User sender, User recipient,
                                String content) {
        // save message before sending as they will still see it if the emails fails after
        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        messageDao.save(message);

        // if the recipient has an email address, send an email
        if (StringUtils.hasText(message.getRecipient().getEmail())) {
            emailManager.sendUserMessage(context, message);
        }

        message.setFriendlyDate(getFriendlyDateTime(message.getDate()));

        return message;
    }

    private boolean userHasAccessToConversation(Conversation conversation, Long participantId) {
        return conversation.getParticipant1().getId().equals(participantId)
                || conversation.getParticipant2().getId().equals(participantId);
    }

    private void populateConversations(List<Conversation> conversations, Long participantId) {
        if (conversations != null) {
            for (Conversation conversation : conversations) {
                populateConversation(conversation, participantId);
            }
        }
    }

    // need to populate this list for the user
    // we add the total number of unread for each convo for THAT user
    // we add the summary of the last message in that convo and the date of it
    // need to go through and show how many messages in a convo that user needs to read
    private void populateConversation(Conversation conversation, Long participantId) {
        if (conversation != null) {
            // type is not null indicate the group message
            if (conversation.getType() != null) {
                // the bulk message is not new for unitadmin who send it
                if (!securityUserManager.isRolePresent("superadmin")
                        && !(securityUserManager.isRolePresent("unitadmin")
                        && participantId.equals(conversation.getParticipant1().getId()))) {
                    if (groupMessageManager.get(participantId, conversation) ==  null) {
                        conversation.setNumberUnread(1);
                    }
                }
            } else {
                conversation.setNumberUnread(messageDao.getNumberOfUnreadMessages(
                    participantId, conversation.getId()).intValue());
            }


            // set the summary details for the convo to the last message
            Message latestMessage = messageDao.getLatestMessage(conversation.getId());

            if (latestMessage != null) {
                conversation.setLatestMessageSummary(latestMessage.getSummary());
                conversation.setLatestMessageDate(latestMessage.getDate());
                // also wnat to make a friendly time like 2 secs ago - doing this in here in case it gets complicated
                conversation.setFriendlyLatestMessageDate(getFriendlyDateTime(latestMessage.getDate()));
            }

            // as there two users in the convo we want the front end to be able to show titles based on the other
            // user in the convo and not the user who is viewing it
            conversation.setOtherUser(getOtherUser(conversation, participantId));
        }
    }

    private String getFriendlyDateTime(Date date) {
        DateTime now = new DateTime();
        DateTime dateTime = new DateTime(date);

        if (dateTime.getYear() == now.getYear()
                && dateTime.getMonthOfYear() == now.getMonthOfYear()
                && dateTime.getDayOfWeek() == now.getDayOfWeek()) {
            return SHORT_DAY_FORMAT.format(date);
        } else {
            return DATE_FORMAT.format(date);
        }
    }

    private User getOtherUser(Conversation conversation, Long participantId) {
        if (conversation.getParticipant1().getId().equals(participantId)) {
            return conversation.getParticipant2();
        } else {
            return conversation.getParticipant1();
        }
    }

    private class UserComparator implements Comparator<User> {
        @Override
        public int compare(User o1, User o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
