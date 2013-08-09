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

import org.apache.commons.lang.StringUtils;
import org.patientview.patientview.model.Unit;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
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
        String page = request.getParameter("page");
        PagedListHolder pagedListHolder =  (PagedListHolder) request.getSession().getAttribute("patients");

        if (StringUtils.isEmpty(page) || pagedListHolder == null) {
            String nhsno = (String) request.getAttribute("nhsno");
            nhsno = (nhsno == null) ? "" : nhsno;
            String name = (String) request.getAttribute("name");
            name = (name == null) ? "" : name;
            boolean showgps = "true".equals((String) request.getAttribute("showgps"));
            List patients =
                    LegacySpringUtils.getPatientManager().getUnitPatientsWithTreatment(unitcode, nhsno, name, showgps);
            pagedListHolder = new PagedListHolder(patients);
            request.getSession().setAttribute("patients", pagedListHolder);
        } else {
            if ("prev".equals(page)) {
                pagedListHolder.previousPage();
            } else if ("next".equals(page)) {
                pagedListHolder.nextPage();
            } else if ("sort".equals(page)) {
                String property = (String) request.getParameter("property");
                MutableSortDefinition newSort = new MutableSortDefinition(property, true, false);
                SortDefinition sort =  pagedListHolder.getSort();
                if (StringUtils.equals(sort.getProperty(), property)) {
                    newSort.setAscending(!sort.isAscending());
                }
                pagedListHolder.setSort(newSort);
                pagedListHolder.resort();
            }
        }

        if (!"".equals(unitcode)) {
            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            request.setAttribute("unit", unit);
        }

        return forwardTo(request, UNIT_PATIENTS_LIST);
    }

}
