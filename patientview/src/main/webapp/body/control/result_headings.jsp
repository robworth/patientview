<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

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
