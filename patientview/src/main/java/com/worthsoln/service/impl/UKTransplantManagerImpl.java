package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.UktStatus;
import com.worthsoln.repository.UktStatusDao;
import com.worthsoln.service.UKTransplantManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 *
 */
@Service(value = "uKTransplantManager")
public class UKTransplantManagerImpl implements UKTransplantManager {

    @Inject
    private UktStatusDao uktStatusDao;

    @Override
    public UktStatus getUktStatus(String nhsno) {
        return uktStatusDao.get(nhsno);
    }

    @Override
    public void save(UktStatus uktStatus) {
        uktStatusDao.save(uktStatus);
    }
}
