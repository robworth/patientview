<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
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
    <h1>${isRadarGroup?'Users in RaDaR Groups':'Users for Unit'} <logic:notEmpty name="unit"><bean:write name="unit" property="name"/></logic:notEmpty></h1>
</div>



<logic:notEmpty name="unitUsers">
    <div class="span10" style="margin-left: 10px;margin-bottom:5px;">
        <div class="row" style="float: right;">
            <logic:equal value="false" name="unitUsers" property="firstPage">
                <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitUsers?page=prev">Prev</a>&nbsp;
            </logic:equal>
            &nbsp;
            <logic:equal value="false" name="unitUsers" property="lastPage">
                <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitUsers?page=next">Next</a>
            </logic:equal>
        </div>
    </div>

  <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
      <tr>
          <th class="tableheader" onclick="sort('name')"><a href="#">Name</a></th>
          <th class="tableheader" onclick="sort('username')"><a href="#">Username</a></th>
          <th class="tableheader" onclick="sort('displayRole')"><a href="#">Role</a></th>
          <th class="tableheader" onclick="sort('email')"><a href="#">Email</a></th>
          <th class="tableheader" onclick="sort('emailverified')"><a href="#">Email Verified</a></th>
          <th class="tableheader" onclick="sort('lastlogon')"><a href="#">Last Login</a></th>
          <th class="tableheader" onclick="sort('accountlocked')"><a href="#">Password</a></th>
          <th class="tableheader" onclick="sort('isrecipient')"><a href="#">Message Recipient</a></th>
          <th class="tableheader" onclick="sort('isclinician')"><a href="#">Clinician</a></th>

      </tr>
    <logic:iterate id="unitUser" name="unitUsers" type="org.patientview.patientview.logon.UnitAdmin" property="pageList">
        <%
            Map <String, String> patientKeyParams = new HashMap <String, String>();
            patientKeyParams.put("unitcode", unitUser.getUnitcode());
            patientKeyParams.put("username", unitUser.getUsername());
            request.setAttribute("patientKeyParams", patientKeyParams);
        %>
      <tr>
        <td class="tablecell">
            <logic:present role="superadmin,unitadmin">
                <html:link action="/control/unitUserEditInput" name="patientKeyParams">
                    <bean:write name="unitUser" property="name"/>
                </html:link>
            </logic:present>
        </td>
        <td class="tablecell"><bean:write name="unitUser" property="username"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="displayRole"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="email"/></td>
        <td class="tablecell">
            <logic:equal value="false" name="unitUser" property="emailverified">
                <big><font color="red">&#10008;</font></big>
            </logic:equal>
            <logic:equal value="true" name="unitUser" property="emailverified">
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

      </tr>
    </logic:iterate>
   </table>
 </logic:notEmpty>
</div>


<script type="text/javascript">
    function sort(property){
        window.location.href="./unitUsers?page=sort&property="+property;
    }
</script>
