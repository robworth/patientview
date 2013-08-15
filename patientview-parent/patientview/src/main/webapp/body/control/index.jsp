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
 <logic:present name="newses">
  <logic:notEmpty name="newses">
    <div  class="page-header">
        <h1>News</h1>
    </div>


    <table cellpadding="3" width="90%">

      <tr>
        <td colspan="2"><hr class="thinblue"/></td>
      </tr>

    <logic:iterate id="news" name="newses" >
      <tr>
        <td><b><bean:write name="news" property="headline" /></b></td>
        <td align="right" valign="center"><bean:write name="news" property="formattedDatestamp"/></td>
      </tr>
      <tr>
        <td colspan="2" ><bean:write name="news" property="bodyForHtml" filter="false"/></td>
      </tr>
      <tr>
        <td colspan="2"><hr class="thinblue"/></td>
      </tr>
    </logic:iterate>

    </table>

  </logic:notEmpty>

  <logic:empty name="newses">
    <p class="header">No news.</p>
  </logic:empty>
</logic:present>
</div>

</div> <%--Close row from top_nav.jsp--%>
