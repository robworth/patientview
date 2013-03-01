<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Select Unit</h1>
</div>


<html:form action="/control/unitUsers">
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b><logic:present tenancy="rpv">Renal Unit</logic:present><logic:present tenancy="ibd">IBD Unit</logic:present></b></td>
      <td><html:select property="unitcode">
             <html:options collection="units" property="unitcode" labelProperty="name"/>
          </html:select></td>
    </tr>
    <tr align="right">
      <td><html:submit value="Select" styleClass="btn" /></td>
    </tr>
 </table>

</html:form>
</div>
</div>
