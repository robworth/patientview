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

import org.patientview.patientview.logon.LogonUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *  Base Controller,supplies the forward/redirect method.All spring controller should extend this.
 */
public class BaseController {

    protected static final String JOIN_REQUEST_LIST_URL = "/control/joinRequestList";
    protected static final String JOIN_REQUEST_EDIT_URL = "/control/joinRequestEdit";
    protected static final String JOIN_REQUEST_EDIT_INPUT_URL = "/control/joinRequestEditInput";


    protected static final String JOIN_REQUEST_LIST_PAGE = "/control/join_request_list";
    protected static final String JOIN_REQUEST_EDIT_INPUT_PAGE = "/control/join_requests_edit_input";

    /**
     * Return the forward page path.
     *
     * @param request HttpServletRequest
     * @param url the page path e.x.(/control/joinRequestList)
     * @return if user first logon, return the /control/password_change.jsp
     *          else return to the speciality url.
     */
    protected String forwardTo(HttpServletRequest request, String url) {
        return LogonUtils.logonChecks(request, url);
    }

    /**
     * Return the speciality redirect uri.
     *
     * @param url spring uri
     * @return redirect uri
     */
    protected String redirectTo(String url) {
        return "redirect:/web" + url;
    }
}
