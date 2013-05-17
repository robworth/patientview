package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.GroupMessage;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.repository.messaging.GroupMessageDao;
import com.worthsoln.service.GroupMessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "groupMessageManager")
public class GroupMessageManagerImpl implements GroupMessageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMessageManagerImpl.class);

    @Inject
    private GroupMessageDao groupMessageDao;

    @Override
    public int getNumberOfUnreadGroupMessages(Long conversationId, GroupEnum groupEnum) {
        return groupMessageDao.getNumberOfUnreadMessages(conversationId, groupEnum);
    }

    @Override
    public void markGroupMessageAsReadForConversation(User recipient, Conversation conversation) {

        if (groupMessageDao.get(recipient.getId(), conversation.getId()) == null) {
            GroupMessage groupMessage = new GroupMessage();
            groupMessage.setConversation(conversation);
            groupMessage.setRecipient(recipient);

            groupMessageDao.save(groupMessage);
        }
    }

    @Override
    public GroupMessage get(Long recipientId, Conversation conversation) {
        return groupMessageDao.get(recipientId, conversation.getId());
    }
}
