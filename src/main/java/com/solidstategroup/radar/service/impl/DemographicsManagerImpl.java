package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.model.Status;
import com.solidstategroup.radar.service.DemographicsManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class DemographicsManagerImpl implements DemographicsManager {

    private DemographicsDao demographicsDao;

    public void saveDemographics(Demographics demographics) {
        // Save or update the demographics object
        demographicsDao.saveDemographics(demographics);
    }

    public Demographics getDemographicsByRadarNumber(long radarNumber) {
        return demographicsDao.getDemographicsByRadarNumber(radarNumber);
    }

    public List<Demographics> getDemographicsByRenalUnit(Centre centre) {
        return demographicsDao.getDemographicsByRenalUnit(centre);
    }

    public List<Demographics> getDemographics() {
        return demographicsDao.getDemographics();
    }

    public Sex getSex(long id) {
        return demographicsDao.getSex(id);
    }

    public List<Sex> getSexes() {
        return demographicsDao.getSexes();
    }

    public Status getStatus(long id) {
        return demographicsDao.getStatus(id);
    }

    public List<Status> getStatuses() {
        return demographicsDao.getStatuses();
    }

    public DemographicsDao getDemographicsDao() {
        return demographicsDao;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }
}
