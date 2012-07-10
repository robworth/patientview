<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">News</p>

<html:errors />


<table cellpadding="3" >

  <logic:notEmpty name="newses">  <tr class="tableheader">
    <td class="tableheader" rowspan="2">Headline</td>
    <td class="tableheader" rowspan="2">Posted</td>
    <logic:present role="superadmin">
      <td class="tableheader" colspan="4" align="center">Available to</td>
    </logic:present>
    <logic:present role="unitadmin">
      <td class="tableheader" colspan="2" align="center">Available to</td>
    </logic:present>
  </tr>
  <tr class="tableheader">
    <logic:present role="superadmin">
      <td class="tableheader">Unit(s)</td>
    </logic:present>
    <td class="tableheader">Admins</td>
    <td class="tableheader">Patients</td>
    <logic:present role="superadmin"><td class="tableheader">Public</td></logic:present>
  </tr>


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
           <html:submit value="Delete" styleClass="formbutton" />
         </html:form>
       </td>

     </tr>
  </logic:iterate>
</logic:notEmpty>


  <tr>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td>
      <html:form action="/control/newsAdd">
        <html:submit value="Add New" styleClass="formbutton" />
      </html:form>
    </td>
  </tr>

</table>

