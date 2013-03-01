<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Feedback</h1>
</div>

<logic:empty name="feedbacks">
    <div class="alert-heading alert">
        No feedback comments found
    </div>
</logic:empty>

<logic:notEmpty name="feedbacks">

<table class="table table-bordered table-striped">
    <thead>
        <tr>
          <th class="tableheader"><b>Date</b></th>
          <th class="tableheader"><b>Name</b></th>
          <th class="tableheader"><b>Comment</b></th>
        </tr>
    </thead>
    <tbody>
  <logic:iterate id="feedback" name="feedbacks" >
     <logic:equal value="true" name="feedback" property="makepublic">
       <tr>
           <td class="tablecell"><bean:write name="feedback" property="formattedDatestamp" /></td>


               <td class="tablecell" ><logic:equal value="true" name="feedback" property="anonymous">Comment is anonymous</logic:equal></td>


               <td class="tablecell"><logic:notEqual value="true" name="feedback" property="anonymous"><bean:write name="feedback" property="name" /></logic:notEqual></td>


           <td class="tablecell"><bean:write name="feedback" property="comment" /></td>
       </tr>
     </logic:equal>
   </logic:iterate>
     </tbody>
 </table>

 </logic:notEmpty>

