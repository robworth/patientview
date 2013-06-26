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
    <h1>Join Requests</h1>
</div>

<table cellpadding="3" class="table table-bordered table-striped">


    <logic:notEmpty name="joinRequests">

        <tr class="tableheader">
            <th class="tableheader">First Name</th>
            <th class="tableheader">Last Name</th>
            <th class="tableheader">Date of Birth</th>
            <th class="tableheader">NHS Number</th>
            <th class="tableheader">Unit Code</th>
            <th class="tableheader">Email</th>
            <th class="tableheader">Date of Request</th>
        </tr>

        <logic:iterate id="joinrequest" name="joinRequests" >
            <tr>
                <td class="tablecell"><bean:write name="joinrequest" property="firstName" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="lastName" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="dateOfBirthFormatted" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="nhsNo" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="unitcode" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="email" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="dateOfRequestFormatted" /></td>
            </tr>
        </logic:iterate>

    </logic:notEmpty>
</table>


</div>
</div>

