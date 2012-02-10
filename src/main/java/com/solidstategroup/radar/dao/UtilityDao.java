package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.*;

import java.util.List;
import java.util.Map;

public interface UtilityDao {

    Centre getCentre(long id);

    List<Centre> getCentres();

    Consultant getConsultant(long id);

    List<Consultant> getConsultants();

    Country getCountry(long id);

    List<Country> getCountries();

    Ethnicity getEthnicityByCode(String ethnicityCode);

    List<Ethnicity> getEthnicities();

    Relative getRelative(long id);

    List<Relative> getRelatives();

    Map<Long, Integer> getPatientCountPerUnitByDiagnosisCode(DiagnosisCode diagnosisCode);

    int getPatientCountByUnit(Centre centre);
}
