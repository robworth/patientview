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

package org.patientview.ibd.action.nutrition;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.ibd.model.Nutrition;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NutritionUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        Nutrition nutrition = new Nutrition();
        nutrition.setNhsno(getNhsNoForUser(request));
        nutrition.setFoodsThatDisagree((String) dynaForm.get(Ibd.FOODS_THAT_DISAGREE_PARAM));
        nutrition.setComment((String) dynaForm.get(Ibd.COMMENT_PARAM));
        nutrition.setNutritionDate(convertFormDateString(Ibd.NUTRITION_DATE_PARAM, dynaForm));

        getIbdManager().saveNutrition(nutrition);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        if (form.get(Ibd.NUTRITION_DATE_PARAM) == null || ((String) form.get(Ibd.NUTRITION_DATE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.NUTRITION_DATE_PARAM, new ActionMessage(Ibd.DATE_REQUIRED));
        }

        if (form.get(Ibd.FOODS_THAT_DISAGREE_PARAM) == null
                || ((String) form.get(Ibd.FOODS_THAT_DISAGREE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.FOODS_THAT_DISAGREE_PARAM, new ActionMessage(Ibd.FOODS_THAT_DISAGREE_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
