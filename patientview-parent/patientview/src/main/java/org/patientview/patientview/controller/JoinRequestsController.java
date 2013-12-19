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

import org.apache.commons.lang.StringUtils;
import org.patientview.patientview.model.JoinRequest;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Join Requests controller, mapping the list, input and edit request URIs.
 */
@Controller
public class JoinRequestsController extends BaseController {

    @Value("${join.request.page.size}")
    private int pageSize;

    /**
     * Deal with the URIs "/control/joinRequestList"
     * get the join requests list(paging and sorting)
     */
    @RequestMapping(value = Routes.JOIN_REQUEST_LIST_URL)
     public String joinRequestList(HttpServletRequest request,
                                   @RequestParam(value = "page", required = false) String page) {
        PagedListHolder pagedListHolder;

        if (page == null || "".equals(page)) {
            pagedListHolder = getPageListData(false);
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
            } else if ("sort".equals(page)) {
                String property = (String) request.getParameter("property");
                MutableSortDefinition newSort = new MutableSortDefinition(property, true, false);
                SortDefinition sort =  pagedListHolder.getSort();
                if (StringUtils.equals(sort.getProperty(), property)) {
                    newSort.setAscending(!sort.isAscending());
                }
                pagedListHolder.setSort(newSort);
                pagedListHolder.resort();
            }
        }
        pagedListHolder.setPageSize(pageSize);
        request.getSession().setAttribute("joinRequests", pagedListHolder);
        request.setAttribute("specialty", getSpecialtyContext());

        if (pagedListHolder.isFirstPage()) {
            request.setAttribute("firstPage", true);
        }

        if (pagedListHolder.isLastPage()) {
            request.setAttribute("lastPage", true);
        }
        List<JoinRequest> joinRequestList = LegacySpringUtils.getJoinRequestManager().getUsersJoinRequests(false);
        if (joinRequestList != null && joinRequestList.size() > 0) {
            request.setAttribute("inCompletedNumber", joinRequestList.size());
        }
        return forwardTo(request, Routes.JOIN_REQUEST_LIST_PAGE);
    }

    /**
     * Deal with the URIs "/control/joinRequestEditInput"
     * get the join request entity with specialty id
     */
    @RequestMapping(value = Routes.JOIN_REQUEST_EDIT_INPUT_URL)
    public String joinRequestEditInput(HttpServletRequest request, @RequestParam(value = "id") Long id) {

        JoinRequest joinRequest = LegacySpringUtils.getJoinRequestManager().get(id);

        request.setAttribute("joinRequest", joinRequest);
        request.setAttribute("specialty", getSpecialtyContext());

        return forwardTo(request, Routes.JOIN_REQUEST_EDIT_INPUT_PAGE);
    }

    /**
     * Deal with the URIs "/control/joinRequestEdit"
     * update the join request entity
     */
    @RequestMapping(value = Routes.JOIN_REQUEST_EDIT_URL)
    public String joinRequestEdit(HttpServletRequest request, @RequestParam(value = "id") Long id,
                                  @RequestParam(value = "isComplete", required = false) String complete,
                                  @RequestParam(value = "notes", required = false) String notes) {

        boolean isComplete = "true".equals(complete);

        JoinRequest joinRequest = LegacySpringUtils.getJoinRequestManager().get(id);
        joinRequest.setNotes(notes);
        joinRequest.setComplete(isComplete);
        LegacySpringUtils.getJoinRequestManager().save(joinRequest);

        return redirectTo(Routes.JOIN_REQUEST_LIST_URL);
    }

    /**
     * Get the join requests list
     * @param isCompleted if null, get all data.
     * @return join requests list
     */
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
