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

package org.patientview.ibd.action.myibd;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.ibd.model.MyIbd;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIbdUpdateAction extends BaseAction {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        User user = UserUtils.retrieveUser(request);

        MyIbd myIbd = getIbdManager().getMyIbd(user);

        // if its null then its new so set the nhs no to the user
        if (myIbd == null || !myIbd.hasValidId()) {
            myIbd = new MyIbd();
            myIbd.setNhsno(getNhsNoForUser(request));
        }

        myIbd.setDiagnosisId((Long) dynaForm.get(Ibd.DIAGNOSIS_ID_PARAM));
        myIbd.setDiseaseExtentId((Long) dynaForm.get(Ibd.DISEASE_EXTENT_ID_PARAM));
        myIbd.setYearOfDiagnosis(convertFormDateString(Ibd.YEAR_OF_DIAGNOSIS_PARAM, dynaForm));
        myIbd.setBodyPartAffected((String) dynaForm.get(Ibd.BODY_PART_AFFECTED_PARAM));
        myIbd.setYearForSurveillanceColonoscopy(convertFormDateString(Ibd.YEAR_FOR_SURVEILLANCE_COLONOSCOPY_PARAM,
                dynaForm));
        myIbd.setNamedConsultant((String) dynaForm.get(Ibd.NAMED_CONSULTANT_PARAM));
        myIbd.setNurses((String) dynaForm.get(Ibd.NURSES_PARAM));
        myIbd.setFamilyHistory((String) dynaForm.get(Ibd.FAMILY_HISTORY_PARAM));
        myIbd.setSmoking((String) dynaForm.get(Ibd.SMOKING_PARAM));
        myIbd.setSurgery((String) dynaForm.get(Ibd.SURGERY_PARAM));
        myIbd.setVaccinationRecord((String) dynaForm.get(Ibd.VACCINATION_RECORD_PARAM));
        myIbd.setComplications((String) dynaForm.get(Ibd.COMPLICATIONS_PARAM));

        getIbdManager().saveMyIbd(myIbd);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        // Comment out the ones that do not require validation

        if (form.get(Ibd.DIAGNOSIS_ID_PARAM) == null || ((Long) form.get(Ibd.DIAGNOSIS_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DIAGNOSIS_ID_PARAM, new ActionMessage(Ibd.DIAGNOSIS_REQUIRED));
        }

        if (form.get(Ibd.DISEASE_EXTENT_ID_PARAM) == null || ((Long) form.get(Ibd.DISEASE_EXTENT_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.DISEASE_EXTENT_ID_PARAM, new ActionMessage(Ibd.DISEASE_EXTENT_REQUIRED));
        }

        if (form.get(Ibd.YEAR_OF_DIAGNOSIS_PARAM) == null
                || ((String) form.get(Ibd.YEAR_OF_DIAGNOSIS_PARAM)).length() == 0) {
            actionErrors.add(Ibd.YEAR_OF_DIAGNOSIS_PARAM, new ActionMessage(Ibd.YEAR_OF_DIAGNOSIS_REQUIRED));
        }

        if (form.get(Ibd.COMPLICATIONS_PARAM) == null || form.get(Ibd.COMPLICATIONS_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.COMPLICATIONS_PARAM, new ActionMessage(Ibd.COMPLICATIONS_REQUIRED));
        }

        if (form.get(Ibd.BODY_PART_AFFECTED_PARAM) == null
                || form.get(Ibd.BODY_PART_AFFECTED_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.BODY_PART_AFFECTED_PARAM, new ActionMessage(Ibd.BODY_PART_AFFECTED_REQUIRED));
        }

        if (form.get(Ibd.FAMILY_HISTORY_PARAM) == null || form.get(Ibd.FAMILY_HISTORY_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.FAMILY_HISTORY_PARAM, new ActionMessage(Ibd.FAMILY_HISTORY_REQUIRED));
        }

        if (form.get(Ibd.SMOKING_PARAM) == null || form.get(Ibd.SMOKING_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.SMOKING_PARAM, new ActionMessage(Ibd.SMOKING_REQUIRED));
        }

        if (form.get(Ibd.SURGERY_PARAM) == null || form.get(Ibd.SURGERY_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.SURGERY_PARAM, new ActionMessage(Ibd.SURGERY_REQUIRED));
        }

        if (form.get(Ibd.VACCINATION_RECORD_PARAM) == null
                || form.get(Ibd.VACCINATION_RECORD_PARAM).toString().length() == 0) {
            actionErrors.add(Ibd.VACCINATION_RECORD_PARAM, new ActionMessage(Ibd.VACCINATION_RECORD_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
