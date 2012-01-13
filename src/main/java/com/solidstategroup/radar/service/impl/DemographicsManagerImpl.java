package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.service.DemographicsManager;

public class DemographicsManagerImpl implements DemographicsManager {

    private DemographicsDao demographicsDao;

    public void saveDemographics(Demographics demographics) {
        // Save or update the demographics object
        demographicsDao.saveDemographics(demographics);
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }
}
