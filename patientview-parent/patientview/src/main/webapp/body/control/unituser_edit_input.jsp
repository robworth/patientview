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
        <h1>Unit User Edit</h1>
    </div>
    <html:errors/>
    <div class="form-horizontal">
        <html:form action="/control/unitUserEdit">
            <div class="control-group">
                <label class="control-label">User Name</label>
                <div class="controls"><html:hidden name="unitUser" property="username" write="true"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Name</label>
                <div class="controls"><html:text name="unitUser" property="name"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Email Address</label>
                <div class="controls"><html:text name="unitUser" property="email"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Role</label>
                <div class="controls"><html:select property="role" name="unitUser">
                    <html:option value="unitstaff">Unit Staff</html:option>
                    <html:option value="unitadmin">Unit Admin</html:option>
                </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Message Recipient</label>
                <div class="controls">
                    <html:checkbox property="isrecipient" name="unitUser" value="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Clinician</label>
                <div class="controls">
                    <html:checkbox property="isclinician" name="unitUser" value="true"/>
                </div>
            </div>
            <html:hidden name="unitcodeThing" property="unitcode"/>
            <html:hidden name="unitUser" property="emailverified"/>
            <html:hidden name="unitUser" property="firstlogon"/>
            <html:hidden name="unitUser" property="password"/>
            <html:hidden name="unitUser" property="lastlogon"/>
            <html:hidden name="unitUser" property="failedlogons"/>
            <html:hidden name="unitUser" property="accountlocked"/>

            <html:submit value="Save" styleClass="pull-right btn btn-primary formbutton float-in-form-actions"/>
        </html:form>

        <div class="patient-edit-form form-actions">
            <div class="row">

                <html:form action="/control/userDelete">
                    <html:hidden name="unitUser" property="username"/>
                    <html:hidden name="unitcodeThing" property="unitcode"/>
                    <html:submit value="Delete Unit User" styleClass="btn btn-danger" style="float:left;margin-left:5px;"/>
                </html:form>

                <html:form action="/control/resetPassword" style="float:left;margin-left:5px;">
                    <html:hidden name="unitUser" property="username"/>
                    <html:hidden name="unitcodeThing" property="unitcode"/>
                    <html:submit value="Reset Password" styleClass="btn"/>
                </html:form>

                <logic:match value="true" name="unitUser" property="accountlocked">
                    <html:form action="/control/passwordUnlock" style="float:left;margin-left:5px;">
                        <html:hidden name="unitUser" property="username"/>
                        <html:hidden name="unitcodeThing" property="unitcode"/>
                        <html:submit value="Unlock Password" styleClass="btn"/>
                    </html:form>
                </logic:match>
            </div>
        </div>

    </div>
</div>