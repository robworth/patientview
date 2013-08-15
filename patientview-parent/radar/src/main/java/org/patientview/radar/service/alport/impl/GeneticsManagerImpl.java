package org.patientview.radar.service.alport.impl;

import org.patientview.radar.dao.alport.GeneticsDao;
import org.patientview.radar.model.Genetics;
import org.patientview.radar.service.alport.GeneticsManager;

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
