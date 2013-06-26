<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

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
        <h1 class="header">Password Reset</h1>
    </div>

<logic:present name="passwordUpdateError" >
    <p>There was an error updating this user's password</p>
</logic:present>

<logic:present name="passwordUpdated" >
    On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully reset the password of the patient with the following details:
    <br /><br />

    <table cellpadding="3" >
        <tr>
          <td><b>User Name</b></td>
          <td><bean:write name="user" property="username" /></td>
        </tr>
        <tr>
          <td><b>Password</b></td>
          <td class="password"><bean:write name="plaintextPassword"/></td>
        </tr>
        <tr>
          <td><b>Name</b></td>
          <td><bean:write name="user" property="name" /></td>
        </tr>
        <tr>
          <td><b>Email Address</b></td>
          <td><bean:write name="user" property="email" /></td>
        </tr>
     </table>
</logic:present>
</div>
</div>
