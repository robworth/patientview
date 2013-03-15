<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<p class="header">Password Reset</p>

<logic:present name="passwordUpdateError" >
    <p>There was an error updating this user's password</p>
</logic:present>

<logic:present name="passwordUpdated" >
    On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully reset the password of the patient with the following details:
    <br /><br />

    <table cellpadding="3" >
        <tr>
          <td><b>User Name</b></td>
          <td><bean:write name="user" property="username" /></td>
        </tr>
        <tr>
          <td><b>Password</b></td>
          <td class="password"><bean:write name="user" property="password" /></td>
        </tr>
        <tr>
          <td><b>Name</b></td>
          <td><bean:write name="user" property="name" /></td>
        </tr>
        <tr>
          <td><b>Email Address</b></td>
          <td><bean:write name="user" property="email" /></td>
        </tr>
     </table>
</logic:present>

