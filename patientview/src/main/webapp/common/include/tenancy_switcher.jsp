<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ page import="com.worthsoln.patientview.model.User" %>
<%@ page import="com.worthsoln.patientview.model.TenancyUserRole" %>
<%@ page import="java.util.List" %>
<%@ page import="com.worthsoln.patientview.model.Tenancy" %>
<!-- the tenancy switcher -->
<%
    if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {

        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        List<TenancyUserRole> tenancyUserRoles = LegacySpringUtils.getUserManager().getTenancyUserRoles(user);

        if (tenancyUserRoles.size() > 1) {

%>
<td class="infostrip">Change speciality:
    <%
        Tenancy tenancy = LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy();

        for (TenancyUserRole tenancyUserRole : tenancyUserRoles) {
            if (!tenancyUserRole.getTenancy().equals(tenancy)) {
    %>
    <a href="/launchpad-select.do?tenancyId=<%=tenancyUserRole.getTenancy().getId()%>"><%=tenancyUserRole.getTenancy().getName()%></a>
    <%
            }
        }
    %>
</td>
<%
        }
    }
%>