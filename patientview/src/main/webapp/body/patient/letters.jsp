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

    <div class="page-header">
        <h1>Letters</h1>
    </div>

    <ul class="padded-list">
        <li>
            Letters are only shown where they can be retrieved from your unit's computer system.
        </li>
        <li>
            It is important to note that letters may be shown here before they have been finally approved and had
            mistakes corrected. This mainly applies if you are reading letters soon (e.g. within a week) after they have
            been typed.
        </li>
        <li>
            If you have any concerns you should discuss it at your next clinic appointment, or contact the author of the
            letter.
        </li>
    </ul>

  <logic:empty name="letters">
      <div class="alert">No letters uploaded</div>
  </logic:empty>

  <logic:notEmpty name="letters">

    <logic:present name="user">

    <h2>Letters for <bean:write name="user" property="name"/></h2>

<table width="90%" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
      <thead>
          <tr>
            <th>Date</th>
            <th>Letter Type</th>
            <th>&nbsp;</th>
            <logic:present role="unitadmin,superadmin"><th>&nbsp;</th></logic:present>
          </tr>
      </thead>

      <logic:iterate name="letters" id="letter">
        <tr>
          <td class="tablecell"><bean:write name="letter" property="formattedDate"/></td>
          <td class="tablecell"><bean:write name="letter" property="type"/></td>
          <td class="tablecell"><html:link action="/patient/letterDetail" paramName="letter" paramProperty="id" paramId="letterId">read letter...</html:link>&nbsp;</td>
          <logic:present role="unitadmin,superadmin"><td class="tablecell"><html:form action="/control/letterDelete"><html:hidden name="letter" property="id"/><html:submit value="Delete" styleClass="btn-danger"/></html:form>&nbsp;</td></logic:present>
        </tr>
      </logic:iterate>

    </logic:present>

  </logic:notEmpty>

</table>
