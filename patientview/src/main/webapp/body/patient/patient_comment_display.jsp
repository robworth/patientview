<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

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

<p class="header">My Comments</p>

<html:errors/>

  <table cellpadding="3" >

    <tr>
      <td class="tableheader" align="center">Date</td>
      <td class="tableheader" align="center">Comment</td>
    </tr>

    <logic:present name="comments">

        <bean:size id="numComments" name="comments"/>

        <logic:iterate name="comments" id="comment">
             <tr>
                 <td class="tablecell" align="center"><bean:write name="comment" property="formattedDatestamp" /></td>
                 <td class="tablecell" align="center"><bean:write name="comment" property="body" /></td>
             </tr>
        </logic:iterate>


    </logic:present>


  </table>

<br/>

<logic:equal value="1" name="numComments">
    <html:link action="/patient/patientEnteredCommentDisplayAll">View all comments</html:link>
</logic:equal>


