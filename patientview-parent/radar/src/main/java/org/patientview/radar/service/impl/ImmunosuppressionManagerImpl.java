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
import org.patientview.radar.dao.ImmunosuppressionDao;
import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.ImmunosuppressionManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.TreatmentManager;
import org.patientview.radar.util.RadarUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class ImmunosuppressionManagerImpl implements ImmunosuppressionManager {

    ImmunosuppressionDao immunosuppressionDao;

    PatientManager  patientManager;


    public void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppression) throws
            InvalidModelException {

        // validation
        List<String> errors = new ArrayList<String>();
        List<ImmunosuppressionTreatment> ummunosupressionList = immunosuppressionDao.
                getImmunosuppressionTreatmentByRadarNumber(immunosuppression.getRadarNumber());

        //  must have finish date before you can start the same treatment again
        for (ImmunosuppressionTreatment existingImmunosuppression : ummunosupressionList) {
            if (existingImmunosuppression.getId().equals(immunosuppression.getId())) {
                continue;
            }
            if (existingImmunosuppression.getEndDate() == null && existingImmunosuppression.getImmunosuppression().
                    getId().equals(immunosuppression.getImmunosuppression().getId())) {
                errors.add(TreatmentManager.PREVIOUS_TREATMENT_NOT_CLOSED_ERROR);
                break;
            }
        }

        // dates must not overlap
        for (ImmunosuppressionTreatment existingImmunosuppression : ummunosupressionList) {
            if (existingImmunosuppression.getId().equals(immunosuppression.getId())) {
                continue;
            }
            if (existingImmunosuppression.getEndDate() != null && existingImmunosuppression.getImmunosuppression().
                    getId().equals(immunosuppression.getImmunosuppression().getId())) {
                if (RadarUtility.isEventsOverlapping(existingImmunosuppression.getStartDate(),
                        existingImmunosuppression.getEndDate(), immunosuppression.getStartDate(),
                        immunosuppression.getEndDate())) {
                    errors.add(TreatmentManager.OVERLAPPING_ERROR);
                    break;
                }
            }
        }

        List<Date> datesToCheck = Arrays.asList(immunosuppression.getStartDate(), immunosuppression.getEndDate());

        // cannot be before date of birth
        Patient patient = patientManager.getPatientByRadarNumber(
                immunosuppression.getRadarNumber());
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
            InvalidModelException exception = new InvalidModelException("immunosupression model is not valid");
            exception.setErrors(errors);
            throw exception;
        }
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppression);
    }

    public void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) {
        immunosuppressionDao.deleteImmunosuppressionTreatment(immunosuppressionTreatment);
    }

    public ImmunosuppressionTreatment getImmunosuppressionTreatment(long id) {
        return immunosuppressionDao.getImmunosuppressionTreatment(id);
    }

    public List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber) {
        return immunosuppressionDao.getImmunosuppressionTreatmentByRadarNumber(radarNumber);
    }

    public List<Immunosuppression> getImmunosuppressions() {
        return immunosuppressionDao.getImmunosuppressions();
    }

    public Immunosuppression getImmunosuppression(long id) {
        return immunosuppressionDao.getImmunosuppression(id);
    }

    public ImmunosuppressionDao getImmunosuppressionDao() {
        return immunosuppressionDao;
    }

    public void setImmunosuppressionDao(ImmunosuppressionDao immunosuppressionDao) {
        this.immunosuppressionDao = immunosuppressionDao;
    }


    public void setPatientManager(PatientManager patientManager) {
        this.patientManager = patientManager;
    }
}
