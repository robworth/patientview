<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="span3">
    <div class="well noSidePadding">
        <ul class="nav nav-list">
        <logic:present tenancy="ibd">
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
                <logic:present tenancy="rpv">Renal Units</logic:present>
                <logic:present tenancy="ibd">IBD Units</logic:present>
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
            <logic:present tenancy="rpv">
                <li class="divider"></li>
                <li><html:link action="/control/feedbackUnitSelect">Feedback</html:link></li>
                <li class="divider"></li>
            </logic:present>
            <logic:present tenancy="rpv">
                <li><html:link action="/patient/patient_details">News</html:link></li>
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
        </ul>
    </div>
</div>