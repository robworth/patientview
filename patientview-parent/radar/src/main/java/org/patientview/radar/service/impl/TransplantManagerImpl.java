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

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.patientview.model.Patient;
import org.patientview.radar.dao.TransplantDao;
import org.patientview.radar.dao.impl.TransplantDaoImpl;
import org.patientview.radar.model.Transplant;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.TransplantManager;
import org.patientview.radar.service.TreatmentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TransplantManagerImpl implements TransplantManager {

    TransplantDao transplantDao;
    PatientManager patientManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransplantDaoImpl.class);

    public void saveTransplant(Transplant transplant) throws InvalidModelException {
        // validate transplant
        List<String> errors = new ArrayList<String>();
        List<Transplant> transplants = transplantDao.getTransplantsByRadarNumber(transplant.getRadarNumber());

        // transplant date must be greater than reccur, failure date, date reject and date biopsy
        List<Date> datesAfterStart = new ArrayList<Date>();
        datesAfterStart.addAll(Arrays.asList(transplant.getDateRecurr(),
                transplant.getDateFailureRejectData().getFailureDate()));
        List<Transplant.RejectData> rejectDataList = transplantDao.getRejectDataByTransplantNumber(
                transplant.getId());
        for (Transplant.RejectData rejectData : rejectDataList) {
            datesAfterStart.add(rejectData.getRejectedDate());
            datesAfterStart.add(rejectData.getBiopsyDate());
        }

        for (Date dateAfterStart : datesAfterStart) {
            if (dateAfterStart != null) {
                if (transplant.getDate().compareTo(dateAfterStart) >= 0) {
                    errors.add(START_DATE_ERROR);
                    break;
                }
            }
        }

        // date of failure has to be after  reccur, date reject and date biopsy
        List<Date> datesAfterEndDate = new ArrayList<Date>();
        datesAfterEndDate.add(transplant.getDateRecurr());
        for (Transplant.RejectData rejectData : rejectDataList) {
            datesAfterEndDate.add(rejectData.getRejectedDate());
            datesAfterEndDate.add(rejectData.getBiopsyDate());
        }

        Date failureDate = transplant.getDateFailureRejectData() != null ? transplant.
                getDateFailureRejectData().getFailureDate() : null;
        if (failureDate != null) {
            for (Date dateAfterEndDate : datesAfterEndDate) {
                if (dateAfterEndDate != null) {
                    if (failureDate.compareTo(dateAfterEndDate) <= 0) {
                        errors.add(FAILURE_DATE_ERROR);
                        break;
                    }
                }
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
                break;
            }
        }

        // cannot add new transplant whilst previous transplant has not failed
        for (Transplant existingTransplant : transplants) {
            if (existingTransplant.getId().equals(transplant.getId())) {
                continue;
            }
            if (existingTransplant.getDateFailureRejectData() != null) {
                if (existingTransplant.getDateFailureRejectData().getFailureDate() == null) {
                    errors.add(TreatmentManager.PREVIOUS_TREATMENT_NOT_CLOSED_ERROR);
                    break;
                }
            } else {
                errors.add(TreatmentManager.PREVIOUS_TREATMENT_NOT_CLOSED_ERROR);
                break;
            }
        }

        // cannot add transplant before another failure date
        for (Transplant existingTransplant : transplants) {
            if (existingTransplant.getId().equals(transplant.getId())) {
                continue;
            }
            if (existingTransplant.getDateFailureRejectData() != null) {
                if (failureDate != null) {
                    if (failureDate.compareTo(transplant.getDate()) > 0) {
                        errors.add(BEFORE_PREVIOUS_FAILURE_DATE);
                        break;
                    }

                }
            }
        }

        List<Date> datesToCheck = Arrays.asList(transplant.getDate(), transplant.getDateRecurr(),
                transplant.getDateFailureRejectData() != null ? transplant.getDateFailureRejectData().
                        getFailureDate() : null);

        // cannot be before date of birth
        Patient patient =  patientManager.getPatientByRadarNumber(transplant.getRadarNumber());
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

    public void saveRejectDataWithValidation(Transplant.RejectData rejectData) throws InvalidModelException {
        List<String> errors = new ArrayList<String>();
        // validatation

        // reject dates must be after transplant start date and before failure date
        Long transplantId = rejectData.getTransplantId();
        Transplant transplant = transplantDao.getTransplant(transplantId);
        List<Date> rejectDates = Arrays.asList(rejectData.getBiopsyDate(), rejectData.getRejectedDate());

        for (Date rejectDate : rejectDates) {
            if (rejectDate != null) {
                Date startDate = transplant.getDate();
                Date failureDate = transplant.getDateFailureRejectData() != null ?
                        transplant.getDateFailureRejectData().getFailureDate() : null;
                if (startDate != null) {
                    if (startDate.compareTo(rejectDate) >= 0) {
                        errors.add(TransplantManager.REJECT_DATA_ERROR_MESSAGE);
                        break;
                    }
                }
                if (failureDate != null) {
                    if (failureDate.compareTo(rejectDate) <= 0) {
                        errors.add(TransplantManager.REJECT_DATA_ERROR_MESSAGE);
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

    public void setPatientManager(PatientManager patientManager) {
        this.patientManager = patientManager;
    }
}
