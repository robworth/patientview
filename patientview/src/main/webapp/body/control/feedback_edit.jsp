<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Feedback</p>

<logic:present name="feedback">

<table>
    <tr>
      <td><img src="images/space.gif" height="30" /> </td>
    </tr>
    <tr>
      <td class="tableheader"><b>Date</b></td>
      <td class="tableheader"><b>Comment</b></td>
      <td class="tableheader"><b>Name</b></td>
      <td class="tableheader"><b>User</b></td>
      <td class="tableheader"><b>NHS No</b></td>
      <td class="tableheader"><b>Is Public</b></td>

    </tr>
        <html:form action="/control/feedbackEditUpdate">
          <html:hidden property="id" name="feedback"/>
       <tr>
           <td class="tablecell"><bean:write name="feedback" property="formattedDatestamp" /></td>
           <td class="tablecell"><bean:write name="feedback" property="comment" /></td>
         <logic:equal value="true" name="feedback" property="anonymous">
             <td class="tablecell" colspan="3">Comment is anonymous</td>

         </logic:equal>
         <logic:notEqual value="true" name="feedback" property="anonymous">
             <td class="tablecell"><bean:write name="feedback" property="name" /></td>
             <td class="tablecell"><bean:write name="feedback" property="username" /></td>
             <td class="tablecell"><bean:write name="feedback" property="nhsno" /></td>
         </logic:notEqual>
         <td class="tablecell">
            <html:checkbox property="makepublic" name="feedback" value="true"/>
         </td>
         <td class="tablecell">
           <html:textarea rows="6" cols="30" property="commentedited" name="feedback" />
         </td>
       </tr>
       <tr><td>&nbsp;</td></tr>
       <tr>
           <td colspan="7" align="right"><html:submit value="Update" styleClass="formbutton" /></td>
       </tr>     
    </html:form>
 </table>

 </logic:present>

