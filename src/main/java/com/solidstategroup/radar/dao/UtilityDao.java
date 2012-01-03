package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Country;
import com.solidstategroup.radar.model.Ethnicity;

import java.util.List;

public interface UtilityDao {

    List<Centre> getCentres();

    Country getCountry(long id);

    List<Country> getCountries();

    List<Ethnicity> getEthnicities();
}
