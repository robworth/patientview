<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<p class="header">Password Unlocked</p>


On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully unlocked the password of this user:
<br/><br/>

<table cellpadding="3">
  <tr>
    <td><b>User Name</b></td>
    <td><bean:write name="patient" property="username"/></td>
  </tr>
  <tr>
    <td><b>Name</b></td>
    <td><bean:write name="patient" property="name"/></td>
  </tr>
  <logic:notEmpty name="patient" property="nhsno">
    <tr>
      <td><b>NHS Number</b></td>
      <td><bean:write name="patient" property="nhsno"/></td>
    </tr>
  </logic:notEmpty>
  <tr>
    <td><b>Email Address</b></td>
    <td><bean:write name="patient" property="email"/></td>
  </tr>
</table>

