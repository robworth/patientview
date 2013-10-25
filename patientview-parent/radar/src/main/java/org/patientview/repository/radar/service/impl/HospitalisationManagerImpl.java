package org.patientview.repository.radar.service.impl;

import org.patientview.repository.radar.dao.HospitalisationDao;
import org.patientview.model.radar.Hospitalisation;
import org.patientview.repository.radar.service.HospitalisationManager;

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
