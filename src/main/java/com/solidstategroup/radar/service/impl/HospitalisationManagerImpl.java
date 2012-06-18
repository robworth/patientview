package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.HospitalisationDao;
import com.solidstategroup.radar.model.Hospitalisation;
import com.solidstategroup.radar.service.HospitalisationManager;

import java.util.List;


public class HospitalisationManagerImpl implements HospitalisationManager {

    private HospitalisationDao hospitalisationDao;

    public void saveHospitilsation(Hospitalisation hospitalisation) {
        hospitalisationDao.saveHospitilsation(hospitalisation);
    }

    public Hospitalisation getHospitalisation(long id) {
        return hospitalisationDao.getHospitalisation(id);
    }

    public List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber) {
        return hospitalisationDao.getHospitalisationsByRadarNumber(radarNumber);
    }

    public HospitalisationDao getHospitalisationDao() {
        return hospitalisationDao;
    }

    public void setHospitalisationDao(HospitalisationDao hospitalisationDao) {
        this.hospitalisationDao = hospitalisationDao;
    }
}
