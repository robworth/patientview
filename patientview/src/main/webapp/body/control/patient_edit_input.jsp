<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">
    <div class="page-header">
        <h1>Patient Edit</h1>
    </div>

    <html:errors/>

    <logic:present name="nhsnoAlreadyExists">
        <p><font color="red">The NHS number <b><bean:write name="nhsnoAlreadyExists"/></b> you entered is already allocated to
            another user. <br/> You may override this using the checkbox below. But please use this with care!!</font></p>
    </logic:present>

    <div class="form-horizontal">
        <html:form action="/control/patientEditX">
            <div class="control-group">
                <label class="control-label">User Name</label>
                <div class="controls">
                    <html:hidden name="patient" property="username" write="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Name</label>
                <div class="controls">
                    <html:text name="patient" property="name"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">NHS Number</label>
                <div class="controls">
                    <html:text property="nhsno" name="nhsnot"/>
                    <html:hidden property="unitcode" name="nhsnot"/>
                </div>
                <logic:present name="nhsnoAlreadyExists">
                    <label class="control-label">Override Duplicate</label>
                    <div class="controls">
                        <html:checkbox property="overrideDuplicateNhsno"/>
                    </div>
                </logic:present>
                <logic:notPresent name="nhsnoAlreadyExists">
                    <html:hidden property="overrideDuplicateNhsno" value=""/>
                </logic:notPresent>
            </div>

            <div class="control-group">
                <label class="control-label">Email Address</label>
                <div class="controls">
                    <html:text name="patient" property="email"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Email Address Verified</label>
                <div class="controls">
                    <logic:equal name="patient" property="emailverified" value="true"><big><font color="green">&#10004;</font></big></logic:equal>
                    <logic:equal name="patient" property="emailverified" value="false"><big><font color="red">&#10008;</font></big></logic:equal>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Dummy Patient</label>
                <div class="controls">
                    <html:checkbox name="patient" property="dummypatient" value="true"/>
                </div>
            </div>

            <html:hidden name="patient" property="emailverified"/>
            <html:hidden name="patient" property="firstlogon"/>
            <html:hidden name="patient" property="password"/>
            <html:hidden name="patient" property="lastlogon"/>
            <html:hidden name="patient" property="failedlogons"/>
            <html:hidden name="patient" property="accountlocked"/>
            <html:hidden name="patient" property="screenname"/>
            <html:submit value="Save" styleClass="pull-right btn btn-primary formbutton float-in-form-actions"/>
        </html:form>

            <div class="patient-edit-form form-actions">
                <div class="row">

                    <%
                        // Note: this is hidden.  To be added back in a future story
                    %>
                    <%--<html:form action="/control/userDelete">--%>
                        <%--<html:hidden name="patient" property="username"/>--%>
                        <%--<html:submit value="Delete Patient" style="float:left;" styleClass="btn btn-danger formbutton"/>--%>
                        <%--<html:hidden property="unitcode" name="nhsnot"/>--%>
                        <%--<html:hidden property="nhsno" name="nhsnot"/>--%>
                    <%--</html:form>--%>

                    <html:form action="/control/resetPassword"  style="float:left;margin-left:5px;">
                        <html:hidden name="patient" property="username"/>
                        <html:hidden property="unitcode" name="nhsnot"/>
                        <html:hidden property="nhsno" name="nhsnot"/>
                        <html:submit value="Reset Password" style="float:left;" styleClass="btn formbutton"/>
                    </html:form>

                    <logic:match value="true" name="patient" property="accountlocked">
                        <html:form action="/control/passwordUnlock" style="float:left;margin-left:5px;">
                            <html:hidden name="patient" property="username"/>
                            <html:hidden property="nhsno" name="nhsnot"/>
                            <html:hidden property="unitcode" name="nhsnot"/>
                            <html:submit value="Unlock Password" styleClass="btn formbutton"/>
                        </html:form>
                    </logic:match>
                </div>
            </div>



    </div>
</div>
</div>
