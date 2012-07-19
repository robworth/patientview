<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Patient Edit</p>

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
      <td><b>Email Address</b></td>
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

    <tr align="right">
      <td><html:submit value="Edit" styleClass="formbutton"/></td>
    </tr>
  </html:form>

  <tr align="right">
    <td>&nbsp;</td>
  </tr>

  <tr align="right">
    <td>&nbsp;</td>
  </tr>

  <html:form action="/control/resetPassword">
    <html:hidden name="patient" property="username"/>
    <tr align="left">
      <td><html:submit value="Reset Password" styleClass="formbutton"/></td>
    </tr>
  </html:form>

  <logic:match value="true" name="patient" property="accountlocked">
    <tr align="right">
      <td>&nbsp;</td>
    </tr>

    <tr align="right">
      <td>&nbsp;</td>
    </tr>

    <html:form action="/control/passwordUnlock">
      <html:hidden name="patient" property="username"/>
      <tr align="left">
        <td><html:submit value="Unlock Password" styleClass="formbutton"/></td>
      </tr>
    </html:form>
  </logic:match>

  <tr align="right">
    <td>&nbsp;</td>
  </tr>

  <tr align="right">
    <td>&nbsp;</td>
  </tr>

  <html:form action="/control/userDelete">
    <html:hidden name="patient" property="username"/>
    <tr align="left">
      <td><html:submit value="Delete Patient" styleClass="formbutton"/></td>
        <html:hidden property="unitcode" name="nhsnot"/>
        <html:hidden property="nhsno" name="nhsnot"/>
    </tr>
  </html:form>

</table>


