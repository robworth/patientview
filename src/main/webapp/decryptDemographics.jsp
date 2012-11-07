<!-- Please see @com.solidstategroup.radar.util.DemographicsDecryptData2SqlMapper#run(javax.servlet.ServletContext) for the usage -->
<%@ page import="com.solidstategroup.radar.util.DemographicsDecryptData2SqlMapper" %>
<%
    DemographicsDecryptData2SqlMapper demographicsDecryptData2SqlMapper = new DemographicsDecryptData2SqlMapper();
    demographicsDecryptData2SqlMapper.run(request.getSession().getServletContext());
%>