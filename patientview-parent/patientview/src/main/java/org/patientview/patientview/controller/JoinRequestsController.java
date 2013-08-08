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

import org.patientview.patientview.model.JoinRequest;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Join Requests controller, mapping the list, edit requests.
 */
@Controller
public class JoinRequestsController extends BaseController {

    @RequestMapping(value = JOIN_REQUEST_LIST_URL)
     public String joinRequestList(HttpServletRequest request) {
        PagedListHolder pagedListHolder;
        String page = request.getParameter("page");

        if (page == null || "".equals(page)) {
            pagedListHolder = getPageListData(null);
        } else {

            pagedListHolder = (PagedListHolder) request.getSession().getAttribute("joinRequests");

            if ("prev".equals(page)) {
                if (pagedListHolder != null) {
                    pagedListHolder.previousPage();
                } else {
                    pagedListHolder = getPageListData(null);
                }
            } else if ("next".equals(page)) {
                if (pagedListHolder != null) {
                    pagedListHolder.nextPage();
                } else {
                    pagedListHolder = getPageListData(null);
                }
            } else if ("all".equals(page)) {
                pagedListHolder = getPageListData(null);
            } else if ("incomplete".equals(page)) {
                pagedListHolder = getPageListData(false);
            } else if ("complete".equals(page)) {
                pagedListHolder = getPageListData(true);
            }
        }
        pagedListHolder.setPageSize(2);
        request.getSession().setAttribute("joinRequests", pagedListHolder);

        return forwardTo(request, JOIN_REQUEST_LIST_PAGE);
    }

    @RequestMapping(value = JOIN_REQUEST_EDIT_INPUT_URL)
    public String joinRequestEditInput(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));

        JoinRequest joinRequest = LegacySpringUtils.getJoinRequestManager().get(id);

        request.setAttribute("joinRequest", joinRequest);
        return forwardTo(request, JOIN_REQUEST_EDIT_INPUT_PAGE);
    }

    @RequestMapping(value = JOIN_REQUEST_EDIT_URL)
    public String joinRequestEdit(@RequestParam("id") Long id, @RequestParam("isComplete") String complete,
                                  @RequestParam("notes") String notes) {

        boolean isComplete = "true".equals(complete);

        JoinRequest joinRequest = LegacySpringUtils.getJoinRequestManager().get(id);
        joinRequest.setNotes(notes);
        joinRequest.setComplete(isComplete);
        LegacySpringUtils.getJoinRequestManager().save(joinRequest);

        return redirectTo(JOIN_REQUEST_LIST_URL);
    }

    private PagedListHolder getPageListData(Boolean isCompleted) {
        List<JoinRequest> joinRequests;
        if (isCompleted != null) {
            joinRequests = LegacySpringUtils.getJoinRequestManager().getUsersJoinRequests(isCompleted);
        } else {
            joinRequests = LegacySpringUtils.getJoinRequestManager().getUsersJoinRequests();
        }
        PagedListHolder pagedListHolder = new PagedListHolder(joinRequests);

        return pagedListHolder;
    }
}
