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
        <h1>Units</h1>
    </div>

<table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">

  <logic:present name="units">
    <logic:notEmpty name="units">
      <tr>
        <th class="tableheader">Unit Code</th>
        <th class="tableheader">Name</th>
        <th></th>
        <th></th>
      </tr>
      <logic:iterate id="unit" name="units">
        <tr>
          <td class="tablecell"><bean:write name="unit" property="unitcode"/></td>
          <td class="tablecell"><bean:write name="unit" property="name"/></td>

          <logic:present role="superadmin,unitadmin">
            <td>
                <html:form action="/control/unitEdit">
                  <html:hidden name="unit" property="unitcode"/>
                  <html:submit value="Edit" styleClass="btn"/>
                </html:form>
            </td>
            <td>
            <html:form action="/control/unitStat">
              <html:hidden name="unit" property="unitcode"/>
              <html:submit value="Stats" styleClass="btn"/>
            </html:form>
            </td>
          </logic:present>
        </tr>
      </logic:iterate>
    </logic:notEmpty>
  </logic:present>
</table>

  <logic:present role="superadmin">

      <html:form action="/control/unitAddInput">
        <html:submit value="Add new" styleClass="btn"/>
      </html:form>
  </logic:present>
</div>
</div>
