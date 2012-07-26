package com.worthsoln.repository;

import com.worthsoln.patientview.model.UktStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface UktStatusDao {

    UktStatus get(String nhsno);

    void save(UktStatus uktStatus);
}
