<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>EDTA Links</h1>
</div>

<table class="table table-bordered table-striped"> 
    <thead>
        <tr>
            <th class="tableheader">EDTA Code</th>
            <th class="tableheader">Description</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
  <logic:iterate id="edtaCode" name="edtaCodes" >
    <html:form action="/control/edtaCodeEdit">
       <tr>
         <td class="tablecell"><bean:write name="edtaCode" property="edtaCode" /></td>
         <td class="tablecell"><bean:write name="edtaCode" property="description" /></td>
         <html:hidden property="edtaCode" name="edtaCode" />
         <html:hidden property="linkType" name="codeType" />
         <td><html:submit value="Edit" styleClass="btn" /></td>
       </tr>
     </html:form>
   </logic:iterate>
    </tbody>
 </table>

    <html:form action="/control/edtaCodeAddInput">
      <html:hidden property="linkType" name="codeType"/>
       <html:submit value="Add New" styleClass="btn" />
     </html:form>

</div>
</div>
