<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<div class="span9">

<div class="page-header">
    <h1>Patient</h1>
</div>


On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully enrolled a new patient with the following details:
<br /><br />

<table cellpadding="3" >
    <tr>
      <td><b>User Name</b></td>
      <td><bean:write name="patient" property="username" /></td>
    </tr>
    <tr>
      <td><b>Password</b></td>
      <td class="password"><bean:write name="patient" property="password" /></td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><bean:write name="patient" property="name" /></td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td><bean:write name="userMapping" property="nhsno" /></td>
    </tr>
    <tr>
      <td><b>Email Address</b></td>
      <td><bean:write name="patient" property="email" /></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><html:link action="/control/gpAdd">GP Logon Details</html:link></td>
    </tr>
 </table>

<br />

<logic:notEmpty name="patient" property="email">
    <p>A verification email has been sent to <bean:write name="patient" property="email"/>. The new user needs to
        click the link in that email to verify their email address. The email sent does NOT contain the user's username
        or password, so you still need to give them this information in the usual way. The verification link will expire
        in two weeks.</p>
</logic:notEmpty>

</div>
</div>