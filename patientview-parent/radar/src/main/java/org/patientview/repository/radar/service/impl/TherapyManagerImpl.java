package org.patientview.repository.radar.service.impl;

import org.patientview.repository.radar.dao.TherapyDao;
import org.patientview.model.radar.sequenced.Therapy;
import org.patientview.repository.radar.service.TherapyManager;

import java.util.List;


public class TherapyManagerImpl implements TherapyManager {

    TherapyDao therapyDao;

    public void saveTherapy(Therapy therapy) {
        therapyDao.saveTherapy(therapy);
    }

    public Therapy getTherapy(long id) {
        return therapyDao.getTherapy(id);
    }

    public List<Therapy> getTherapyByRadarNumber(long radarNumber) {
        return therapyDao.getTherapyByRadarNumber(radarNumber);
    }

    public Therapy getFirstTherapyByRadarNumber(long radarNumber) {
        return therapyDao.getFirstTherapyByRadarNumber(radarNumber);
    }

    public TherapyDao getTherapyDao() {
        return therapyDao;
    }

    public void setTherapyDao(TherapyDao therapyDao) {
        this.therapyDao = therapyDao;
    }
}
