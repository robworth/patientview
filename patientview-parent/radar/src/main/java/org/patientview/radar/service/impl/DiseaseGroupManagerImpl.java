package org.patientview.radar.service.impl;

import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.service.generic.DiseaseGroupManager;

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
