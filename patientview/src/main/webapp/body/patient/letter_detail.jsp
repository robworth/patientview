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

<html:link action="/patient/letters" styleClass="btn"><< back to letters</html:link>

<table width="470" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped paragraphSizeTopMargin">
    <thead>
        <tr>
            <th width="100" class="tablecellbold">Date</th>
            <th class="tablecellbold">Letter Type</th>
        </tr>
    </thead>

  <tr>
    <td class="tablecell"><bean:write name="letter" property="formattedDate"/></td>
    <td class="tablecell"><bean:write name="letter" property="type"/></td>
  </tr>
</table>
<div>
    <bean:write name="letter" property="formattedContent" filter="false"/>
</div>

<logic:present role="unitadmin,superadmin">
    <br>
    <div>
        <html:form action="/control/letterDelete">
            <html:hidden name="letter" property="id"/>
            <html:submit value="Delete Letter" styleClass="btn-danger"/>
        </html:form>
    </div>
</logic:present>
