<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ page import="org.patientview.ibd.model.enums.Diagnosis" %>
<%@ page import="org.patientview.patientview.user.UserUtils" %>
<%@ page import="org.patientview.patientview.model.User" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
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

<ul class="nav nav-pills">
    <li <%=("index".equals(request.getAttribute("currentNav"))) ? "class\"active\"" : "" %>><html:link action="/index">Home</html:link></li>
    <%
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty()) {
    %>

    <logic:present specialty="renal">
        <li <%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : ""%>><html:link action="/patient/patient_details">My Details</html:link></li>
    </logic:present>
    <logic:present specialty="ibd">
        <li <%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : ""%>><html:link action="/ibd-patient_details">My Details</html:link></li>
    </logic:present>

    <logic:present specialty="renal">
        <li <%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/patient_view">Patient Info</html:link></li>
    </logic:present>

    <logic:present specialty="ibd">
        <li <%=("ibd_myibd".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/myibd">My IBD</html:link></li>

        <%
        String symptomsUrl = null;

        Diagnosis loggedInUserDiagnosis = LegacySpringUtils.getIbdManager().getLoggedInUserDiagnosis();

        // check, we could be viewing as a superadmin at a patient's account
        if (loggedInUserDiagnosis == null) {
            loggedInUserDiagnosis = LegacySpringUtils.getIbdManager().getDiagnosis(UserUtils.retrieveUser(request));
        }

        if (Diagnosis.COLITIS_UNSPECIFIED == loggedInUserDiagnosis ||
                Diagnosis.ULCERATIVE_COLITIS == loggedInUserDiagnosis) {
            symptomsUrl = "/colitis-edit";
        } else if (Diagnosis.CROHNS == loggedInUserDiagnosis) {
            symptomsUrl = "/crohns-edit";
        }

        if (symptomsUrl != null && symptomsUrl.length() > 0) {
        %>
            <li <%=("ibd_entersymptoms".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="<%=symptomsUrl%>">Enter Symptoms</html:link></li>
        <%
        }
        %>
        <li <%=("ibd_medications".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/medications">Medicines</html:link></li>
        <li <%=("ibd_careplan".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/careplan">Care Plan</html:link></li>

        <li <%=("ibd_nutrition".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/nutrition">Nutrition</html:link></li>
        <li <%=("diagnostics".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/diagnostics">Diagnostics</html:link></li>
    </logic:present>
    <logic:present specialty="renal">
        <li <%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/aboutme">About Me</html:link></li>
        <li <%=("patient_entry".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/patient_entry">Enter My Own Results</html:link></li>
        <li <%=("medicines".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/medicines">Medicines</html:link></li>
    </logic:present>

    <li <%=("results".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/results">Results</html:link></li>

    <li <%=("letters".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/letters" >Letters</html:link></li>

    <logic:present feature="messaging">
        <%
            // need to get the number of unread messages if they have any
            User user = UserUtils.retrieveUser(request);

            if (user != null) {
                int numberUnreadMessages = LegacySpringUtils.getMessageManager().getTotalNumberUnreadMessages(user.getId());
        %>

        <li <%= ("conversations".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>>
            <a href="/patient/conversations.do">
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

    <logic:present specialty="renal">
        <li <%=("contact".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/contact">Contact</html:link></li>
    </logic:present>
    <logic:present specialty="ibd">
        <li <%=("contact".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/ibd-contact">Contact</html:link></li>
    </logic:present>


    <logic:present specialty="ibd">
        <li <%= ("education".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/education">Information</html:link></li>
    </logic:present>
    <%
        } else {
    %>
        <li <%=("index".equals(request.getAttribute("currentNav"))) ? "class\"active\"" : "" %>><html:link action="/join">I Want To Join</html:link></li>
    <%
        }
    %>

    <li><html:link action="/help" styleClass="<%= ("help".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Help?</html:link></li>

    <%
        if (!LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty()) {
    %>

    <li class="pull-right"><html:link action="/forgotten-password" styleClass="<%= ("forgotten-password".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Forgotten password?</html:link></li>

    <%
        }
    %>

</ul>
