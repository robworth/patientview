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

package org.patientview.patientview.controller;

import org.patientview.patientview.medicine.MedicineWithShortName;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MedicineController extends BaseController {

    @RequestMapping(value = Routes.API_MEDICINE_URL)
    @ResponseBody
    public List<MedicineWithShortName> getMedicines(@RequestParam(value = "nhsno", required = true) String nhsno)
            throws Exception {
        List<MedicineWithShortName> medicines = getMedicinesForPatient(nhsno);
        sortNullDatesOnMedicines(medicines);

        return medicines;
    }

    private List<MedicineWithShortName> getMedicinesForPatient(String nhsno) throws Exception {
        List<MedicineWithShortName> medicinesWithShortName = new ArrayList<MedicineWithShortName>();
        List<Medicine> medicines = LegacySpringUtils.getMedicineManager().getUserMedicines(nhsno);
        if (medicines == null) {
            return medicinesWithShortName;
        }
        for (Medicine med : medicines) {
            Unit unit = UnitUtils.retrieveUnit(med.getUnitcode());
            if (unit != null) {
                medicinesWithShortName.add(new MedicineWithShortName(med, unit.getShortname()));
            } else {
                medicinesWithShortName.add(new MedicineWithShortName(med, "UNKNOWN UNIT:" + med.getUnitcode()));
            }
        }

        return medicinesWithShortName;
    }

    private List<MedicineWithShortName> sortNullDatesOnMedicines(List<MedicineWithShortName> medicines) {
        for (Medicine medicine : medicines) {
            // todo this probably won't work anymore
            Medicine tempMed = LegacySpringUtils.getMedicineManager().get(medicine.getId());
            medicine.setStartdate(tempMed.getStartdate());
        }

        return medicines;
    }
}
