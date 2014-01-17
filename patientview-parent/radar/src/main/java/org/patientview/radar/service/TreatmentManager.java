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

package org.patientview.radar.service;

import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.Arrays;
import java.util.List;

public interface TreatmentManager {

    public static final String PREVIOUS_TREATMENT_NOT_CLOSED_ERROR = "Cannot start a new treatment after a previous " +
            "treatment start date which has not been closed";
    public static final String OVERLAPPING_ERROR = "Cannot add treatment overlapping with a previous entry";
    public static final String BEFORE_DOB_ERROR = "treatment dates cannot precede date of birth";
    public static final String AFTER_TODAY_ERROR = "treatment dates cannot be after today";
    public static final List<String> ERROR_MESSAGES = Arrays.asList(PREVIOUS_TREATMENT_NOT_CLOSED_ERROR,
            OVERLAPPING_ERROR, BEFORE_DOB_ERROR, AFTER_TODAY_ERROR);

    void saveTreatment(Treatment treatment) throws InvalidModelException;

    void deleteTreatment(Treatment treatment);

    Treatment getTreatment(long id);

    List<Treatment> getTreatmentsByRadarNumber(long radarNumber);

    TreatmentModality getTreatmentModality(long id);

    List<TreatmentModality> getTreatmentModalities();

}
