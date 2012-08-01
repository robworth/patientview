<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="page-header">
    <h1>Please log in below</h1>
</div>
<form action="j_security_check" method="POST" class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="j_username">Username</label>
            <div class="controls">
                <input type="text" name="j_username" id="j_username" class="loginform input-xlarge"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="j_password">Password</label>
            <div class="controls">
                <input type="password" id="j_password" name="j_password" class="loginform" />
            </div>
        </div>
        <div class="form-actions">
                <input type="submit" value="Login" class="btn"/>         
                <a href="forgotten-password.jsp"> Forgotten password?</a>
        </div>
    </fieldset>
</form>