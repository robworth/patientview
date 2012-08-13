<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>
<div class="span9">

<div class="page-header">
    <h1>Admin Addition Confirmation</h1>
</div>

On <dt:format
        pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully added a new admin user with the following details:
<br/><br/>

<table cellpadding="3">
    <tr>
        <td><b>User Name</b></td>
        <td><bean:write name="adminuser" property="username"/></td>
    </tr>
    <logic:notEqual value="" name="adminuser" property="password">
      <tr>
          <td><b>Password</b></td>
          <td><bean:write name="adminuser" property="password"/></td>
      </tr>
    </logic:notEqual>    
    <tr>
        <td><b>Name</b></td>
        <td><bean:write name="adminuser" property="name"/></td>
    </tr>
    <tr>
        <td><b>Email Address</b></td>
        <td><bean:write name="adminuser" property="email"/></td>
    </tr>
</table>

<br/>

<logic:notEmpty name="adminuser" property="email">
    <p>A verification email has been sent to <bean:write name="adminuser" property="email"/>. The new user needs to
        click the link in that email to verify their email address. The email sent does NOT contain the user's username
        or password, so you still need to give them this information in the usual way. The verification link will expire
        in two weeks.</p>
</logic:notEmpty>
</div>