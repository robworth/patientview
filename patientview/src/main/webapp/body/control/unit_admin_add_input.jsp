<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
    <div class="page-header">
        <h1>Unit Admins</h1>
    </div>

<html:errors />

<logic:present name="userAlreadyExists" >
  <p><font color="red">The username <b><bean:write name="userAlreadyExists" /></b> you have entered is already being used by another user. Please pick another.</font></p>
</logic:present>

<html:form action="/control/unitAdminAdd">
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
      <td><b>Email Address</b></td>
      <td><html:text property="email" /></td>
    </tr>
    <tr>
      <td><b>Role</b></td>
      <td><html:select property="role" onchange="if(document.patientForm.role.value == 'superadmin') {document.patientForm.unitcode.value = '';}">
             <html:option value="">-- Please select an admin type --</html:option>
             <logic:present role="superadmin">
               <html:option value="superadmin">Super Admin</html:option>
             </logic:present>
             <html:option value="unitadmin">Unit Admin</html:option>
             <html:option value="unitstaff">Unit Staff</html:option>
          </html:select></td>
    </tr>
    <tr>
      <td><b><logic:present tenancy="rpv">Renal Unit</logic:present><logic:present tenancy="ibd">IBD Unit</logic:present></b></td>
      <td><html:select property="unitcode">
             <logic:present role="superadmin">
               <html:option value="">None</html:option>
             </logic:present>
             <html:options collection="units" property="unitcode" labelProperty="name"/>
          </html:select></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Add" styleClass="btn" /></td>
    </tr>
 </table>

</html:form>
</div>
</div>
