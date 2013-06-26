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
        <h1>My IBD Links</h1>
    </div>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th class="tableheader"><bean:write name="codeType" property="linkType" /> Code</th>
            <th class="tableheader">Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <logic:iterate id="edtaCode" name="edtaCodes" >
            <html:form action="/control/myibd-links-edit">
                <tr>
                    <td class="tablecell"><bean:write name="edtaCode" property="edtaCode" /></td>
                    <td class="tablecell"><bean:write name="edtaCode" property="description" /></td>
                    <html:hidden property="edtaCode" name="edtaCode" />
                    <html:hidden property="linkType" name="codeType" />
                    <td><html:submit value="Edit" styleClass="btn" /></td>
                </tr>
            </html:form>
        </logic:iterate>
        </tbody>
    </table>

    <html:form action="/control/myibd-links-add">
        <html:hidden property="linkType" name="codeType"/>
        <html:submit value="Add New" styleClass="btn" />
    </html:form>

</div>
</div>
