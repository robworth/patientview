package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.*;

import java.util.List;

public interface UtilityManager {

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
}
