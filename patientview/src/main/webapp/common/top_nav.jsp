<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<ul class="nav nav-pills">
    <li class="active first"><html:link action="/index" styleClass="<%= ("index".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Home</html:link></li>
    <li><html:link action="/patient/patient_details" styleClass="<%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My Details</html:link></li>
    <li><html:link action="/ibd/myibd" styleClass="<%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My IBD</html:link></li>
    <li><html:link action="/patient/aboutme" styleClass="<%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My Care Plan</html:link></li>
    <li><html:link action="/patient/patient_entry" styleClass="<%= ("patient_entry".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Enter My Symptoms</html:link></li>
    <li><a href="#">Nutrition</a></li>
    <li><html:link action="/patient/results" styleClass="<%= ("results".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Results</html:link></li>
    <li><html:link action="/patient/medicines" styleClass="<%= ("medicines".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Medicines</html:link></li>
    <li><html:link action="/patient/letters" styleClass="<%= ("letters".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Letters</html:link></li>
    <li><html:link action="/patient/contact" styleClass="<%= ("contact".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Contact</html:link></li>
    <li><a href="#">Education</a></li>
</ul>

<tr>
    <td colspan="2" bgcolor="#CFE6FC">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="navcell-empty"><font class="navlink">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>

                <td class="navcell"><html:link action="/index" styleClass="<%= ("index".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Home</html:link></td>

                <%
                    if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {
                %>

                <td class="navcell"><html:link action="/patient/patient_details" styleClass="<%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Patient Details</html:link></td>

                <td class="navcell"><html:link action="/patient/patient_view" styleClass="<%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Patient Info</html:link></td>

                <logic:present tenancy="ibd">
                    <td class="navcell"><a>My IBD</a></td>
                </logic:present>

                <td class="navcell"><html:link action="/patient/aboutme" styleClass="<%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">About Me</html:link></td>

                <td class="navcell"><html:link action="/patient/patient_entry" styleClass="<%= ("patient_entry".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Enter My...</html:link></td>

                <td class="navcell"><html:link action="/patient/results" styleClass="<%= ("results".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Results</html:link></td>

                <td class="navcell"><html:link action="/patient/medicines" styleClass="<%= ("medicines".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Medicines</html:link></td>

                <td class="navcell"><html:link action="/patient/letters" styleClass="<%= ("letters".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Letters</html:link></td>

                <td class="navcell"><html:link action="/patient/contact" styleClass="<%= ("contact".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Contact</html:link></td>

                <td class="navcell"><html:link href="/forums/list.page" styleClass="<%= ("xxxxxxx".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Forum</html:link></td>

                <%
                    }
                %>
                <td class="navcell-right"><html:link action="/help" styleClass="<%= ("help".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Help</html:link></td>

                <td class="navcell-empty"><font class="navlink">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
            </tr>
        </table>
    </td>
</tr>
