<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:xhtml/>

<div class="page-header">
    <h1>Join Patient View</h1>
</div>

<p>Please enter your details. You need to be a patient of a renal unit.</p>
<p>Ensure they are accurate since they will be used by your unit's staff to verify who you are and to contact you to add you to PatientView.</p>
<p>If your local unit has PatientView, the message will go to the local PatientView admin.  If it doesn't, we'll send it to someone senior at the unit.  See if your unit has PatientView on the map at <a href ="http://bit.ly/rpvsites" target="_blank">bit.ly/rpvsites</a></p>
<br />

<html:form action="/joinSubmit" styleClass="form-horizontal">

    <html:errors/>

    <div class="control-group">
        <label class="control-label">First name</label>

        <div class="controls"><html:text property="firstName"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Last name</label>

        <div class="controls"><html:text property="lastName"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Date of birth</label>

        <div class="date datePicker controls" data-date="<bean:write name="joinForm" property="dateOfBirth"/>">
            <input name="dateOfBirth" class="span2" size="16" type="text"
                   value="<bean:write name="joinForm" property="dateOfBirth"/>">
            <span class="add-on"><i class="icon-th"></i></span>
            <span class="help-inline">dd-mm-yyyy</span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">NHS Number</label>

        <div class="controls"><html:text property="nhsNo"/> (optional but useful)</div>
    </div>

    <div class="control-group">
        <label class="control-label">Unit</label>

        <div class="controls">
            <html:select property="unitcode">
                <option value="-1" selected="selected">-- Select your unit --</option>
                <html:options collection="units" property="unitcode" labelProperty="name"/>
            </html:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Email address</label>

        <div class="controls"><html:text property="email"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Security Question</label>


        <div class="controls">
            <div class="help-block top-help-block"><%=request.getSession().getAttribute("antiSpamQuestion")%> = ?</div>

            <html:text property="antiSpamAnswer"/>
        </div>
    </div>

    <div class="control-group">
        <div class="controls"><html:submit value="Send join request" styleClass="btn"/></div>
    </div>

</html:form>
