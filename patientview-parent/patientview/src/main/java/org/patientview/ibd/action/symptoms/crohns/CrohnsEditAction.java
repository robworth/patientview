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

package org.patientview.ibd.action.symptoms.crohns;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class CrohnsEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        // if these were set in the other form of the pge they will be passed through with this one
        Date fromDate = convertFormDateString(Ibd.FROM_DATE_PARAM, dynaForm);
        Date toDate = convertFormDateString(Ibd.TO_DATE_PARAM, dynaForm);

        request.setAttribute(Ibd.FROM_DATE_PARAM, convertFormDateString(fromDate));
        request.setAttribute(Ibd.TO_DATE_PARAM, convertFormDateString(toDate));

        addSymptomsGraphData(user, Ibd.CROHNS_GRAPH_TYPE, fromDate, toDate, request);

        if (request.getParameter(Ibd.SHOW_ADVICE_PARAM) != null) {
            addLastSymptomAdvice(user, Ibd.CROHNS_GRAPH_TYPE, request);
        }

        // set the form to have empty values
        dynaForm.set(Ibd.ABDOMINAL_PAIN_PARAM, null);
        dynaForm.set(Ibd.OPEN_BOWELS_PARAM, null);
        dynaForm.set(Ibd.FEELING_PARAM, null);
        dynaForm.set(Ibd.COMPLICATION_PARAM, null);
        dynaForm.set(Ibd.MASS_IN_TUMMY_PARAM, null);
        dynaForm.set(Ibd.SYMPTOM_DATE_PARAM, null);

        // add the lists to the page
        request.getSession().setAttribute(ABDOMINAL_PAIN_LIST_PROPERTY, getAbdominalPainList());
        request.getSession().setAttribute(FEELING_LIST_PROPERTY, getFeelingList());
        request.getSession().setAttribute(CROHNS_COMPLICATION_LIST_PROPERTY, getCrohnsComplicationList());
        request.getSession().setAttribute(MASS_IN_TUMMY_LIST_PROPERTY, getMassInTummy());
        request.getSession().setAttribute(OPEN_BOWEL_LIST_PROPERTY, getOpenBowelList());

        // add any managed links in for this page
        addMyIbdLinks(getIbdManager().getMyIbd(UserUtils.retrieveUser(request)), request);

        return mapping.findForward(SUCCESS);
    }
}
