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

/**
 *  Base Controller,supplies the forward/redirect method.All spring controller should extend this.
 */
public final class Routes {

    public static final String JOIN_REQUEST_LIST_URL = "/control/joinRequestList";
    public static final String JOIN_REQUEST_EDIT_URL = "/control/joinRequestEdit";
    public static final String JOIN_REQUEST_EDIT_INPUT_URL = "/control/joinRequestEditInput";

    public static final String LOOKING_LOCAL_HOME = "/lookinglocal/home";
    public static final String LOOKING_LOCAL_AUTH = "/lookinglocal/auth";
    public static final String LOOKING_LOCAL_ERROR = "/lookinglocal/error";
    public static final String LOOKING_LOCAL_MAIN = "/lookinglocal/main";
    public static final String LOOKING_LOCAL_DETAILS = "/lookinglocal/details";
    public static final String LOOKING_LOCAL_RESULTS_DISPLAY = "/lookinglocal/resultsDisplay";
    public static final String LOOKING_LOCAL_LETTER_DISPLAY = "/lookinglocal/letterDisplay";
    public static final String JOIN_REQUEST_LIST_PAGE = "/control/join_request_list";
    public static final String JOIN_REQUEST_EDIT_INPUT_PAGE = "/control/join_requests_edit_input";

    public static final String UNIT_PATIENTS_LIST_URL = "/control/unitPatients";
    public static final String UNIT_USERS_LIST_URL = "/control/unitUsers";
    public static final String UNIT_PATIENTS_LIST_PAGE = "/control/unit_patients";
    public static final String UNIT_USERS_LIST_PAGE = "/control/unit_users";

    public static final String EMAIL_VERIFICATION_URL = "/control/emailverification";

    public static final String DIABETES_CAREPLAN_URL = "/careplan-diabetes";
    public static final String DIABETES_CAREPLAN_UPDATE_URL = "/careplan-update";
    public static final String DIABETES_CAREPLAN_PAGE = "/diabetes/careplan/careplan-edit";

    private Routes() { }
}
