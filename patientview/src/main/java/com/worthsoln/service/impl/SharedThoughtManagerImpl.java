package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Conversation;
import com.worthsoln.patientview.model.Message;
import com.worthsoln.patientview.model.SharedThought;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SharedThoughtDao;
import com.worthsoln.repository.messaging.ConversationDao;
import com.worthsoln.service.SharedThoughtManager;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service(value = "sharedThoughtManager")
public class SharedThoughtManagerImpl implements SharedThoughtManager {

    @Inject
    private SharedThoughtDao sharedThoughtDao;

    @Override
    public SharedThought getSharedThought(Long sharedThoughtId) {
        return sharedThoughtDao.get(sharedThoughtId);
    }

    @Override
    public List<SharedThought> getUsersThoughts(Long userId, boolean isSubmitted) {
        return sharedThoughtDao.getUsersThoughts(userId, isSubmitted);
    }

    @Override
    public void delete(Long sharedThoughtId) {
        sharedThoughtDao.delete(sharedThoughtId);
    }

    @Override
    public void save(SharedThought thought) {
        thought.setDateLastSaved(GregorianCalendar.getInstance().getTime());
        sharedThoughtDao.save(thought);
    }
}
