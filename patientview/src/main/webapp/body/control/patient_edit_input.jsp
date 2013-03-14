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

<table cellpadding="3">
  <html:form action="/control/patientEditX">
    <tr>
      <td><img src="images/space.gif" height="10"/></td>
    </tr>
    <tr>
      <td><b>User Name</b></td>
      <td><html:hidden name="patient" property="username" write="true"/></td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text name="patient" property="name"/></td>
    </tr>


    <tr>
      <td><b>NHS Number</b></td>
      <td><html:text property="nhsno" name="nhsnot"/></td>
       <html:hidden property="unitcode" name="nhsnot"/>
      <logic:present name="nhsnoAlreadyExists">
        <td><b>Override Duplicate</b></td>
        <td><html:checkbox property="overrideDuplicateNhsno"/></td>
      </logic:present>
      <logic:notPresent name="nhsnoAlreadyExists">
        <html:hidden property="overrideDuplicateNhsno" value=""/>
      </logic:notPresent>
    </tr>

    <tr>
      <td><b>Email Address</b></td>
      <td><html:text name="patient" property="email"/></td>
    </tr>
    <tr>
      <td><b>Email Address Verified</b></td>
      <td>
          <logic:equal name="patient" property="emailverified" value="true"><big><font color="green">&#10004;</font></big></logic:equal>
          <logic:equal name="patient" property="emailverified" value="false"><big><font color="red">&#10008;</font></big></logic:equal>
      </td>
    </tr>
    <tr>
      <td><b>Dummy Patient</b></td>
      <td><html:checkbox name="patient" property="dummypatient" value="true"/></td>
    </tr>
    <html:hidden name="patient" property="emailverified"/>
    <html:hidden name="patient" property="firstlogon"/>
    <html:hidden name="patient" property="password"/>
    <html:hidden name="patient" property="lastlogon"/>
    <html:hidden name="patient" property="failedlogons"/>
    <html:hidden name="patient" property="accountlocked"/>
    <html:hidden name="patient" property="screenname"/>
  <tr><td></td></tr>
  <tr><td></td></tr>
  <tr align="right">

      </html:form>

      <html:form action="/control/userDelete">
          <html:hidden name="patient" property="username"/>
          <td width="10" align="left"><html:submit value="Delete Patient" style="float:left;" styleClass="btn btn-danger formbutton"/></td>
          <html:hidden property="unitcode" name="nhsnot"/>
          <html:hidden property="nhsno" name="nhsnot"/>
      </html:form>

      <html:form action="/control/resetPassword"  style="float:left;margin-left:5px;">
        <html:hidden name="patient" property="username"/>
          <td align="left"><html:submit value="Reset Password" style="float:left;" styleClass="btn formbutton"/></td>
      </html:form>

      <td>&nbsp;</td>
      <td align="right" style="vertical-align:top;"><html:submit value="Save" styleClass="btn btn-primary formbutton"/></td>

  </tr>
  <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <logic:match value="true" name="patient" property="accountlocked">
          <html:form action="/control/passwordUnlock">
              <html:hidden name="patient" property="username"/>
              <html:hidden property="nhsno" name="nhsnot"/>
              <html:hidden property="unitcode" name="nhsnot"/>
      <td align="right" style="vertical-align: top;"><html:submit value="Unlock Password" styleClass="btn formbutton"/></td>
      </html:form>
      </logic:match>
  </tr>
</table>
</div>

