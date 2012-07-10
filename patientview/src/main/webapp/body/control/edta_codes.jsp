<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">EDTA Links</p>

<table>
    <tr>
      <td><img src="images/space.gif" height="30" /> </td>
    </tr>
    <tr>
      <td class="tableheader"><b>EDTA Code</b></td>
      <td class="tableheader"><b>Description</b></td>
    </tr>
  <logic:iterate id="edtaCode" name="edtaCodes" >
    <html:form action="/control/edtaCodeEdit">
       <tr>
         <td class="tablecell"><bean:write name="edtaCode" property="edtaCode" /></td>
         <td class="tablecell"><bean:write name="edtaCode" property="description" /></td>
         <html:hidden property="edtaCode" name="edtaCode" />
         <html:hidden property="linkType" name="codeType" />
         <td><html:submit value="Edit" styleClass="formbutton" /></td>
       </tr>
     </html:form>
   </logic:iterate>
       <tr>
         <td>&nbsp;</td>
       </tr>
    <html:form action="/control/edtaCodeAddInput">
      <html:hidden property="linkType" name="codeType"/>
       <tr>
         <td><html:submit value="Add New" styleClass="formbutton" /></td>
       </tr>
     </html:form>
 </table>

