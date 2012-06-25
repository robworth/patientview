package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.generic.DiseaseGroupDao;
import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.service.generic.DiseaseGroupManager;

import java.util.List;


public class DiseaseGroupManagerImpl implements DiseaseGroupManager {
    private DiseaseGroupDao diseaseGroupDao;

    public List<DiseaseGroup> getAll() {
        return diseaseGroupDao.getAll();
    }

    public DiseaseGroup getById(String id) {
        return diseaseGroupDao.getById(id);
    }

    public void setDiseaseGroupDao(DiseaseGroupDao diseaseGroupDao) {
        this.diseaseGroupDao = diseaseGroupDao;
    }

}
