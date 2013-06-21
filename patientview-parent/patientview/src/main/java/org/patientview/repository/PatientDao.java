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

package org.patientview.repository;

import org.patientview.patientview.logon.PatientLogonWithTreatment;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface PatientDao {

    Patient get(Long id);

    Patient get(String nhsno, String unitcode);

    void save(Patient patient);

    void delete(String nhsno, String unitcode);

    List<Patient> get(String centreCode);

    // Note: generics not used as the result is half user, half patient
    List getUnitPatientsWithTreatmentDao(String unitcode, String nhsno, String name, boolean showgps,
                                         Specialty specialty);

    // Note: generics not used as the result is half user, half patient
    List<PatientLogonWithTreatment> getUnitPatientsAllWithTreatmentDao(String unitcode, Specialty specialty);

    List<Patient> getUktPatients();
}
