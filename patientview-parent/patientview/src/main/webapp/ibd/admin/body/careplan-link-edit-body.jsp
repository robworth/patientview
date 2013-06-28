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
        <h1>Links Editing</h1>
    </div>

    <html:errors/>

    <html:form action="/control/careplan-links-update" styleClass="form-horizontal">
        <html:hidden property="linkType" name="edtaCode"/>
        <fieldset>
            <div class="control-group">
                <label class="control-label">Code</label>

                <div class="controls"><bean:write name="edtaCode" property="edtaCode"/></div>
                <html:hidden name="edtaCode" property="edtaCode"/>
            </div>
            <div class="control-group">
                <label class="control-label">Description</label>

                <div class="controls"><html:text name="edtaCode" property="description"/></div>
            </div>
        </fieldset>

        <table cellpadding="3" class="table table-bordered table-striped">
            <tr>
                <td></td>
                <td align="center"><b>Link (e.g. http:// etc.)</b></td>
                <td align="center"><b>Text Description</b></td>
            </tr>
            <tr>
                <td><b>Help Link</b></td>
                <td><html:text name="edtaCode" property="medicalLink01"/></td>
                <td><html:text name="edtaCode" property="medicalLinkText01"/></td>
            </tr>
        </table>

        <html:submit value="Update" styleClass="btn"/>

    </html:form>
</div>
