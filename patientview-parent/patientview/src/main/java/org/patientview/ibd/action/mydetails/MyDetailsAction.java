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

package org.patientview.ibd.action.mydetails;

import org.patientview.ibd.Ibd;
import org.patientview.ibd.model.MyIbd;
import org.patientview.patientview.PatientDetailsAction;
import org.patientview.patientview.user.UserUtils;
import org.patientview.service.ibd.IbdManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDetailsAction extends PatientDetailsAction {

    /**
     * This is a sub class just so that we can add the myIbd stuff into the request without having to
     * write all the myDetails code and JSPs again
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        MyIbd myIbd = getIbdManager().getMyIbd(UserUtils.retrieveUser(request));

        if (myIbd != null && myIbd.getDiagnosis() != null) {
            request.getSession().setAttribute(Ibd.MY_IBD_DIAGNOSIS_PARAM, myIbd.getDiagnosis().getName());
        }

        return super.execute(mapping, form, request, response);
    }

    protected IbdManager getIbdManager() {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(
                servlet.getServletContext());

        return webApplicationContext.getBean(IbdManager.class);
    }
}
