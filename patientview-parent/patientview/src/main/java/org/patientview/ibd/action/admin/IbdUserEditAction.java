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

package org.patientview.ibd.action.admin;

import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.MyIbdSeverityLevel;
import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.Severity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IbdUserEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        /**
         * This is a little hack so instead of having an form setup action and a update action
         *
         * If the request comes in and the form has not actually been submitted can assume its just
         * to set up the form
         *
         * If the request form has the submitted value set to true then treat it as an update
         */
        if (!isFormSubmitted((DynaActionForm) form)) {
            return setupForm(mapping, dynaForm, request);
        } else {
            return handleFormSubmission(mapping, dynaForm, request);
        }
    }

    private ActionForward setupForm(ActionMapping mapping, DynaActionForm dynaForm,
                                    HttpServletRequest request) {
        // always want an id so if this doesnt exist just send them back to user page
        String nhsNo = (String) dynaForm.get(Ibd.NHS_NO_PARAM);

        if (nhsNo == null || nhsNo.length() <= 0) {
            return mapping.findForward(SUCCESS);
        }

        MyIbd myIbd = getIbdManager().getMyIbd(nhsNo);

        if (myIbd != null) {
            request.setAttribute(Ibd.MY_IBD_PARAM, myIbd);

            Diagnosis diagnosis = myIbd.getDiagnosis();

            MyIbdSeverityLevel myIbdSevereLevel = getIbdManager().getMyIbdSeverityLevel(nhsNo, Severity.SEVERE);
            dynaForm.set(Ibd.SEVERE_LEVEL_PARAM, myIbdSevereLevel.getLevel(diagnosis));
            dynaForm.set(Ibd.SEVERE_TREATMENT_PARAM, myIbdSevereLevel.getTreatment());

            MyIbdSeverityLevel myIbdModerateLevel = getIbdManager().getMyIbdSeverityLevel(nhsNo, Severity.MODERATE);
            dynaForm.set(Ibd.MODERATE_LEVEL_PARAM, myIbdModerateLevel.getLevel(diagnosis));
            dynaForm.set(Ibd.MODERATE_TREATMENT_PARAM, myIbdModerateLevel.getTreatment());

            MyIbdSeverityLevel myIbdMildLevel = getIbdManager().getMyIbdSeverityLevel(nhsNo, Severity.MILD);
            dynaForm.set(Ibd.MILD_LEVEL_PARAM, myIbdMildLevel.getLevel(diagnosis));
            dynaForm.set(Ibd.MILD_TREATMENT_PARAM, myIbdMildLevel.getTreatment());

            request.setAttribute(Ibd.DIAGNOSIS_ID_PARAM, myIbd.getDiagnosis().getId());
        }

        return mapping.findForward(INPUT);
    }

    /**
     * This will handle the actual medicine stop form submission
     * @param mapping ActionMapping
     * @param dynaForm DynaActionForm
     * @param request HttpServletRequest
     * @return ActionForward
     */
    private ActionForward handleFormSubmission(ActionMapping mapping, DynaActionForm dynaForm,
                                               HttpServletRequest request) {
        // always want an id so if this doesnt exist just send them back to user page
        String nhsNo = (String) dynaForm.get(Ibd.NHS_NO_PARAM);

        if (nhsNo == null || nhsNo.length() <= 0) {
            return mapping.findForward(SUCCESS);
        }

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        List<MyIbdSeverityLevel> myIbdSeverityLevels = new ArrayList<MyIbdSeverityLevel>();

        myIbdSeverityLevels.add(new MyIbdSeverityLevel(nhsNo, Severity.SEVERE,
                (Integer) dynaForm.get(Ibd.SEVERE_LEVEL_PARAM), (String) dynaForm.get(Ibd.SEVERE_TREATMENT_PARAM)));

        myIbdSeverityLevels.add(new MyIbdSeverityLevel(nhsNo, Severity.MODERATE,
                (Integer) dynaForm.get(Ibd.MODERATE_LEVEL_PARAM), (String) dynaForm.get(Ibd.MODERATE_TREATMENT_PARAM)));

        myIbdSeverityLevels.add(new MyIbdSeverityLevel(nhsNo, Severity.MILD,
                (Integer) dynaForm.get(Ibd.MILD_LEVEL_PARAM), (String) dynaForm.get(Ibd.MILD_TREATMENT_PARAM)));

        getIbdManager().saveMyIbdSeverityLevels(myIbdSeverityLevels);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        boolean foundError = false;

        if (form.get(Ibd.SEVERE_LEVEL_PARAM) != null) {
            if (!isMultipleOfTwo((Integer) form.get(Ibd.SEVERE_LEVEL_PARAM))) {
                foundError = true;
            }
        }

        if (form.get(Ibd.MODERATE_LEVEL_PARAM) != null) {
            if (!isMultipleOfTwo((Integer) form.get(Ibd.MODERATE_LEVEL_PARAM))) {
                foundError = true;
            }
        }

        if (form.get(Ibd.MILD_LEVEL_PARAM) != null) {
            if (!isMultipleOfTwo((Integer) form.get(Ibd.MILD_LEVEL_PARAM))) {
                foundError = true;
            }
        }

        if (foundError) {
            actionErrors.add(Ibd.SEVERE_LEVEL_PARAM, new ActionMessage(Ibd.VALUES_MUST_BE_MULTIPLE_OF_TWO));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }

    private boolean isMultipleOfTwo(Integer i) {
        return (i % 2 == 0);
    }
}
