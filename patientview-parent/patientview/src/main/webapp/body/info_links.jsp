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
    <h1>Information Links</h1>
</div>

<p>
    <bean:message key="cautionary.alllinks" />
</p>

<logic:present name="edtaCodes">
<logic:notEmpty name="edtaCodes">
<h2>Diagnosis Information Links</h2>
<table class="table table-bordered table-striped">

  <thead valign="top">
    <tr>
        <th>Diagnosis</th>
        <th>EDTA</th>
        <th>Patient Links</th>
        <th>Medical Links</th>
    </tr>
  </thead>

  <logic:iterate id="edtaCode" name="edtaCodes">
    <tr>
      <td valign="top" class="tablecell"><b><bean:write name="edtaCode" property="description"/></b></td>
      <td valign="top" class="tablecell"><b><bean:write name="edtaCode" property="edtaCode"/></b></td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="edtaCode" property="patientLink01">
          <a href="<bean:write name="edtaCode" property="patientLink01"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink02">
          <a href="<bean:write name="edtaCode" property="patientLink02"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink03">
          <a href="<bean:write name="edtaCode" property="patientLink03"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink04">
          <a href="<bean:write name="edtaCode" property="patientLink04"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink05">
          <a href="<bean:write name="edtaCode" property="patientLink05"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink06">
          <a href="<bean:write name="edtaCode" property="patientLink06"/>" target="_blank"><bean:write name="edtaCode" property="patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="edtaCode" property="medicalLink01">
          <a href="<bean:write name="edtaCode" property="medicalLink01"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink02">
          <a href="<bean:write name="edtaCode" property="medicalLink02"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink03">
          <a href="<bean:write name="edtaCode" property="medicalLink03"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink04">
          <a href="<bean:write name="edtaCode" property="medicalLink04"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink05">
          <a href="<bean:write name="edtaCode" property="medicalLink05"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink06">
          <a href="<bean:write name="edtaCode" property="medicalLink06"/>" target="_blank"><bean:write name="edtaCode" property="medicalLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
  </logic:iterate>
</table>
</logic:notEmpty>
</logic:present>

<logic:present name="treatmentCodes">
<logic:notEmpty name="treatmentCodes">
<h2>Treatment Information Links</h2>

<table class="table table-bordered table-striped">
  <tr valign="top">
    <td valign="top" class="tablecell"><b>Treatment</b></td>
    <td valign="top" class="tablecell"><b>EDTA</b></td>
    <td valign="top" class="tablecell"><b>Patient Links</b></td>
    <td valign="top" class="tablecell"><b>Medical Links</b></td>
  </tr>

  <logic:iterate id="treatmentCode" name="treatmentCodes">
    <tr>
      <td valign="top" class="tablecell"><b><bean:write name="treatmentCode" property="description"/></b></td>
      <td valign="top" class="tablecell"><b><bean:write name="treatmentCode" property="edtaCode"/></b></td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="treatmentCode" property="patientLink01">
          <a href="<bean:write name="treatmentCode" property="patientLink01"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="patientLink02">
          <a href="<bean:write name="treatmentCode" property="patientLink02"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="patientLink03">
          <a href="<bean:write name="treatmentCode" property="patientLink03"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="patientLink04">
          <a href="<bean:write name="treatmentCode" property="patientLink04"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="patientLink05">
          <a href="<bean:write name="treatmentCode" property="patientLink05"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="patientLink06">
          <a href="<bean:write name="treatmentCode" property="patientLink06"/>" target="_blank"><bean:write name="treatmentCode" property="patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="treatmentCode" property="medicalLink01">
          <a href="<bean:write name="treatmentCode" property="medicalLink01"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="medicalLink02">
          <a href="<bean:write name="treatmentCode" property="medicalLink02"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="medicalLink03">
          <a href="<bean:write name="treatmentCode" property="medicalLink03"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="medicalLink04">
          <a href="<bean:write name="treatmentCode" property="medicalLink04"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="medicalLink05">
          <a href="<bean:write name="treatmentCode" property="medicalLink05"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="treatmentCode" property="medicalLink06">
          <a href="<bean:write name="treatmentCode" property="medicalLink06"/>" target="_blank"><bean:write name="treatmentCode" property="medicalLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
  </logic:iterate>

</table>

</logic:notEmpty>
</logic:present>

<logic:present name="staticCodes">
<logic:notEmpty name="staticCodes">
<h2>General Information Links</h2>

<table class="table table-bordered table-striped">
<tr valign="top">
<td valign="top" class="tablecell" colspan="2">&nbsp;</td>
<td valign="top" class="tablecell"><b>Patient Links</b></td>
<td valign="top" class="tablecell"><b>Medical Links</b></td>
</tr>

  <logic:iterate id="staticCode" name="staticCodes">
    <tr>
      <td valign="top" class="tablecell" colspan="2"><b><bean:write name="staticCode" property="description"/></b></td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="staticCode" property="patientLink01">
          <a href="<bean:write name="staticCode" property="patientLink01"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="patientLink02">
          <a href="<bean:write name="staticCode" property="patientLink02"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="patientLink03">
          <a href="<bean:write name="staticCode" property="patientLink03"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="patientLink04">
          <a href="<bean:write name="staticCode" property="patientLink04"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="patientLink05">
          <a href="<bean:write name="staticCode" property="patientLink05"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="patientLink06">
          <a href="<bean:write name="staticCode" property="patientLink06"/>" target="_blank"><bean:write name="staticCode" property="patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="staticCode" property="medicalLink01">
          <a href="<bean:write name="staticCode" property="medicalLink01"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="medicalLink02">
          <a href="<bean:write name="staticCode" property="medicalLink02"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="medicalLink03">
          <a href="<bean:write name="staticCode" property="medicalLink03"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="medicalLink04">
          <a href="<bean:write name="staticCode" property="medicalLink04"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="medicalLink05">
          <a href="<bean:write name="staticCode" property="medicalLink05"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticCode" property="medicalLink06">
          <a href="<bean:write name="staticCode" property="medicalLink06"/>" target="_blank"><bean:write name="staticCode" property="medicalLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
  </logic:iterate>
</table>
</logic:notEmpty>
</logic:present>


