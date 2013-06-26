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

package org.patientview.ibd.action.mymedications;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.ibd.model.medication.MyMedication;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class MyMedicationStopAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

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
            // always want an id so if this doesnt exist send them to meds page
            Long id = (Long) dynaForm.get(Ibd.ID_PARAM);

            if (id == null || id <= 0) {
                return mapping.findForward(ERROR);
            }

            request.getSession().setAttribute(Ibd.MY_MEDICATION_PARAM, getIbdManager().getMyMedication(id));
            return mapping.findForward(INPUT);
        } else {
            return handleFormSubmission(mapping, dynaForm, request);
        }
    }

    /**
     * This will handle the actual medicine stop form submission
     *
     * @param mapping  ActionMapping
     * @param dynaForm DynaActionForm
     * @param request  HttpServletRequest
     * @return ActionForward
     */
    private ActionForward handleFormSubmission(ActionMapping mapping, DynaActionForm dynaForm,
                                               HttpServletRequest request) {
        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        MyMedication myMedication = getIbdManager().getMyMedication((Long) dynaForm.get(Ibd.ID_PARAM));
        myMedication.setDateStopped(new Date());
        myMedication.setReasonForStopping((String) dynaForm.get(Ibd.REASON_FOR_STOPPING_PARAM));

        getIbdManager().saveMyMedication(myMedication);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // make sure we have a valid if and the medication has a record to update
        Long id = (Long) form.get(Ibd.ID_PARAM);

        if (id == null || id <= 0) {
            actionErrors.add(Ibd.ID_PARAM, new ActionMessage(Ibd.NOT_A_VALID_ID));
        } else {
            MyMedication myMedication = getIbdManager().getMyMedication(id);

            if (myMedication == null || !myMedication.hasValidId()) {
                actionErrors.add(Ibd.ID_PARAM, new ActionMessage(Ibd.MEDICATION_NOT_FOUND_TO_UPDATE));
            }
        }

        if (form.get(Ibd.REASON_FOR_STOPPING_PARAM) == null
                || ((String) form.get(Ibd.REASON_FOR_STOPPING_PARAM)).length() == 0) {
            actionErrors.add(Ibd.REASON_FOR_STOPPING_PARAM, new ActionMessage(Ibd.REASON_FOR_STOPPING_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
