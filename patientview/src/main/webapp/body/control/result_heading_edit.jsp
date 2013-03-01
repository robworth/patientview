<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Links Editing</h1>
</div>


<html:form action="/control/resultHeadingUpdate">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Code</b></td>
      <td><html:hidden name="resultHeading" property="headingcode" write="true"/></td>

    </tr>
    <tr>
      <td><b>Heading</b></td>
      <td><html:text name="resultHeading" property="heading" maxlength="30"/></td>
    </tr>
    <tr>
      <td><b>Rollover</b></td>
      <td><html:text name="resultHeading" property="rollover" maxlength="50"/></td>
    </tr>
    <tr>
      <td><b>Link</b></td>
      <td><html:text name="resultHeading" property="link" maxlength="100"/></td>
    </tr>
    <tr>
      <td><b>Panel</b></td>
      <td><html:text name="resultHeading" property="panel" /></td>
    </tr>
    <tr>
      <td><b>Panel Order</b></td>
      <td><html:text name="resultHeading" property="panelorder" /></td>
    </tr>
    <tr>
      <td><html:submit value="Update" styleClass="btn"/></td>
    </tr>
</html:form>

 </table>

</div>
</div>

