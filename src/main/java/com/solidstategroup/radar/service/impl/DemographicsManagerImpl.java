package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.service.DemographicsManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class DemographicsManagerImpl implements DemographicsManager {

    @SpringBean
    private DemographicsDao demographicsDao;

    public void saveDemographics(Demographics demographics) {
        // Save or update the demographics object
        demographicsDao.saveDemographics(demographics);
    }

}
