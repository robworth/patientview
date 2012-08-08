<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Feedback</h1>
</div>

<logic:empty name="feedbacks">
    <b>No feedback comments found</b>
</logic:empty>

<logic:notEmpty name="feedbacks">

<table class="table table-bordered table-striped">
    <tr>
      <td class="tableheader"><b>Date</b></td>
      <td class="tableheader"><b>Original Comment</b></td>
      <td class="tableheader"><b>Edited Comment</b></td>
      <td class="tableheader"><b>Name</b></td>
      <td class="tableheader"><b>User</b></td>
      <td class="tableheader"><b>NHS No</b></td>
      <td class="tableheader"><b>Is Public</b></td>
      <td class="tableheader"></td>
    </tr>
  <logic:iterate id="feedback" name="feedbacks" >

       <tr>
           <td class="tablecell"><bean:write name="feedback" property="formattedDatestamp" /></td>
           <td class="tablecell"><bean:write name="feedback" property="comment" /></td>
           <td class="tablecell"><bean:write name="feedback" property="commentedited" /></td>
         <logic:equal value="true" name="feedback" property="anonymous">
             <td class="tablecell" colspan="3">Comment is anonymous</td>

         </logic:equal>
         <logic:notEqual value="true" name="feedback" property="anonymous">
             <td class="tablecell"><bean:write name="feedback" property="name" /></td>
             <td class="tablecell"><bean:write name="feedback" property="username" /></td>
             <td class="tablecell"><bean:write name="feedback" property="nhsno" /></td>
         </logic:notEqual>
         <td class="tablecell">
           <logic:equal value="true" name="feedback" property="makepublic"><font color="green">&#10004;</font></logic:equal>
           <logic:notEqual value="true" name="feedback" property="makepublic"><font color="red">&#10008;</font></logic:notEqual>
         </td>
         <td class="tablecell">
             <html:form action="/control/feedbackEditDisplay">
                 <html:hidden property="id" name="feedback"/>
                  <html:submit value="Edit" styleClass="btn" />
             </html:form>
         </td>
       </tr>

   </logic:iterate>

 </table>

 </logic:notEmpty>

</div>
</div>