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

package org.patientview.patientview.group;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.patientview.model.Specialty;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupUpdateAction extends Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupUpdateAction.class);

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            Unit unit = LegacySpringUtils.getUnitManager().get(BeanUtils.getProperty(form, "unitcode"));
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();
            UnitUtils.buildUnit(unit, form, specialty);
            LegacySpringUtils.getUnitManager().save(unit);
            request.setAttribute("unit", unit);
            request.setAttribute("succeedMsg", "Updated the group successfully.");
        } catch (Exception e) {
            LOGGER.error("Could not update group: ", e);
            request.setAttribute("failureMsg", "Could not update the group.");
        }

        return LogonUtils.logonChecks(mapping, request);
    }

}
