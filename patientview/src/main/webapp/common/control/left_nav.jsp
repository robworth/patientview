<%@ page import="com.worthsoln.patientview.model.User" %>
<%@ page import="com.worthsoln.patientview.user.UserUtils" %>
<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
            <logic:present specialty="renal">
                <li class="divider"></li>
                <li><html:link action="/control/feedbackUnitSelect">Feedback</html:link></li>
                <li class="divider"></li>
            </logic:present>
            <logic:present specialty="renal">
                <li><html:link action="/control/sharingThoughtsAdminHome">Sharing Thoughts</html:link></li>
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
            <li class="divider"></li>
            <li><html:link href="/forums/list.page">Forum</html:link></li>

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