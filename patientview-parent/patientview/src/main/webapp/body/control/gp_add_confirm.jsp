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
    <h1>GP</h1>
</div>


On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully enrolled a new GP with the following details:
<br /><br />

<table cellpadding="3" >
    <tr>
      <td><b>User Name</b></td>
      <td><logic:notEmpty name="gp">
            <bean:write name="gp" property="username" />
          </logic:notEmpty>
      </td>
    </tr>
    <tr>
      <td><b>Password</b></td>
      <td class="password">
        <logic:notEmpty name="gp">
          <h3><bean:write name="gp" property="password" /></h3>
        </logic:notEmpty>
    </td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td>
          <logic:notEmpty name="gp">
              <bean:write name="gp" property="name" />
          </logic:notEmpty>
      </td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td>
          <logic:notEmpty name="gp">
              <bean:write name="userMappingGp" property="nhsno" />
          </logic:notEmpty>
      </td>
    </tr>
    <tr>
      <td><b>Email Address</b></td>
      <td>
          <logic:notEmpty name="gp">
            <bean:write name="gp" property="email" />
          </logic:notEmpty>
      </td>
    </tr>
 </table>
<br/>
</div>
