package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.TreatmentDao;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;
import com.solidstategroup.radar.service.TreatmentManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class TreatmentManagerImpl implements TreatmentManager {

    TreatmentDao treatmentDao;

    public void saveTreatment(Treatment treatment) {
       treatmentDao.saveTreatment(treatment);
    }

    public void deleteTreatment(Treatment treatment) {
        treatmentDao.deleteTreatment(treatment);
    }

    public Treatment getTreatment(long id) {
        return treatmentDao.getTreatment(id);
    }

    public List<Treatment> getTreatmentsByRadarNumber(long radarNumber) {
        return treatmentDao.getTreatmentsByRadarNumber(radarNumber);
    }

    public TreatmentModality getTreatmentModality(long id) {
        return treatmentDao.getTreatmentModality(id);
    }

    public List<TreatmentModality> getTreatmentModalities() {
        return treatmentDao.getTreatmentModalities();
    }

    public TreatmentDao getTreatmentDao() {
        return treatmentDao;
    }

    public void setTreatmentDao(TreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }
}
