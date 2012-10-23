<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Patient</h1>
</div>

<html:errors />

<logic:present name="userAlreadyExists" >
  <p><font color="red">The username <b><bean:write name="userAlreadyExists" /></b> you entered is already being used by another user. Please pick another.</font></p>
</logic:present>

<logic:present name="nhsnoAlreadyExists" >
  <p><font color="red">The NHS number <b><bean:write name="nhsnoAlreadyExists" /></b> you entered is already allocated to another user. <br/> You may override this using the checkbox below. But please use this with care!!</font></p>
</logic:present>

<logic:present name="patientAlreadyInUnit" >
  <p><font color="red">The patient with NHS number <b><bean:write name="patientAlreadyInUnit" /></b> already exists in your unit. You can't add another patient with the same NHS no to your unit. Please check the NHS number.</font></p>
</logic:present>

<html:form action="/control/patientAdd">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>User Name</b></td>
      <td><html:text property="username" /></td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text property="name" /></td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td><html:text property="nhsno" /></td>
      <logic:present name="nhsnoAlreadyExists" >
        <td><b>Override Duplicate</b></td>
        <td><html:checkbox property="overrideDuplicateNhsno"/></td>
      </logic:present>
      <logic:notPresent name="nhsnoAlreadyExists" >
        <html:hidden property="overrideDuplicateNhsno" value="" />
      </logic:notPresent>
    </tr>
    <tr>
      <td><b>Email Address</b></td>
      <td><html:text property="email" /></td>
    </tr>
    <tr>
      <td><b>
          <logic:present tenancy="rpv">Renal Unit</logic:present><logic:present tenancy="ibd">IBD Unit</logic:present>
      </b></td>
      <td><html:select property="unitcode">
             <html:options collection="units" property="unitcode" labelProperty="name"/>
          </html:select></td>
    </tr>
    <tr>
      <td><b>Dummy Patient</b></td>
      <td><html:checkbox property="dummypatient" value="true" /></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Add" styleClass="btn" /></td>
    </tr>
 </table>

</html:form>
</div>
</div>