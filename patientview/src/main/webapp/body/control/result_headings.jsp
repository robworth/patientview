<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Result Headings</p>

<p>The panels are ordered 1,2,3 etc. There <b>must</b> be at at least one result heading in panel 1. </p>

<p>Put the panel to be zero for the columns that you don't want to appear in the results at all.</p>

<p>The panel order is the order that the columns appear in each panel running from left to right.
If you have two results headings in the same panel that both have the same panel order then the
order of those columns in the results page will be unpredictable.</p>

<table>
    <tr>
      <td><img src="images/space.gif" height="30" /> </td>
    </tr>
    <tr>
      <td class="tableheader"><b>Heading Code</b></td>
      <td class="tableheader"><b>Heading</b></td>
      <td class="tableheader"><b>Rollover</b></td>
      <td class="tableheader"><b>Panel</b></td>
      <td class="tableheader"><b>Panel Order</b></td>
    </tr>
  <logic:iterate id="resultHeading" name="resultHeadings" >
    <html:form action="/control/resultHeadingEdit">
       <tr>
         <td class="tablecell"><html:hidden name="resultHeading" property="headingcode" write="true" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="heading" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="rollover" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="panel" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="panelorder" /></td>
         <td><html:submit value="Edit" styleClass="formbutton" /></td>
       </tr>
     </html:form>
   </logic:iterate>
       <tr>
         <td>&nbsp;</td>
       </tr>
    <html:form action="/control/resultHeadingAddInput">
       <tr>
         <td><html:submit value="Add New" styleClass="formbutton" /></td>
       </tr>
     </html:form>
 </table>

