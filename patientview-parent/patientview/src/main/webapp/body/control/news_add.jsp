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
<table width="100%" cellpadding="3">
  <tr>
    <td align="left"><html:link action="/control/newsList" styleClass="btn">&larr; back to News List</html:link></td>
  </tr>
</table>


<html:form action="/control/newsPreview">

  <table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Headline</b></td>
    </tr>
    <tr>
      <td><html:text property="headline" /></td>
    </tr>
    <tr>
      <td><b>Text</b></td>
    </tr>
    <tr>
      <td colspan="2"><html:textarea property="body" cols="50" rows="12" /></td>
    </tr>
    <tr>
        <td><b>Renal Unit</b></td>
        <td><html:select property="unitcode">
               <logic:present role="superadmin">
                 <html:option value="all">-- All Units --</html:option>
               </logic:present>
               <html:options collection="units" property="unitcode" labelProperty="name"/>
            </html:select></td>
      </tr>
    <tr>
      <td><b>Viewable by Unit Admins</b></td>
      <td><html:checkbox property="admin" value="true" /></td>
    </tr>
    <tr>
      <td><b>Viewable by Patients</b></td>
      <td><html:checkbox property="patient" value="true" /></td>
    </tr>
    <logic:present role="superadmin">
      <tr>
        <td><b>Viewable by the General Public</b></td>
        <td><html:checkbox property="everyone" value="true" /></td>
      </tr>
    </logic:present>
    <tr align="right">

      <td>&nbsp;</td>
      <td><html:submit value="Preview" styleClass="btn" property="submission"/>&nbsp;
      <html:submit value="Post" styleClass="btn" property="submission"/></td>
    </tr>
 </table>

</html:form>

<logic:present name="news">

  <p class="header"><bean:write name="news" property="headline" /></p>

  <p><bean:write name="news" property="bodyForHtml" filter="false" /></p>

</logic:present>
</div>
</div>
