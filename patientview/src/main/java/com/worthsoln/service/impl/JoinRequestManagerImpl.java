package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.repository.JoinRequestDao;
import com.worthsoln.service.JoinRequestManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "joinRequestManager")
public class JoinRequestManagerImpl implements JoinRequestManager {

    @Inject
    JoinRequestDao joinRequestDao;

    @Override
    public void save(JoinRequest joinRequest) {
        joinRequestDao.save(joinRequest);
    }
}
