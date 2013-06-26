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
    <h1>Feedback</h1>
</div>

<logic:present name="feedback">

<table class="table table-bordered table-striped">
    <tr>
      <td class="tableheader"><b>Date</b></td>
      <td class="tableheader"><b>Comment</b></td>
      <td class="tableheader"><b>Name</b></td>
      <td class="tableheader"><b>User</b></td>
      <td class="tableheader"><b>NHS No</b></td>
      <td class="tableheader"><b>Is Public</b></td>
      <td class="tableheader"></td>
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
       <tr>
           <td colspan="7" align="right"><html:submit value="Update" styleClass="btn" /></td>
       </tr>
    </html:form>
 </table>

 </logic:present>
</div>
</div>
