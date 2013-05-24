<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
