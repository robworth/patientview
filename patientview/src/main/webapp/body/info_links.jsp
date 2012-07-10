<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<br />

<div align="center">

  <table border="0" width="690">

      <tr valign="top">
        <td colspan="4"><bean:message key="cautionary.alllinks" /></td>
      </tr>

      <tr valign="top">
        <td colspan="4"><img src="images/space.gif" height="5"/></td>
      </tr>

    <logic:present name="edtaCodes">
    <logic:notEmpty name="edtaCodes">
      <tr valign="top">
        <td class="tableheader" colspan="4">Diagnosis Information Links</td>
      </tr>

      <tr valign="top">
        <td valign="top" class="tablecell"><b>Diagnosis</b></td>
        <td valign="top" class="tablecell"><b>EDTA</b></td>
        <td valign="top" class="tablecell"><b>Patient Links</b></td>
        <td valign="top" class="tablecell"><b>Medical Links</b></td>
      </tr>

      <logic:iterate id="edtaCode" name="edtaCodes">
        <tr>
          <td valign="top" class="tablecell"><b><bean:write name="edtaCode" property="description"/></b></td>
          <td valign="top" class="tablecell"><b><bean:write name="edtaCode" property="edtaCode"/></b></td>
          <td valign="top" class="tablecell">
            <logic:notEmpty name="edtaCode" property="patientLink01">
              <a href="<bean:write name="edtaCode" property="patientLink01"/>"><bean:write name="edtaCode" property="patientLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="patientLink02">
              <a href="<bean:write name="edtaCode" property="patientLink02"/>"><bean:write name="edtaCode" property="patientLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="patientLink03">
              <a href="<bean:write name="edtaCode" property="patientLink03"/>"><bean:write name="edtaCode" property="patientLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="patientLink04">
              <a href="<bean:write name="edtaCode" property="patientLink04"/>"><bean:write name="edtaCode" property="patientLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="patientLink05">
              <a href="<bean:write name="edtaCode" property="patientLink05"/>"><bean:write name="edtaCode" property="patientLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="patientLink06">
              <a href="<bean:write name="edtaCode" property="patientLink06"/>"><bean:write name="edtaCode" property="patientLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
          <td valign="top" class="tablecell">
            <logic:notEmpty name="edtaCode" property="medicalLink01">
              <a href="<bean:write name="edtaCode" property="medicalLink01"/>"><bean:write name="edtaCode" property="medicalLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="medicalLink02">
              <a href="<bean:write name="edtaCode" property="medicalLink02"/>"><bean:write name="edtaCode" property="medicalLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="medicalLink03">
              <a href="<bean:write name="edtaCode" property="medicalLink03"/>"><bean:write name="edtaCode" property="medicalLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="medicalLink04">
              <a href="<bean:write name="edtaCode" property="medicalLink04"/>"><bean:write name="edtaCode" property="medicalLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="medicalLink05">
              <a href="<bean:write name="edtaCode" property="medicalLink05"/>"><bean:write name="edtaCode" property="medicalLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="edtaCode" property="medicalLink06">
              <a href="<bean:write name="edtaCode" property="medicalLink06"/>"><bean:write name="edtaCode" property="medicalLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
        </tr>
      </logic:iterate>

      <tr valign="top">
        <td valign="top" colspan="3">&nbsp;</td>
      </tr>

    </logic:notEmpty>
    </logic:present>

    <logic:present name="treatmentCodes">
    <logic:notEmpty name="treatmentCodes">
      <tr valign="top">
        <td class="tableheader" colspan="4">Treatment Information Links</td>
      </tr>

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
              <a href="<bean:write name="treatmentCode" property="patientLink01"/>"><bean:write name="treatmentCode" property="patientLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="patientLink02">
              <a href="<bean:write name="treatmentCode" property="patientLink02"/>"><bean:write name="treatmentCode" property="patientLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="patientLink03">
              <a href="<bean:write name="treatmentCode" property="patientLink03"/>"><bean:write name="treatmentCode" property="patientLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="patientLink04">
              <a href="<bean:write name="treatmentCode" property="patientLink04"/>"><bean:write name="treatmentCode" property="patientLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="patientLink05">
              <a href="<bean:write name="treatmentCode" property="patientLink05"/>"><bean:write name="treatmentCode" property="patientLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="patientLink06">
              <a href="<bean:write name="treatmentCode" property="patientLink06"/>"><bean:write name="treatmentCode" property="patientLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
          <td valign="top" class="tablecell">
            <logic:notEmpty name="treatmentCode" property="medicalLink01">
              <a href="<bean:write name="treatmentCode" property="medicalLink01"/>"><bean:write name="treatmentCode" property="medicalLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="medicalLink02">
              <a href="<bean:write name="treatmentCode" property="medicalLink02"/>"><bean:write name="treatmentCode" property="medicalLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="medicalLink03">
              <a href="<bean:write name="treatmentCode" property="medicalLink03"/>"><bean:write name="treatmentCode" property="medicalLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="medicalLink04">
              <a href="<bean:write name="treatmentCode" property="medicalLink04"/>"><bean:write name="treatmentCode" property="medicalLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="medicalLink05">
              <a href="<bean:write name="treatmentCode" property="medicalLink05"/>"><bean:write name="treatmentCode" property="medicalLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="treatmentCode" property="medicalLink06">
              <a href="<bean:write name="treatmentCode" property="medicalLink06"/>"><bean:write name="treatmentCode" property="medicalLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
        </tr>
      </logic:iterate>

      <tr valign="top">
        <td valign="top" colspan="3">&nbsp;</td>
      </tr>

    </logic:notEmpty>
    </logic:present>

    <logic:present name="staticCodes">
    <logic:notEmpty name="staticCodes">
      <tr valign="top">
        <td class="tableheader" colspan="4">General Information Links</td>
      </tr>

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
              <a href="<bean:write name="staticCode" property="patientLink01"/>"><bean:write name="staticCode" property="patientLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="patientLink02">
              <a href="<bean:write name="staticCode" property="patientLink02"/>"><bean:write name="staticCode" property="patientLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="patientLink03">
              <a href="<bean:write name="staticCode" property="patientLink03"/>"><bean:write name="staticCode" property="patientLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="patientLink04">
              <a href="<bean:write name="staticCode" property="patientLink04"/>"><bean:write name="staticCode" property="patientLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="patientLink05">
              <a href="<bean:write name="staticCode" property="patientLink05"/>"><bean:write name="staticCode" property="patientLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="patientLink06">
              <a href="<bean:write name="staticCode" property="patientLink06"/>"><bean:write name="staticCode" property="patientLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
          <td valign="top" class="tablecell">
            <logic:notEmpty name="staticCode" property="medicalLink01">
              <a href="<bean:write name="staticCode" property="medicalLink01"/>"><bean:write name="staticCode" property="medicalLinkText01"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="medicalLink02">
              <a href="<bean:write name="staticCode" property="medicalLink02"/>"><bean:write name="staticCode" property="medicalLinkText02"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="medicalLink03">
              <a href="<bean:write name="staticCode" property="medicalLink03"/>"><bean:write name="staticCode" property="medicalLinkText03"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="medicalLink04">
              <a href="<bean:write name="staticCode" property="medicalLink04"/>"><bean:write name="staticCode" property="medicalLinkText04"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="medicalLink05">
              <a href="<bean:write name="staticCode" property="medicalLink05"/>"><bean:write name="staticCode" property="medicalLinkText05"/></a><br />
            </logic:notEmpty>
            <logic:notEmpty name="staticCode" property="medicalLink06">
              <a href="<bean:write name="staticCode" property="medicalLink06"/>"><bean:write name="staticCode" property="medicalLinkText06"/></a><br />
            </logic:notEmpty>
          </td>
        </tr>
      </logic:iterate>

    </logic:notEmpty>
    </logic:present>

  </table>

</div>