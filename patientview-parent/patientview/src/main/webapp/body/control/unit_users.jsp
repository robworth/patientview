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
    <h1>Users for Unit <bean:write name="unit" property="name"/></h1>
</div>

<logic:notEmpty name="unitUsers">
  <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
      <tr>
        <th class="tableheader">Name</th>
        <th class="tableheader">Username</th>
        <th class="tableheader">Role</th>
        <th class="tableheader">Email</th>
        <th class="tableheader">Email Verified</th>
        <th class="tableheader">Last Login</th>
        <th class="tableheader">Password Locked</th>
        <th class="tableheader">Message Recipient</th>
        <th class="tableheader">Clinician</th>  
        <th></th>
        <th></th>
      </tr>
    <logic:iterate id="unitUser" name="unitUsers">
      <tr>
        <td class="tablecell"><bean:write name="unitUser" property="name"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="username"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="displayRole"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="email"/></td>
        <td class="tablecell">
            <logic:equal value="false" name="unitUser" property="emailverfied">
                <big><font color="red">&#10008;</font></big>
            </logic:equal>
            <logic:equal value="true" name="unitUser" property="emailverfied">
                <big><font color="green">&#10004;</font></big>
            </logic:equal>
        </td>
        <td class="tablecell"><bean:write name="unitUser" property="lastlogonFormatted"/></td>
          <td class="tablecell">
              <logic:equal value="true" name="unitUser" property="accountlocked">
                  <font color="red">locked</font>
              </logic:equal>
              <logic:equal value="false" name="unitUser" property="accountlocked">
                  <big><font color="green">&#10004;</font></big>
              </logic:equal>
          </td>

          <td class="tablecell">
              <logic:equal name="unitUser" property="isrecipient" value="false">No</logic:equal>
              <logic:equal name="unitUser" property="isrecipient" value="true">Yes</logic:equal>
          </td>
          <td class="tablecell">
              <logic:equal name="unitUser" property="isclinician" value="false">No</logic:equal>
              <logic:equal name="unitUser" property="isclinician" value="true">Yes</logic:equal>
          </td>

          <logic:present role="superadmin,unitadmin">
            <td>
              <html:form action="/control/unitUserEditInput">
                <html:hidden name="unitUser" property="username" />
                <html:hidden name="unit" property="unitcode" />
                <html:submit value="Edit" styleClass="btn" />
              </html:form>
            </td>
        </logic:present>

        <logic:present role="superadmin,unitadmin">
            <td>
              <html:form action="/control/activityByUser">
                <html:hidden name="unitUser" property="username" />
                <html:submit value="Activity" styleClass="btn" />
              </html:form>
            </td>
        </logic:present>

      </tr>
    </logic:iterate>
   </table>
 </logic:notEmpty>
</div>
