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

package org.patientview.service;

import org.patientview.model.Patient;
import org.patientview.patientview.PatientDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface PatientManager {

    Patient get(Long id);

    Patient get(String nhsno, String unitcode);

    Patient getPatient(String username);

    Patient getRadarPatient(String nhsNo);

    void save(Patient patient);

    void delete(String nhsno, String unitcode);

    void removePatientFromSystem(String nhsno, String unitcode);

    List<Patient> get(String unitCode);

    List<Patient> getByNhsNo(String nhsNo);

    // Note: generics not used as the result is half user, half patient
    List getUnitPatientsWithTreatment(String unitcode, String nhsno, String firstname, String lastname,
                                      boolean showgps);

    @Secured(value = { "ROLE_RENAL_SUPERADMIN" })
    List getAllUnitPatientsWithTreatment(String nhsno, String firstname, String lastname, boolean showgps);

    // Note: generics not used as the result is half user, half patient
    List getUnitPatientsAllWithTreatmentDao(String unitcode);

    List<Patient> getUktPatients();

    /**
     * Get all patient records that are associated with this user
     * @param username of user
     * @return a list of 'mini' objects based on patient records
     */
    List<PatientDetails> getPatientDetails(String username);

    List<PatientDetails> getPatientDetails(Long id);

    Map.Entry<String, Date> getLatestTestResultUnit(String nhsNo);


}
