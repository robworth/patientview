<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">
<div class="page-header">
    <h1>Result Headings</h1>
</div>

<p>The panels are ordered 1,2,3 etc. There <b>must</b> be at at least one result heading in panel 1. </p>

<p>Put the panel to be zero for the columns that you don't want to appear in the results at all.</p>

<p>The panel order is the order that the columns appear in each panel running from left to right.
If you have two results headings in the same panel that both have the same panel order then the
order of those columns in the results page will be unpredictable.</p>

<table class="table table-bordered table-striped">
    <thead>
        <tr>
            <th class="tableheader">Heading Code</th>
            <th class="tableheader">Heading</th>
            <th class="tableheader">Rollover</th>
            <th class="tableheader">Panel</th>
            <th class="tableheader">Panel Order</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
  <logic:iterate id="resultHeading" name="resultHeadings" >
    <html:form action="/control/resultHeadingEdit">
       <tr>
         <td class="tablecell"><html:hidden name="resultHeading" property="headingcode" write="true" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="heading" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="rollover" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="panel" /></td>
         <td class="tablecell"><bean:write name="resultHeading" property="panelorder" /></td>
         <td><html:submit value="Edit" styleClass="btn" /></td>
       </tr>
     </html:form>
   </logic:iterate>
    </tbody>
</table>
    
    <html:form action="/control/resultHeadingAddInput">
       <html:submit value="Add New" styleClass="btn" />
     </html:form>

</div>
</div>
