<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div class="page-header">
    <h1>Forgotten password</h1>
</div>
<logic:notEmpty name="nullUser">
    <div class="alert alert-info">Please enter your user name and email address.</div>
</logic:notEmpty>
<logic:equal name="foundUser" value="false">
    <div class="alert alert-block">No match was found for the user and email combination you entered.</div>
</logic:equal>
<logic:notEmpty name="nullEmail">
    <div class="alert alert-error">That username does not have an email set. Please contact an administrator.</div>
</logic:notEmpty>
<logic:notEmpty name="noMatch">
    <div class="alert alert-error">No match was found for the user and email combination you entered.</div>
</logic:notEmpty>

<form action="forgotten-password.do" class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="username">Username</label>
            <div class="controls">
                <input type="text" name="username" id="username" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email address</label>
            <div class="controls">
                <input type="text" name="email" id="email" />
            </div>  
        </div>
        <div class="form-actions">
            <input type="submit" value="Submit" class="btn"/>    
        </div>
    </fieldset>
</form>

