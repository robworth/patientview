package org.patientview.radar.service.impl;

import org.patientview.radar.dao.TreatmentDao;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.TreatmentManager;
import org.patientview.radar.util.RadarUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TreatmentManagerImpl implements TreatmentManager {

    TreatmentDao treatmentDao;
    DemographicsManager demographicsManager;

    public void saveTreatment(Treatment treatment) throws InvalidModelException {

        // validation
        List<String> errors = new ArrayList<String>();
        List<Treatment> treatmentsList = treatmentDao.getTreatmentsByRadarNumber(treatment.getRadarNumber());

        //  Cannot start a new treatment after a previous treatment start date which has not been closed
        for (Treatment existingTreatment : treatmentsList) {
            if (existingTreatment.getId().equals(treatment.getId())) {
                continue;
            }
            if (existingTreatment.getEndDate() == null && treatment.getStartDate().compareTo(
                    existingTreatment.getStartDate()) > 0) {
                if (treatment.getEndDate() != null) {
                    if (treatment.getEndDate().compareTo(existingTreatment.getStartDate())  > 0) {
                        errors.add(TreatmentManager.PREVIOUS_TREATMENT_NOT_CLOSED_ERROR);
                        break;
                    }
                }

            }
        }

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

        List<Date> datesToCheck = Arrays.asList(treatment.getStartDate(), treatment.getEndDate());

        // cannot be before date of birth
        Demographics demographics = demographicsManager.getDemographicsByRadarNumber(treatment.getRadarNumber());
        if (demographics != null) {
            Date dob = demographics.getDateOfBirth();
            if (dob != null) {
                for (Date date : datesToCheck) {
                    if (date != null) {
                        if (dob.compareTo(date) > 0) {
                            errors.add(TreatmentManager.BEFORE_DOB_ERROR);
                            break;
                        }
                    }
                }

            }
        }

        // cannot be after today
        Date today = new Date();
        for (Date date : datesToCheck) {
            if (date != null) {
                if (today.compareTo(date) < 0) {
                    errors.add(TreatmentManager.AFTER_TODAY_ERROR);
                    break;
                }
            }
        }

        if (!errors.isEmpty()) {
            InvalidModelException exception = new InvalidModelException("treatment model is not valid");
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

    public void setDemographicsManager(DemographicsManager demographicsManager) {
        this.demographicsManager = demographicsManager;
    }
}
