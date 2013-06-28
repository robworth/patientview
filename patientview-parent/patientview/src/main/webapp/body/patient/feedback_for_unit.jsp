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

