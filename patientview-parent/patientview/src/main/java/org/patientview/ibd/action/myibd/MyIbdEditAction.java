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
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIbdEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        MyIbd myIbd = getIbdManager().getMyIbd(UserUtils.retrieveUser(request));

        if (myIbd == null) {
            myIbd = new MyIbd();
        }

        DynaActionForm dynaForm = (DynaActionForm) form;
        dynaForm.set(Ibd.DIAGNOSIS_ID_PARAM, myIbd.getDiagnosisId());
        dynaForm.set(Ibd.DISEASE_EXTENT_ID_PARAM, myIbd.getDiseaseExtentId());
        dynaForm.set(Ibd.YEAR_OF_DIAGNOSIS_PARAM, convertFormDateString(myIbd.getYearOfDiagnosis()));
        dynaForm.set(Ibd.BODY_PART_AFFECTED_PARAM, myIbd.getBodyPartAffected());
        dynaForm.set(Ibd.YEAR_FOR_SURVEILLANCE_COLONOSCOPY_PARAM, convertFormDateString(myIbd
                .getYearForSurveillanceColonoscopy()));
        dynaForm.set(Ibd.NAMED_CONSULTANT_PARAM, myIbd.getNamedConsultant());
        dynaForm.set(Ibd.NURSES_PARAM, myIbd.getNurses());
        dynaForm.set(Ibd.FAMILY_HISTORY_PARAM, myIbd.getFamilyHistory());
        dynaForm.set(Ibd.SMOKING_PARAM, myIbd.getSmoking());
        dynaForm.set(Ibd.SURGERY_PARAM, myIbd.getSurgery());
        dynaForm.set(Ibd.VACCINATION_RECORD_PARAM, myIbd.getVaccinationRecord());
        dynaForm.set(Ibd.COMPLICATIONS_PARAM, myIbd.getComplications());

        // set up all the needed lists in the session
        request.getSession().setAttribute(DISEASE_EXTENT_LIST_PROPERTY, getDiseaseExtentList());
        request.getSession().setAttribute(DIAGNOSIS_LIST_PROPERTY, getDiagnosisList());
        request.getSession().setAttribute(COMPLICATION_LIST_PROPERTY, getComplicationList());
        request.getSession().setAttribute(FAMILY_HISTORY_LIST_PROPERTY, getFamilyHistoryList());
        request.getSession().setAttribute(SMOKING_LIST_PROPERTY, getSmokingList());
        request.getSession().setAttribute(SURGERY_LIST_PROPERTY, getSurgeryList());
        request.getSession().setAttribute(BODY_PART_AFFECTED_LIST_PROPERTY, getBodyPartAffectedList());
        request.getSession().setAttribute(VACCINATION_RECORD_LIST_PROPERTY, getVaccinationRecordList());

        return mapping.findForward(SUCCESS);
    }
}
