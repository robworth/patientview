<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
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

<div class="navbar">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <html:link action="/index" styleClass="brand">
            <logic:present specialty="ibd">My IBD</logic:present>
            <logic:present specialty="renal">RPV</logic:present>
            Administration Area
        </html:link>
       <div class="nav-collapse">
            <ul class="nav pull-right">
                <%
                    if (LegacySpringUtils.getSecurityUserManager().isLoggedIn()) {
                %>
                <li class="pull-right "><div class="navText">logged in as: <b><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%></b></div>
                <logic:present role="superadmin,unitadmin,unitstaff">
                <li><html:link forward="controlPasswordChangeInput">Change password</html:link></li>
                </logic:present>
                <li><html:link action="logout">Logout</html:link></li>
                <jsp:include page="../include/specialty_switcher.jsp"/>
                <%
                    }
                %>

                <li><html:link action="/help" styleClass="<%= ("help".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Need help <i class="icon-question-sign icon-white"></i></html:link></li>

            </ul>

            <%
                    if (!LegacySpringUtils.getSecurityUserManager().isLoggedIn()) {
            %>
                <form class="navbar-form pull-right" action="j_security_check" method="POST">
                    <input type="text" class="span2" name="j_username" placeholder="Username">
                    <input type="password" class="span2" name="j_password" placeholder="Password">
                    <input class="btn" type="submit" value="Login">
                </form>
            <%
                }
            %>
        </div><!-- /.nav-collapse -->
      </div>
    </div><!-- /navbar-inner -->
</div>

<div class="container">
