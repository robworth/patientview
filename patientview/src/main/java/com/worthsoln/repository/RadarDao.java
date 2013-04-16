package com.worthsoln.repository;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.radar.Demographics;

public interface RadarDao {

    /**
     * This method is currently used just to get RadarNo given NHSNo.
     *
     * You may need to implement another method returning a list of demographics
     *
     * @param nhsno
     * @return  demographics object with radar number set only (if demographics exist)
     */
    Demographics getDemographicsByNhsNo(String nhsno);

    boolean userExistsInRadar(Long userId);

    void createProfessionalUserInRadar(User user, Unit unit);

    void removeUserFromRadar(Long userId);
}
