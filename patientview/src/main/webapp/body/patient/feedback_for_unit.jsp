<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Feedback</p>

<logic:empty name="feedbacks">
    <b>No feedback comments found</b>
</logic:empty>

<logic:notEmpty name="feedbacks">

<table>
    <tr>
      <td><img src="images/space.gif" height="30" /> </td>
    </tr>
    <tr>
      <td class="tableheader"><b>Date</b></td>
      <td class="tableheader"><b>Name</b></td>
      <td class="tableheader"><b>Comment</b></td>

    </tr>
  <logic:iterate id="feedback" name="feedbacks" >
     <logic:equal value="true" name="feedback" property="makepublic">
       <tr>
           <td class="tablecell"><bean:write name="feedback" property="formattedDatestamp" /></td>

           <logic:equal value="true" name="feedback" property="anonymous">
               <td class="tablecell" >Comment is anonymous</td>
           </logic:equal>
           <logic:notEqual value="true" name="feedback" property="anonymous">
               <td class="tablecell"><bean:write name="feedback" property="name" /></td>
           </logic:notEqual>

           <td class="tablecell"><bean:write name="feedback" property="comment" /></td>
       </tr>
                                                                     </logic:equal>
   </logic:iterate>

 </table>

 </logic:notEmpty>

