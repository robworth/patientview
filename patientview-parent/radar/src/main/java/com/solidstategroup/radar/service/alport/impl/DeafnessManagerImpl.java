package com.solidstategroup.radar.service.alport.impl;

import com.solidstategroup.radar.dao.alport.DeafnessDao;
import com.solidstategroup.radar.model.alport.Deafness;
import com.solidstategroup.radar.service.alport.DeafnessManager;

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
