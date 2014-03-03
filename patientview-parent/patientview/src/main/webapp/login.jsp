<%@ page import="org.patientview.patientview.controller.Routes" %>
<%@ page import="org.springframework.security.web.savedrequest.SavedRequest" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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

<%
    // handle Looking Local security by redirecting to Looking Local home, not standard login
    SavedRequest savedRequest = (SavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

    if(savedRequest != null)
        if(savedRequest.getRedirectUrl().toLowerCase().contains("/lookinglocal/"))
            response.sendRedirect("/web" + Routes.LOOKING_LOCAL_HOME);
%>

<tiles:insert definition="default.layout" flush="true" >

    <tiles:put name="left_nav" value="/common/left_nav_login.jsp" />

    <tiles:put name="body" value="/body/login.jsp" />

</tiles:insert>
