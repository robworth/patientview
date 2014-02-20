<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="org.patientview.utils.LegacySpringUtils" %>
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

<html:xhtml/>
<div class="span9">

<div class="page-header">
    <h1>Select Unit</h1>
</div>

<%
    if (request.getParameter("validation") != null) {
%>
<div class="alert alert error">
    Please select either a renal unit, NHS number or a Forename/Surname
</div>
<%
    }
%>

<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients" method="post">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b><logic:present specialty="renal">Renal Unit</logic:present><logic:present specialty="ibd">IBD Unit</logic:present>
          <logic:present specialty="diabetes">Unit</logic:present></b></td>
      <td>
          <select name="unitcode">
            <logic:present role="superadmin">
              <option value="" >-- All Units --</option>
            </logic:present>
            <logic:iterate id="unit" name="units" >
                <option value="${unit.unitcode}">${unit.name}</option>
            </logic:iterate>
          </select>
      </td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td><input type="text" name="nhsno" value=""></td>
    </tr>
    <tr>
      <td><b>Forename</b></td>
      <td><input type="text" name="firstname" value=""></td>
    </tr>
    <tr>
      <td><b>Surname</b></td>
      <td><input type="text" name="lastname" value=""></td>
    </tr>
    <tr>
      <td><b>Show GPs</b></td>
      <td><input type="checkbox" name="showgps" value="true"></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Search" styleClass="btn" /></td>
    </tr>
 </table>
</form>
</div>
</div>
