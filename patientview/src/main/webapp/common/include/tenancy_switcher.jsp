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
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Change specialty<b class="caret"></b></a>
    <ul class="dropdown-menu">
         <%
            Tenancy tenancy = LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy();

            for (TenancyUserRole tenancyUserRole : tenancyUserRoles) {
                if (!tenancyUserRole.getTenancy().equals(tenancy)) {
        %>
            <li><a href="/launchpad-select.do?tenancyId=<%=tenancyUserRole.getTenancy().getId()%>"><%=tenancyUserRole.getTenancy().getName()%></a></li>
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