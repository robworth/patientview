package com.worthsoln.service;

import com.worthsoln.patientview.model.UktStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface UKTransplantManager {

    UktStatus getUktStatus(String nhsno);

    void save(UktStatus uktStatus);
}
