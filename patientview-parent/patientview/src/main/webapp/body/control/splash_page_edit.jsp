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
    <h1>Splash Page Editing</h1>
</div>

<html:errors />

<p><b>Note:</b> It is the <b>name</b> field that lets a user know that they should see the page again. Changing the name lets users that have seen a previous splash page seen the new one. So if you are just tweaking a splash page, don't change the name. If it is a new one, change the name so everyone sees it.</p>

<p><b>HTML:</b> You can use simple html markup in the pages like links or bold but don't be too fancy or risk breaking the page.</p>
<html:form action="/control/splashPageUpdate">
   <html:hidden property="id" name="splashPage" />
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text name="splashPage" property="name" /></td>
    </tr>
    <tr>
      <td><b>Headline</b></td>
      <td><html:text name="splashPage" property="headline" /></td>
    </tr>
    <tr>
        <td><b>Unit</b></td>
        <td><html:select property="unitcode">
            <logic:present role="superadmin">
                <html:option value="ALL">-- All units --</html:option>
            </logic:present>
            <html:options collection="units" property="unitcode" labelProperty="unitNamePlusCode"/>
        </html:select></td>
    </tr>
    <tr>
      <td><b>Body Text</b></td>
      <td><html:textarea cols="40" rows="10" name="splashPage" property="bodytext" /></td>
    </tr>
    <tr>
      <td><b>Is Live</b></td>
      <td><html:select name="splashPage" property="live" >
            <html:option value="true">live</html:option>
            <html:option value="false">inactive</html:option>
          </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>

    <tr>
        <td>&nbsp;</td>
      <td align="right"><html:submit value="Update" styleClass="btn"/></td>
    </tr>
</html:form>

</table>
      <br/>
<hr/>

<p class="header"><bean:write name="splashPage" property="headline" ignore="true"/></p>

<p style="white-space: pre-wrap;"><bean:write filter="false" name="splashPage" property="bodyTextForHtml"  ignore="true"/></p>

</div>
</div>
