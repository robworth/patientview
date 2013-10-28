<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

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
    <h1>Genetics</h1>
</div>

<table width="440" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

<tr valign="top">
  <td class="tablecellbold" style="width: 25%">Has a genetic test been done?</td>
  <td class="tablecell">
      <logic:present name="genetics" property="testsDone">
        <bean:write name="genetics" property="testsDone.value"/>
      </logic:present>
  </td>
</tr>
<tr valign="top">
  <td class="tablecellbold" style="width: 25%">Lab where the test was done:</td>
  <td class="tablecell"><bean:write name="genetics" property="labWhereTestWasDone"/></td>
</tr>
<tr valign="top">
  <td class="tablecellbold" style="width: 25%">Who was the test done on: </td>
  <td class="tablecell"><bean:write name="genetics" property="testDoneOn"/></td>
</tr>
<tr valign="top">
  <td class="tablecellbold" style="width: 25%">Result ref number: </td>
  <td class="tablecell"><bean:write name="genetics" property="referenceNumber"/></td>
</tr>
<tr valign="top">
  <td class="tablecellbold" style="width: 25%">What did the results show? </td>
  <td class="tablecell"><bean:write name="genetics" property="whatResultsShowed"/></td>
</tr>
<tr valign="top">
  <td class="tablecellbold" style="width: 25%">Summarise the key evidence for the diagnosis: </td>
  <td class="tablecell"><bean:write name="genetics" property="keyEvidence"/></td>
</tr>

</table>










