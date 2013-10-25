package org.patientview.repository.radar.service.alport.impl;

import org.patientview.repository.radar.dao.alport.GeneticsDao;
import org.patientview.model.radar.Genetics;
import org.patientview.repository.radar.service.alport.GeneticsManager;

public class GeneticsManagerImpl implements GeneticsManager {

    private GeneticsDao geneticsDao;

    public void save(Genetics genetics) {
        geneticsDao.save(genetics);
    }

    public Genetics get(Long radarNo) {
        return geneticsDao.get(radarNo);
    }

    public void setGeneticsDao(GeneticsDao geneticsDao) {
        this.geneticsDao = geneticsDao;
    }
}
