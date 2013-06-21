<%@ page import="org.patientview.patientview.model.User" %>
<%@ page import="org.patientview.patientview.user.UserUtils" %>
<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

<div class="span3">
    <div class="well noSidePadding">
        <ul class="nav nav-list">
        <logic:present specialty="ibd">
            <li><html:link action="/control/myibd-links-display">My IBD Links</html:link></li>
            <li><html:link action="/control/careplan-links-display">Care plan Links</html:link></li>
            <li class="divider"></li>
        </logic:present>
        <logic:present role="superadmin">
            <li><html:link action="/control/edtaCodeDisplay">EDTA Codes</html:link></li>
            <li><html:link action="/control/treatmentCodeDisplay">Treatment Codes</html:link></li>
            <li><html:link action="/control/staticLinkEdit">Static Links</html:link></li>
            <li><html:link action="/control/resultHeadingDisplay">Result Headings</html:link></li>
            <li class="divider"></li>
        </logic:present>
        <logic:present role="superadmin,unitadmin">
            <li><html:link action="/control/unitDisplay">
                <logic:present specialty="renal">Renal Units</logic:present>
                <logic:present specialty="ibd">IBD Units</logic:present>
            </html:link></li>
            <li class="divider"></li>
            <li><html:link action="/control/unitAdminAddInput">Add Unit User</html:link></li>
            <li><html:link action="/control/unitUsersUnitSelect">Users In Unit</html:link></li>
            <li class="divider"></li>
        </logic:present>
          <li><html:link action="/control/unitPatientsUnitSelect">Patients In Unit</html:link></li>
        <logic:present role="superadmin,unitadmin">
            <li><html:link action="/control/patientAddInput">Add Patient</html:link></li>
            <li><html:link action="/control/logView">View Log</html:link></li>
            <li class="divider"></li>
            <li><html:link action="/control/joinReqestList">Join Requests</html:link></li>
            <logic:present specialty="renal">
                <li class="divider"></li>
                <li><html:link action="/control/feedbackUnitSelect">Feedback</html:link></li>
                <li class="divider"></li>
            </logic:present>
            <logic:present specialty="renal">
                <li><html:link action="/control/newsView">News</html:link></li>
                <li><html:link action="/control/newsList">News Edit</html:link></li>
                <logic:present role="superadmin,unitadmin">
                    <li class="divider"></li>
                    <li><html:link action="/control/splashPageList">Splash Pages</html:link></li>
                </logic:present>
                <logic:present role="superadmin">
                    <li class="divider"></li>
                    <li><html:link action="/control/version">RPV Version</html:link></li>
                </logic:present>
            </logic:present>
        </logic:present>

            <logic:present feature="messaging">
            <%
            // need to get the number of unread messages if they have any
            User user = UserUtils.retrieveUser(request);

            if (user != null) {
            int numberUnreadMessages = LegacySpringUtils.getMessageManager().getTotalNumberUnreadMessages(user.getId());
            %>
            <li class="divider"></li>
            <li <%= ("conversations".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>>
                <a href="/control/conversations.do">
                    Messages
                    <%
                        if (numberUnreadMessages > 0) {
                    %>
                    <span class="badge badge-important"><%=numberUnreadMessages%></span>
                    <%
                        }
                    %>
                </a>
            </li>
            <%
                }
            %>
            </logic:present>

        </ul>
    </div>
</div>
