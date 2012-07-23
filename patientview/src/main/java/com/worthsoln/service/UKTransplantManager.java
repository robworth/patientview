package com.worthsoln.service;

import com.worthsoln.patientview.model.UktStatus;

/**
 *
 */
public interface UKTransplantManager {

    UktStatus getUktStatus(String nhsno);

    void save(UktStatus uktStatus);
}
