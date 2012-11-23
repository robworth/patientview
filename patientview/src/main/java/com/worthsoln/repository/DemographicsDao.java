package com.worthsoln.repository;

import com.worthsoln.patientview.model.Demographics;

public interface DemographicsDao {

    /**
     * This method is currently used just to get RadarNo given NHSNo.
     *
     * You may need to implement another method returning a list of demographics
     *
     * @param nhsno
     * @return  demographics object with radar number set only (if demographics exist)
     */
    Demographics getDemographicsByNhsNo(String nhsno);

}
