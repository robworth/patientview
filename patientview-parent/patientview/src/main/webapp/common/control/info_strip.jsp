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

<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%--      <tr>
        <td colspan="10">
          <table width="100%">
            <tr>
                <logic:present role="any_user,patient,demo,superadmin,unitadmin,unitstaff">
                   <td class="infostrip" align="left">logged in as: <b><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%></b> <html:link action="logout">log out</html:link></td>
                </logic:present>

                <logic:notPresent role="any_user,patient,demo,superadmin,unitadmin,unitstaff">
                   <td class="infostrip" align="left">&nbsp;&nbsp;<html:link action="/patient/patient_details">log in</html:link></td>
                </logic:notPresent>

                <logic:present role="superadmin,unitadmin,unitstaff">
                   <td class="infostrip" align="left"><html:link forward="controlPasswordChangeInput">change password</html:link></td>
                </logic:present>

                <jsp:include page="../include/specialty_switcher.jsp"/>

                <td class="infostrip" align="right"><dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format></td>
            </tr>
          </table>
        </td>
      </tr>--%>
