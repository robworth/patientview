package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Consultant;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Relative;

import java.util.List;

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
}
