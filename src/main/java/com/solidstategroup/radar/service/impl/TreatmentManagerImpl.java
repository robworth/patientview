package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.TreatmentDao;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.model.TreatmentModality;
import com.solidstategroup.radar.model.exception.InvalidModelException;
import com.solidstategroup.radar.service.TreatmentManager;
import com.solidstategroup.radar.util.RadarUtility;

import java.util.ArrayList;
import java.util.List;


public class TreatmentManagerImpl implements TreatmentManager {

    TreatmentDao treatmentDao;

    public void saveTreatment(Treatment treatment) throws InvalidModelException {

        // validation
        List<String> errors = new ArrayList<String>();
        List<Treatment> treatmentsList = treatmentDao.getTreatmentsByRadarNumber(treatment.getRadarNumber());

        // dates must not overlap
        for (Treatment existingTreatment : treatmentsList) {
            if (existingTreatment.getId().equals(treatment.getId())) {
                continue;
            }
            if (RadarUtility.isEventsOverlapping(existingTreatment.getStartDate(), existingTreatment.getEndDate(),
                    treatment.getStartDate(), treatment.getEndDate())) {
                errors.add(TreatmentManager.OVERLAPPING_ERROR);
                break;
            }
        }

        if (!errors.isEmpty()) {
            InvalidModelException exception = new InvalidModelException("plasmapheresis model is not valid");
            exception.setErrors(errors);
            throw exception;
        }
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
