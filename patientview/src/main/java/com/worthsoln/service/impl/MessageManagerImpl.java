package com.worthsoln.service.impl;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.repository.messaging.MessageDao;
import com.worthsoln.service.EmailManager;
import com.worthsoln.service.MessageManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
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

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service(value = "messageManager")
public class MessageManagerImpl implements MessageManager {

    // TODO: could build the string to format based on if its more than 1 day more than 1 year etc
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yy HH:mm");
    private static final SimpleDateFormat SHORT_DAY_FORMAT = new SimpleDateFormat("HH:mm");

    @Inject
    private ConversationDao conversationDao;

    @Inject
    private MessageDao messageDao;

    @Inject
    private EmailManager emailManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Override
    public Conversation getConversation(Long conversationId) {
        return conversationDao.get(conversationId);
    }

    @Override
    public Conversation getConversationForUser(Long conversationId, Long participantId) {
        Conversation conversation = conversationDao.get(conversationId);

        if (!userHasAccessToConversation(conversation, participantId)) {
            return null;
        }

        populateConversation(conversation, participantId);
        return conversation;
    }

    @Override
    public List<Conversation> getConversations(Long participantId) {
        List<Conversation> conversations = conversationDao.getConversations(participantId);
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
    public List<User> getUnitAdminRecipients(List<Unit> units, User requestingUser) {
        List<User> unitAdminRecipients = new ArrayList<User>();

        if (units != null) {
            for (Unit unit : units) {
                unitAdminRecipients.addAll(getUnitAdminRecipients(unit, requestingUser));
            }
        }

        // sort by name
        Collections.sort(unitAdminRecipients, new UserComparator());

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
    public List<User> getUnitStaffRecipients(List<Unit> units, User requestingUser) {
        List<User> unitStaffRecipients = new ArrayList<User>();

        if (units != null) {
            for (Unit unit : units) {
                unitStaffRecipients.addAll(getUnitStaffRecipients(unit, requestingUser));
            }
        }

        // sort by name
        Collections.sort(unitStaffRecipients, new UserComparator());

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
    public List<User> getUnitPatientRecipients(List<Unit> units, User requestingUser) {
        List<User> unitPatientRecipients = new ArrayList<User>();

        if (units != null) {
            for (Unit unit : units) {
                unitPatientRecipients.addAll(getUnitPatientRecipients(unit, requestingUser));
            }
        }

        // sort by name
        Collections.sort(unitPatientRecipients, new UserComparator());

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

    private List<User> getUnitAdminOrStaffRecipients(String adminOrStaff, Unit unit, User requestingUser) {
        List<User> unitAdminRecipients = new ArrayList<User>();

        if (unit != null) {
            if (!unit.getUnitcode().equalsIgnoreCase("patient")) {
                List<UnitAdmin> unitAdmins = unitManager.getUnitUsers(unit.getUnitcode());

                for (UnitAdmin unitAdmin : unitAdmins) {
                    User unitUser = userManager.get(unitAdmin.getUsername());

                    if (!unitUser.equals(requestingUser)) {
                        if (unitAdmin.getRole().equals(adminOrStaff)) {
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
            conversation.setNumberUnread(messageDao.getNumberOfUnreadMessages(
                    participantId, conversation.getId()).intValue());

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
