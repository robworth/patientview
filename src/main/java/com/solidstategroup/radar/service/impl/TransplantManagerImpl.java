package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.TransplantDao;
import com.solidstategroup.radar.dao.impl.TransplantDaoImpl;
import com.solidstategroup.radar.model.Transplant;
import com.solidstategroup.radar.model.exception.InvalidModelException;
import com.solidstategroup.radar.service.TransplantManager;
import org.jfree.data.time.Day;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TransplantManagerImpl implements TransplantManager {

    TransplantDao transplantDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransplantDaoImpl.class);

    public void saveTransplant(Transplant transplant) throws InvalidModelException {
        // validate transplant
        List<String> errors = new ArrayList<String>();
        List<Transplant> transplants = transplantDao.getTransplantsByRadarNumber(transplant.getRadarNumber());

        // date of recurrance must be greater than transplant date
        if (transplant.getDateRecurr() != null) {
            if (transplant.getDate().compareTo(transplant.getDateRecurr()) >= 0) {
                errors.add(RECURRANCE_DATE_ERROR);
            }
        }

        // transplant must be 14 days apart
        for (Transplant existingTransplant : transplants) {
            if (existingTransplant.getId().equals(transplant.getId())) {
                continue;
            }
            int daysApart = Math.abs(Days.daysBetween(new DateTime(existingTransplant.getDate()),
                    new DateTime(transplant.getDate())).getDays());
            if (Math.abs(daysApart) <= 14) {
                errors.add(TRANSPLANTS_INTERVAL_ERROR);
            }
        }

        // cannot add new transplant whilst previous transplant has not failed
        for (Transplant existingTransplant : transplants) {
            if (existingTransplant.getId().equals(transplant.getId())) {
                continue;
            }
            if (existingTransplant.getDateFailureRejectData() != null) {
                if (existingTransplant.getDateFailureRejectData().getFailureDate() == null) {
                    errors.add(PREVIOUS_TRANSPLANT_NOT_FAILED_ERROR);
                    break;
                }
            } else {
                errors.add(PREVIOUS_TRANSPLANT_NOT_FAILED_ERROR);
                break;
            }
        }

        // cannot add transplant before another failure date
        for (Transplant existingTransplant : transplants) {
            if (existingTransplant.getId().equals(transplant.getId())) {
                continue;
            }
            if (existingTransplant.getDateFailureRejectData() != null) {
                if (existingTransplant.getDateFailureRejectData().getFailureDate() != null) {
                    Date failureDate = existingTransplant.getDateFailureRejectData().getFailureDate();
                    if (failureDate.compareTo(transplant.getDate()) > 0) {
                        errors.add(BEFORE_PREVIOUS_FAILURE_DATE);
                        break;
                    }

                }
            }
        }

        if (!errors.isEmpty()) {
            InvalidModelException exception = new InvalidModelException("Transplant model is not valid");
            exception.setErrors(errors);
            throw exception;
        }
        transplantDao.saveTransplant(transplant);
    }

    public void deleteTransplant(Transplant transplant) {
        transplantDao.deleteTransplant(transplant);
    }

    public Transplant getTransplant(long id) {
        return transplantDao.getTransplant(id);
    }

    public List<Transplant> getTransplantsByRadarNumber(long radarNumber) {
        return transplantDao.getTransplantsByRadarNumber(radarNumber);
    }

    public List<Transplant.Modality> getTransplantModalitites() {
        return transplantDao.getTransplantModalitites();
    }

    public Transplant.Modality getTransplantModality(long id) {
        return transplantDao.getTransplantModality(id);
    }

    public void saveRejectData(Transplant.RejectData rejectData) {
        transplantDao.saveRejectData(rejectData);
    }

    public void deleteRejectData(Transplant.RejectData rejectData) {
        transplantDao.deleteRejectData(rejectData);
    }

    public List<Transplant.RejectData> getRejectDataByTransplantNumber(Long transplantNumber) {
        return transplantDao.getRejectDataByTransplantNumber(transplantNumber);
    }

    public Transplant.RejectData getRejectData(Long id) {
        return transplantDao.getRejectData(id);
    }

    public TransplantDao getTransplantDao() {
        return transplantDao;
    }

    public void setTransplantDao(TransplantDao transplantDao) {
        this.transplantDao = transplantDao;
    }
}
