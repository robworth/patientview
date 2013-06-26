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
    <h1>Patient</h1>
</div>

<html:errors />

<logic:present name="invalidNhsno" >
  <p><font color="red">The NHS number <b><bean:write name="invalidNhsno" /></b> is invalid.</font></p>
</logic:present>

<logic:present name="userAlreadyExists" >
  <p><font color="red">The username <b><bean:write name="userAlreadyExists" /></b> you entered is already being used by another user. Please pick another.</font></p>
</logic:present>

<logic:present name="nhsnoAlreadyExists" >
  <p><font color="red">The NHS number <b><bean:write name="nhsnoAlreadyExists" /></b> you entered is already allocated to another user. <br/> You may override this using the checkbox below. But please use this with care!!</font></p>
</logic:present>

<logic:present name="patientAlreadyInUnit" >
  <p><font color="red">The patient with NHS number <b><bean:write name="patientAlreadyInUnit" /></b> already exists in your unit. You can't add another patient with the same NHS no to your unit. Please check the NHS number.</font></p>
</logic:present>

<html:form action="/control/patientAdd">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>User Name</b></td>
      <td><html:text property="username" /></td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text property="name" /></td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td><html:text property="nhsno" /></td>
      <logic:present name="nhsnoAlreadyExists" >
        <td><b>Override Duplicate</b></td>
        <td><html:checkbox property="overrideDuplicateNhsno"/></td>
      </logic:present>
      <logic:notPresent name="nhsnoAlreadyExists" >
        <html:hidden property="overrideDuplicateNhsno" value="" />
      </logic:notPresent>
      <logic:present name="invalidNhsno" >
        <td><b>Add patient with invalid NHS number</b></td>
        <td><html:checkbox property="overrideInvalidNhsno"/></td>
      </logic:present>
    </tr>
    <tr>
      <td><b>Email Address</b></td>
      <td><html:text property="email" /></td>
    </tr>
    <tr>
      <td><b>
          <logic:present specialty="renal">Renal Unit</logic:present><logic:present specialty="ibd">IBD Unit</logic:present>
      </b></td>
      <td><html:select property="unitcode">
             <html:options collection="units" property="unitcode" labelProperty="name"/>
          </html:select></td>
    </tr>
    <tr>
      <td><b>Dummy Patient</b></td>
      <td><html:checkbox property="dummypatient" value="true" /></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Add" styleClass="btn" /></td>
    </tr>
 </table>

</html:form>
</div>
</div>
