<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<p class="header">Forgotten password</p>
<logic:notEmpty name="nullUser">
    <p class="error">Please enter your user name and email address.</p>
</logic:notEmpty>
<logic:equal name="foundUser" value="false">
    <p class="error">No match was found for the user and email combination you entered.</p>
</logic:equal>
<logic:notEmpty name="nullEmail">
    <p class="error">That username does not have an email set. Please contact an administrator.</p>
</logic:notEmpty>
<logic:notEmpty name="noMatch">
    <p class="error">No match was found for the user and email combination you entered.</p>
</logic:notEmpty>

<form action="forgotten-password.do" class="forgotenPassword">
    <table>
        <tr>
            <td>User name</td>
            <td><input type="text" name="username" id="username" /></td>
        </tr>
        <tr>
            <td>Email address</td>
            <td><input type="text" name="email" id="email" /></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" class="formButton" value="Submit"/></td>
        </tr>
    </table>
</form>

