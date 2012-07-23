package com.worthsoln.repository;

import com.worthsoln.patientview.model.UktStatus;

/**
 *
 */
public interface UktStatusDao {

    UktStatus get(String nhsno);

    void save(UktStatus uktStatus);
}
