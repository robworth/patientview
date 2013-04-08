<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ page import="com.worthsoln.patientview.model.User" %>
<%@ page import="com.worthsoln.patientview.model.SpecialtyUserRole" %>
<%@ page import="java.util.List" %>
<%@ page import="com.worthsoln.patientview.model.Specialty" %>
<!-- the specialty switcher -->
<%
    if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty()) {

        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        List<SpecialtyUserRole> specialtyUserRoles = LegacySpringUtils.getUserManager().getSpecialtyUserRoles(user);

        if (specialtyUserRoles.size() > 1) {

%>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Change specialty<b class="caret"></b></a>
    <ul class="dropdown-menu">
         <%
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            for (SpecialtyUserRole specialtyUserRole : specialtyUserRoles) {
                if (!specialtyUserRole.getSpecialty().equals(specialty)) {
        %>
            <li><a href="/launchpad-select.do?specialtyId=<%=specialtyUserRole.getSpecialty().getId()%>"><%=specialtyUserRole.getSpecialty().getName()%></a></li>
        <%
                }
            }
        %>
    </ul>
</li>
<%
        }
    }
%>