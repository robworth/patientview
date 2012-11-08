package com.solidstategroup.radar.service.alport.impl;

import com.solidstategroup.radar.dao.alport.GeneticsDao;
import com.solidstategroup.radar.service.alport.GeneticsManager;
import com.solidstategroup.radar.model.alport.Genetics;

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
