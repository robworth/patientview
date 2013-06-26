<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.patientview.radar.util.UserUpgradeManager" %><%
    UserUpgradeManager userUpgradeManager = (UserUpgradeManager) WebApplicationContextUtils
            .getWebApplicationContext(session.getServletContext()).getBean("userUpgradeManager");

    userUpgradeManager.run();
%>