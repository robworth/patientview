/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.radar.dao.TreatmentDao;
import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.TreatmentManager;
import org.patientview.radar.util.RadarUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TreatmentManagerImpl implements TreatmentManager {

    TreatmentDao treatmentDao;
    PatientManager patientManager;

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
        Patient patient =  patientManager.getPatientByRadarNumber(treatment.getRadarNumber());
        if (patient != null) {
            Date dob = patient.getDob();
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

    public void setPatientManager(PatientManager patientManager) {
        this.patientManager = patientManager;
    }
}
