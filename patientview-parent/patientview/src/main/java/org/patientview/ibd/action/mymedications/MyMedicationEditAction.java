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
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMedicationEditAction extends BaseAction {

    static final int MEDICATION_TYPE_ID_PARAM_MINUS_1 = -1;
    static final int MEDICATION_TYPE_ID_PARAM_MINUS_2 = -2;

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        MyMedication myMedication = new MyMedication();

        DynaActionForm dynaForm = (DynaActionForm) form;

        dynaForm.set(Ibd.DATE_STARTED_PARAM, convertFormDateString(myMedication.getDateStarted()));

        if (myMedication.getMedicationType() != null) {
            dynaForm.set(Ibd.MEDICATION_TYPE_ID_PARAM, myMedication.getMedicationType().getId());
        } else {
            dynaForm.set(Ibd.MEDICATION_TYPE_ID_PARAM, new Long(MEDICATION_TYPE_ID_PARAM_MINUS_1));
        }

        if (myMedication.getMedication() != null) {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, myMedication.getMedication().getId());
        } else if (myMedication.getOtherMedication() != null && myMedication.getOtherMedication().length() > 0) {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, new Long(MEDICATION_TYPE_ID_PARAM_MINUS_2));
        } else {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, new Long(MEDICATION_TYPE_ID_PARAM_MINUS_1));
        }

        String otherMedication = "";
        if (myMedication.getOtherMedication() != null) {
            otherMedication = myMedication.getOtherMedication();
        }

        String otherMedicationDose = "";
        if (myMedication.getOtherMedicationDose() != null) {
            otherMedicationDose = myMedication.getOtherMedicationDose();
        }

        dynaForm.set(Ibd.OTHER_MEDICATION_ID_PARAM, otherMedication);
        dynaForm.set(Ibd.OTHER_MEDICATION_DOSE_ID_PARAM, otherMedicationDose);
        dynaForm.set(Ibd.MEDICATION_FREQUENCY_ID_PARAM, myMedication.getMedicationFrequencyId());

        // set up all the needed lists in the session
        request.getSession().setAttribute(MEDICATION_TYPE_LIST_PROPERTY, getMedicationTypeList());
        request.getSession().setAttribute(MEDICATION_FREQUENCY_LIST_PROPERTY, getMedicationFrequencyList());

        return mapping.findForward(SUCCESS);
    }
}
