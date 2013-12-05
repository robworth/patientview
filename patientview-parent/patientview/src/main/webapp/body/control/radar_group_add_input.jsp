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
    <h1>RaDaR Group Add</h1>
</div>

<html:errors />

<html:form action="/control/radarGroupAdd">

    <logic:present name="UnitExisted">
        <bean:message key="errors.header"/><bean:message key="errors.prefix"/><bean:message
            key="unitcode.duplicated"/><bean:message key="errors.suffix"/><bean:message key="errors.footer"/>
    </logic:present>

  <table cellpadding="3" >

    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Code</b></td>
      <td><html:text property="unitcode" /></td>
    </tr>

    <tr>
      <td><b>Name</b></td>
      <td><html:text property="name" /></td>
    </tr>

    <tr>
      <td><b>Short Name</b></td>
      <td><html:text property="shortname" maxlength="15"/></td>
    </tr>

    <html:hidden property="sourceType" value="radargroup"/>

    <tr>
      <td><b>Admin Name</b></td>
      <td><html:text property="renaladminname" /></td>
    </tr>

    <tr>
      <td><b>Admin Phone</b></td>
      <td><html:text property="renaladminphone" /></td>
    </tr>

    <tr>
      <td><b>Admin Email</b></td>
      <td><html:text property="renaladminemail" /></td>
    </tr>

    <tr>
      <td><html:submit value="Add" styleClass="formButton"/></td>
    </tr>

  </table>

</html:form>
</div>

</div>
