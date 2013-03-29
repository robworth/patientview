<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ page import="com.worthsoln.ibd.model.enums.Diagnosis" %>
<%@ page import="com.worthsoln.patientview.user.UserUtils" %>
<%@ page import="com.worthsoln.patientview.model.User" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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

    <logic:present specialty="renal">
        <li <%= ("sharing_thoughts".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><html:link action="/patient/sharingThoughts">Sharing Thoughts</html:link></li>
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
    <logic:present specialty="renal">
        <li <%= ("xxxxxxx".equals(request.getAttribute("currentNav"))) ? "class=\"active\"" : "" %>><a href="/forums/list.page">Forum</a></li>
    </logic:present>
    <%
        } else {
    %>
        <li <%=("index".equals(request.getAttribute("currentNav"))) ? "class\"active\"" : "" %>><html:link action="/join">I Want To Join</html:link></li>
    <%
        }
    %>

    <logic:present role="patient">
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
</ul>
