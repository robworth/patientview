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

import org.patientview.radar.model.Transplant;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.Arrays;
import java.util.List;

public interface TransplantManager {

    // transplant data error messages
    public static final String BEFORE_PREVIOUS_FAILURE_DATE = "Cannot add transplant before a previous transplant "
            + "failure date";
    public static final String TRANSPLANTS_INTERVAL_ERROR = "Transplants must be greater than 14 days apart";
    public static final String START_DATE_ERROR = "transplant date must be before recurrance date, failure date, " +
            "reject date and biopsy date";
    public static final String FAILURE_DATE_ERROR = "Date of failure has to be after  reccurance date, reject date " +
            "and " + "biopsy date";
    public static final List<String> ERROR_MESSAGES = Arrays.asList(BEFORE_PREVIOUS_FAILURE_DATE,
            TRANSPLANTS_INTERVAL_ERROR, START_DATE_ERROR, FAILURE_DATE_ERROR);

    // reject data error messages
    public static final String REJECT_DATA_ERROR_MESSAGE = "Reject dates have to be after transplant start date and " +
            "before failure date";
    public static final List<String> REJECT_DATA_ERROR_MESSAGES = Arrays.asList(REJECT_DATA_ERROR_MESSAGE);

    void saveTransplant(Transplant transplant) throws InvalidModelException;

    void deleteTransplant(Transplant transplant);

    Transplant getTransplant(long id);

    List<Transplant> getTransplantsByRadarNumber(long radarNumber);

    List<Transplant.Modality> getTransplantModalitites();

    Transplant.Modality getTransplantModality(long id);

    void saveRejectData(Transplant.RejectData rejectData);

    void saveRejectDataWithValidation(Transplant.RejectData rejectData) throws InvalidModelException;

    void deleteRejectData(Transplant.RejectData rejectData);

    List<Transplant.RejectData> getRejectDataByTransplantNumber(Long transplantNumber);

    Transplant.RejectData getRejectData(Long id);

}
