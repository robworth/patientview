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
    <h1>Admin Addition Confirmation</h1>
</div>

On <dt:format
        pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully added a new admin user with the following details:
<br/><br/>

<table cellpadding="3">
    <tr>
        <td><b>User Name</b></td>
        <td><bean:write name="adminuser" property="username"/></td>
    </tr>
    <logic:notEqual value="" name="adminuser" property="password">
      <tr>
          <td><b>Password</b></td>
          <td><bean:write name="adminuser" property="password"/></td>
      </tr>
    </logic:notEqual>
    <tr>
        <td><b>Name</b></td>
        <td><bean:write name="adminuser" property="name"/></td>
    </tr>
    <tr>
        <td><b>Email Address</b></td>
        <td><bean:write name="adminuser" property="email"/></td>
    </tr>
</table>

<br/>

<logic:notEmpty name="adminuser" property="email">
    <p>A verification email has been sent to <bean:write name="adminuser" property="email"/>. The new user needs to
        click the link in that email to verify their email address. The email sent does NOT contain the user's username
        or password, so you still need to give them this information in the usual way. The verification link will expire
        in two weeks.</p>
</logic:notEmpty>
</div>
