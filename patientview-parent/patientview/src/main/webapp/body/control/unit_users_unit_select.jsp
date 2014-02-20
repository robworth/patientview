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
    <h1>${isRadarGroup?'Select RaDaR Group':'Select Unit'}</h1>
</div>


<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitUsers" method="post">
    <input type="hidden" name="isRadarGroup" value="${isRadarGroup}">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b><logic:present specialty="renal">${isRadarGroup? 'RaDaR Group':'Renal Unit'}</logic:present>
          <logic:present specialty="ibd">IBD Unit</logic:present>
          <logic:present specialty="diabetes">Unit</logic:present></b></td>
      <td>
          <select name="unitcode">
              <logic:present role="superadmin">
                  <option value="" >-- ${isRadarGroup? 'All RaDaR groups' : 'All units'} --</option>
              </logic:present>
              <logic:iterate id="unit" name="units" >
                  <option value="${unit.unitcode}" >${unit.name}</option>
              </logic:iterate>
          </select>
      </td>
    </tr>
    <tr align="right">
      <td><html:submit value="Select" styleClass="btn" /></td>
    </tr>
 </table>

</form>
</div>
</div>
