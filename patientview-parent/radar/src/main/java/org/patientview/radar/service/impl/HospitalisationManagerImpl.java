package org.patientview.radar.service.impl;

import org.patientview.radar.dao.HospitalisationDao;
import org.patientview.radar.model.Hospitalisation;
import org.patientview.radar.service.HospitalisationManager;

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
