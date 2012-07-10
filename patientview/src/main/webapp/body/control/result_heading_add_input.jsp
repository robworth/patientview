<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Result Heading Add</p>

<html:errors />

<html:form action="/control/resultHeadingAdd">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Code</b></td>
      <td><html:text property="headingcode" /></td>
    </tr>
    <tr>
      <td><b>Heading</b></td>
      <td><html:text property="heading" /></td>
    </tr>
    <tr>
      <td><b>Rollover</b></td>
      <td><html:text property="rollover" /></td>
    </tr>
    <tr>
      <td><b>Link</b></td>
      <td><html:text property="link" /></td>
    </tr>
    <tr>
      <td><b>Panel</b></td>
      <td><html:text property="panel" /></td>
    </tr>
    <tr>
      <td><b>Panel Order</b></td>
      <td><html:text property="panelorder" /></td>
    </tr>
     <tr>
       <td><html:submit value="Add" styleClass="formbutton" /></td>
     </tr>
 </table>

</html:form>
