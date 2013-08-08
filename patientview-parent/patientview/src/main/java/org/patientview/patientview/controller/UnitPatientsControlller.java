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

import org.patientview.patientview.model.Unit;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class UnitPatientsControlller extends BaseController {

    @RequestMapping(value = "/control/unitPatients")
    public String execute(HttpServletRequest request) {

        String unitcode = (String) request.getAttribute("unitcode");
        unitcode = (unitcode == null) ? "" : unitcode;
        String nhsno = (String) request.getAttribute("nhsno");
        nhsno = (nhsno == null) ? "" : nhsno;
        String name = (String) request.getAttribute("name");
        name = (name == null) ? "" : name;
        boolean showgps = "true".equals((String) request.getAttribute("showgps"));

        if (!"".equals(unitcode)) {
            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            request.setAttribute("unit", unit);
        }

        List patients
                = LegacySpringUtils.getPatientManager().getUnitPatientsWithTreatment(unitcode, nhsno, name, showgps);

        request.setAttribute("patients", patients);

        return forwardTo(request, UNIT_PATIENTS_LIST);
    }

}
