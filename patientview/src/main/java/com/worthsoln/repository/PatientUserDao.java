package com.worthsoln.repository;

import com.worthsoln.patientview.model.PatientUser;

public interface PatientUserDao {

    /**
     *
     * @param radarNo
     * @return patientuser object with pID information set only
     */
    PatientUser getPatientUserByRadarNo(long radarNo);

}
