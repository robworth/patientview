<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
    <h1>View Log</h1>
</div>
<table width="600" border="0" cellspacing="1" cellpadding="3">

  <tr height="20">
    <td colspan="10"><div class="alert alert-info">You must enter a start and an end date and at least one other search criteria. Dates in format
      dd/mm/yyyy</div>
    </td>
  </tr>

  <html:form action="/control/logView">

    <tr>
      <td><b>Start Date</b></td>
      <td><html:text property="startdate"/></td>
      <td><b>End Date</b></td>
      <td><html:text property="enddate"/></td>
    </tr>

    <tr>
      <td><b>NHS No</b></td>
      <td><html:text property="nhsno"/></td>
      <td><b>User</b></td>
      <td><html:text property="user"/></td>
    </tr>

    <tr>
      <td><b>Actor</b></td>
      <td><html:text property="actor"/></td>
      <td><b>Action</b></td>
      <td>
        <html:select property="action">
          <html:option value=""/>
          <html:option value="password reset"/>
          <html:option value="password reset forgotten"/>
          <html:option value="password change"/>
          <html:option value="patient data load"/>
          <html:option value="patient data fail"/>
          <html:option value="patient data remove"/>
          <html:option value="logon"/>
          <html:option value="patient add"/>
          <html:option value="patient delete"/>
          <html:option value="patient view"/>
          <html:option value="admin add"/>
          <html:option value="ukt data"/>
          <html:option value="password locked"/>
          <html:option value="password unlocked"/>
          <html:option value="email verified"/>
        </html:select>
      </td>
    </tr>

    <tr>
      <td><b>Unit</b></td>
      <td colspan="3"><html:select property="unitcode">
        <logic:present role="superadmin">
          <html:option value="">-- All units --</html:option>
        </logic:present>
        <html:options collection="units" property="unitcode" labelProperty="unitNamePlusCode"/>
      </html:select></td>
    </tr>

    <tr align="left">
      <td>&nbsp;</td>
      <td><html:submit value="Search" styleClass="btn"/></td>
    </tr>

  </html:form>
</table>


<table width="600" border="0" cellspacing="1" cellpadding="1" class="table table-bordered table-striped paragraphSizeTopMargin">

  <logic:empty name="log">
    <tr valign="top">
      <td class="tableheader"><div class="alert alert-block">No log entries found.</div></td>
    </tr>
  </logic:empty>

  <logic:notEmpty name="log">
    <thead>
        <tr>
          <th class="tablecellbold">Date and time</th>
          <th class="tablecellbold">NHS No</th>
          <th class="tablecellbold">User</th>
          <th class="tablecellbold">Action</th>
          <th class="tablecellbold">Actor</th>
          <th class="tablecellbold">Unit</th>
          <th class="tablecellbold">Extra Info</th>
        </tr>
    </thead>
    <tbody>
    <logic:iterate name="log" id="logentry">
      <tr>
        <td class="tablecell"><bean:write name="logentry" property="formattedDate"/></td>
        <td class="tablecell"><bean:write name="logentry" property="nhsno"/></td>
        <td class="tablecell"><bean:write name="logentry" property="user"/></td>
        <td class="tablecell"><bean:write name="logentry" property="action"/></td>
        <td class="tablecell"><bean:write name="logentry" property="actor"/></td>
        <td class="tablecell"><bean:write name="logentry" property="unitcode"/></td>
        <logic:notEqual value="patient data load" name="logentry" property="action" >
          <logic:notEqual value="patient data remove" name="logentry" property="action">
            <logic:notEqual value="patient data fail" name="logentry" property="action">
              <td class="tablecell"><bean:write name="logentry" property="extrainfo"/></td>
            </logic:notEqual>
          </logic:notEqual>
        </logic:notEqual>
        <logic:equal value="patient data load" name="logentry" property="action">
          <td class="tablecell"><html:link action="/control/xmlFileView" paramId="xmlfile" paramName="logentry" paramProperty="xmlfilename"><bean:write name="logentry" property="extrainfo"/></html:link></td>
        </logic:equal>
        <logic:equal value="patient data remove" name="logentry" property="action">
          <td class="tablecell"><html:link action="/control/xmlFileView" paramId="xmlfile" paramName="logentry" paramProperty="xmlfilename"><bean:write name="logentry" property="extrainfo"/></html:link></td>
        </logic:equal>
        <logic:equal value="patient data fail" name="logentry" property="action">
          <td class="tablecell"><html:link action="/control/xmlFileView" paramId="xmlfile" paramName="logentry" paramProperty="xmlfilename"><bean:write name="logentry" property="xmlfilename"/></html:link><bean:write name="logentry" property="extrainfolessxmlfilename"/></td>
        </logic:equal>
      </tr>
    </logic:iterate>
    </tbody>
  </logic:notEmpty>

</table>
</div>
</div>
