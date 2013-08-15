package org.patientview.radar.service.alport.impl;

import org.patientview.radar.dao.alport.DeafnessDao;
import org.patientview.radar.model.alport.Deafness;
import org.patientview.radar.service.alport.DeafnessManager;

public class DeafnessManagerImpl implements DeafnessManager {

    private DeafnessDao deafnessDao;

    public void save(Deafness deafness) {
        deafnessDao.save(deafness);
    }

    public Deafness get(Long radarNo) {
        return deafnessDao.get(radarNo);
    }

    public void setDeafnessDao(DeafnessDao deafnessDao) {
        this.deafnessDao = deafnessDao;
    }
}
