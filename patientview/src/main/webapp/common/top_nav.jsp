<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<ul class="nav nav-pills">
    <li><html:link action="/index" styleClass="<%= ("index".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Home</html:link></li>
    <%
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {
    %>

    <li><html:link action="/patient/patient_details" styleClass="<%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Patient Details</html:link></li>

    <li><html:link action="/patient/patient_view" styleClass="<%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Patient Info</html:link></li>

    <logic:present tenancy="ibd">
        <li><html:link action="/myibd" styleClass="<%= ("ibd_myibd".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My IBD</html:link></li>
        <li><html:link action="/careplan" styleClass="<%= ("ibd_careplan".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My Care Plan</html:link></li>
    </logic:present>

    <li><html:link action="/patient/aboutme" styleClass="<%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">About Me</html:link></li>

    <li><html:link action="/patient/patient_entry" styleClass="<%= ("patient_entry".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Enter My...</html:link></li>

    <li><html:link action="/patient/results" styleClass="<%= ("results".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Results</html:link></li>

    <li><html:link action="/patient/medicines" styleClass="<%= ("medicines".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Medicines</html:link></li>

    <li><html:link action="/patient/letters" styleClass="<%= ("letters".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Letters</html:link></li>

    <li><html:link action="/patient/contact" styleClass="<%= ("contact".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Contact</html:link></li>

    <li><html:link href="/forums/list.page" styleClass="<%= ("xxxxxxx".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Forum</html:link></li>

    <%
        }
    %>
    <li><html:link action="/help" styleClass="<%= ("help".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Help</html:link></li>
</ul>
