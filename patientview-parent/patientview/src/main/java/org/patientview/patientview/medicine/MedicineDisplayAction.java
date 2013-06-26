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

package org.patientview.patientview.medicine;

import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.User;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MedicineDisplayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        User user = UserUtils.retrieveUser(request);
        List medicines = getMedicinesForPatient(user, request);
        sortNullDatesOnMedicines(medicines);

        request.setAttribute("medicines", medicines);
        request.setAttribute("user", user);

        return LogonUtils.logonChecks(mapping, request);
    }

    private List getMedicinesForPatient(User user, HttpServletRequest request) throws Exception {
        List<MedicineWithShortName> medicinesWithShortName = new ArrayList<MedicineWithShortName>();
        if (user != null) {
            List<Medicine> medicines = LegacySpringUtils.getMedicineManager().getUserMedicines(user);

            for (Medicine med : medicines) {
                Unit unit = UnitUtils.retrieveUnit(med.getUnitcode());
                if (unit != null) {
                    medicinesWithShortName.add(new MedicineWithShortName(med, unit.getShortname()));
                } else {
                    medicinesWithShortName.add(new MedicineWithShortName(med, "UNKNOWN UNIT:" + med.getUnitcode()));
                }
            }
        }

        return medicinesWithShortName;
    }

    private List sortNullDatesOnMedicines(List medicines) {
        for (Object obj : medicines) {
            Medicine medicine = (Medicine) obj;

            // todo this probably won't work anymore
            Medicine tempMed = LegacySpringUtils.getMedicineManager().get(medicine.getId());
            medicine.setStartdate(tempMed.getStartdate());
        }

        return medicines;
    }

}
