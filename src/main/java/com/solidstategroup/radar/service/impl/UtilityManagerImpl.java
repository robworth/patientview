package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.*;
import com.solidstategroup.radar.service.UtilityManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class UtilityManagerImpl implements UtilityManager {

    private UtilityDao utilityDao;

    public Centre getCentre(long id) {
        return utilityDao.getCentre(id);
    }

    public List<Centre> getCentres() {
        return utilityDao.getCentres();
    }

    public Consultant getConsultant(long id) {
        return utilityDao.getConsultant(id);
    }

    public List<Consultant> getConsultants() {
        return utilityDao.getConsultants();
    }

    public Country getCountry(long id) {
        return utilityDao.getCountry(id);
    }

    public List<Country> getCountries() {
        return utilityDao.getCountries();
    }

    public Ethnicity getEthnicityByCode(String ethnicityCode) {
        return utilityDao.getEthnicityByCode(ethnicityCode);
    }

    public List<Ethnicity> getEthnicities() {
        return utilityDao.getEthnicities();
    }

    public Relative getRelative(long id) {
        return utilityDao.getRelative(id);
    }

    public List<Relative> getRelatives() {
        return utilityDao.getRelatives();
    }

    public UtilityDao getUtilityDao() {
        return utilityDao;
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
