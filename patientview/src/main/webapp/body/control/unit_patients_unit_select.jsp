<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Select Unit</p>


<html:form action="/control/unitPatients">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Renal Unit</b></td>
      <td><html:select property="unitcode">
            <logic:present role="superadmin">
              <html:option value="" >-- All Units --</html:option>
            </logic:present>
            <html:options collection="units" property="unitcode" labelProperty="name"/>
          </html:select></td>
    </tr>
    <tr>
      <td><b>NHS Number</b></td>
      <td><html:text property="nhsno" /></td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text property="name" /></td>
    </tr>
    <tr>
      <td><b>Show GPs</b></td>
      <td><html:checkbox property="showgps" value="true" /></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Select" styleClass="formbutton" /></td>
    </tr>
 </table>

</html:form>
