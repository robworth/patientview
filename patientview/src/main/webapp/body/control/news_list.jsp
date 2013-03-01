<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">

<div class="page-header">
    <h1>News</h1>
</div>

<html:errors />


<table cellpadding="3" class="table table-bordered table-striped">

    <thead>
  <logic:notEmpty name="newses">  <tr class="tableheader">
    <th class="tableheader" rowspan="2">Headline</th>
    <th class="tableheader" rowspan="2">Posted</th>
    <logic:present role="superadmin">
      <th class="tableheader" colspan="4" align="center">Available to</th>
    </logic:present>
    <logic:present role="unitadmin">
      <th class="tableheader" colspan="2" align="center">Available to</th>
    </logic:present>
      <th rowspan="2"></th>
      
  </tr>
  <tr class="tableheader">
    <logic:present role="superadmin">
      <th class="tableheader">Unit(s)</th>
    </logic:present>
    <th class="tableheader">Admins</th>
    <th class="tableheader">Patients</th>
    <logic:present role="superadmin"><th class="tableheader">Public</th></logic:present>
  </tr>
  </thead>

  <logic:iterate id="news" name="newses" >
     <tr>

       <td class="tablecell">
         <html:link action="/control/newsEdit" paramId="id" paramName="news" paramProperty="id">
           <bean:write name="news" property="headline" />
         </html:link>
       </td>

       <td class="tablecell"><bean:write name="news" property="formattedDatestamp"/></td>

       <logic:present role="superadmin">
         <td class="tablecell"><bean:write name="news" property="unitcode" /></td>
       </logic:present>
       
       <td class="tablecell" align="center">
         <logic:equal value="true" name="news" property="admin"><font color="green">&#10004;</font></logic:equal>
         <logic:notEqual value="true" name="news" property="admin"><font color="red">&#10008;</font></logic:notEqual>
       </td>

       <td class="tablecell" align="center">
         <logic:equal value="true" name="news" property="patient"><font color="green">&#10004;</font></logic:equal>
         <logic:notEqual value="true" name="news" property="patient"><font color="red">&#10008;</font></logic:notEqual>
       </td>

       <logic:present role="superadmin">
         <td class="tablecell" align="center">
           <logic:equal value="true" name="news" property="everyone"><font color="green">&#10004;</font></logic:equal>
           <logic:notEqual value="true" name="news" property="everyone"><font color="red">&#10008;</font></logic:notEqual>
         </td>
       </logic:present>

       <td class="tablecell" align="center" valign="center">
         <html:form action="/control/newsDelete">
           <html:hidden name="news" property="id" />
           <html:submit value="Delete" styleClass="btn" />
         </html:form>
       </td>

     </tr>
  </logic:iterate>
</logic:notEmpty>

</table>
 
      <html:form action="/control/newsAdd">
        <html:submit value="Add New" styleClass="btn" />
      </html:form>

</div>
</div>
