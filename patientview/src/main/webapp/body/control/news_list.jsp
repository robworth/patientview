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
    <h1>News</h1>
</div>

<html:errors />


<table cellpadding="3" class="table table-bordered table-striped">

    <thead>
  <logic:notEmpty name="newses">  <tr class="tableheader">
    <th class="tableheader" rowspan="2">Headline</th>
    <th class="tableheader" rowspan="2">Posted</th>
    <logic:present role="superadmin">
      <th class="tableheader" colspan="4" align="center">Available to</th>
    </logic:present>
    <logic:present role="unitadmin">
      <th class="tableheader" colspan="2" align="center">Available to</th>
    </logic:present>
      <th rowspan="2"></th>

  </tr>
  <tr class="tableheader">
    <logic:present role="superadmin">
      <th class="tableheader">Unit(s)</th>
    </logic:present>
    <th class="tableheader">Admins</th>
    <th class="tableheader">Patients</th>
    <logic:present role="superadmin"><th class="tableheader">Public</th></logic:present>
  </tr>
  </thead>

  <logic:iterate id="news" name="newses" >
     <tr>

       <td class="tablecell">
         <html:link action="/control/newsEdit" paramId="id" paramName="news" paramProperty="id">
           <bean:write name="news" property="headline" />
         </html:link>
       </td>

       <td class="tablecell"><bean:write name="news" property="formattedDatestamp"/></td>

       <logic:present role="superadmin">
         <td class="tablecell"><bean:write name="news" property="unitcode" /></td>
       </logic:present>

       <td class="tablecell" align="center">
         <logic:equal value="true" name="news" property="admin"><font color="green">&#10004;</font></logic:equal>
         <logic:notEqual value="true" name="news" property="admin"><font color="red">&#10008;</font></logic:notEqual>
       </td>

       <td class="tablecell" align="center">
         <logic:equal value="true" name="news" property="patient"><font color="green">&#10004;</font></logic:equal>
         <logic:notEqual value="true" name="news" property="patient"><font color="red">&#10008;</font></logic:notEqual>
       </td>

       <logic:present role="superadmin">
         <td class="tablecell" align="center">
           <logic:equal value="true" name="news" property="everyone"><font color="green">&#10004;</font></logic:equal>
           <logic:notEqual value="true" name="news" property="everyone"><font color="red">&#10008;</font></logic:notEqual>
         </td>
       </logic:present>

       <td class="tablecell" align="center" valign="center">
         <html:form action="/control/newsDelete">
           <html:hidden name="news" property="id" />
           <html:submit value="Delete" styleClass="btn" />
         </html:form>
       </td>

     </tr>
  </logic:iterate>
</logic:notEmpty>

</table>

      <html:form action="/control/newsAdd">
        <html:submit value="Add New" styleClass="btn" />
      </html:form>

</div>
</div>
