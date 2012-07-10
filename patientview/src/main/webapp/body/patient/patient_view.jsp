<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div align="left">
The links on this page are to information that is mainly written for patients, but it is often very useful for healthcare staff too. Almost all links are to other websites. These links have been chosen by us, but the information is not written by us.
</div>

<br/>

<div align="center">

  <table width="690">

  <logic:notEmpty name="patientDetails">

  <logic:iterate id="patientDetail" name="patientDetails">

    <logic:present name="patientDetail" property="unit">
    <logic:present name="patientDetail" property="patient">
      <tr valign="top">
        <td class="tableheader" colspan="3">Patient Details for
          <bean:write name="patientDetail" property="patient.forename"/>
          <bean:write name="patientDetail" property="patient.surname"/> &nbsp;&nbsp;- &nbsp;&nbsp;uploaded by unit: <bean:write name="patientDetail" property="unit.shortname"/>
        </td>
      </tr>
    </logic:present>
    </logic:present>

    <logic:notPresent name="patientDetail" property="edtaDiagnosis">
      <tr>
        <td valign="top">Primary Diagnosis:</td>
        <td valign="top"><b>No primary diagnosis has been loaded</b></td>
      </tr>
    </logic:notPresent>

    <logic:present name="patientDetail" property="edtaDiagnosis">
    <tr>
      <td valign="top" class="tablecell">Primary Diagnosis:</td>
      <td valign="top" class="tablecell"><b><bean:write name="patientDetail" property="edtaDiagnosis.description"/></b></td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink01">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink01"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink02">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink02"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink03">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink03"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink04">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink04"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink05">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink05"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaDiagnosis.patientLink06">
           <a href="<bean:write name="patientDetail" property="edtaDiagnosis.patientLink06"/>" target="_blank"><bean:write name="patientDetail" property="edtaDiagnosis.patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
    </logic:present>


    <logic:notPresent name="patientDetail" property="edtaTreatment">
    <tr>
      <td valign="top" class="tablecell">Treatment:</td>
      <td valign="top" class="tablecell"><b>No treatment has been uploaded</b></td>
    </tr>
    </logic:notPresent>

    <logic:present name="patientDetail" property="edtaTreatment">
    <tr>
      <td valign="top" class="tablecell">Treatment:</td>
      <td valign="top" class="tablecell"><b><bean:write name="patientDetail" property="edtaTreatment.description"/></b></td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink01">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink01"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink02">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink02"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink03">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink03"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink04">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink04"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink05">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink05"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="patientDetail" property="edtaTreatment.patientLink06">
           <a href="<bean:write name="patientDetail" property="edtaTreatment.patientLink06"/>" target="_blank"><bean:write name="patientDetail" property="edtaTreatment.patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
    </logic:present>

    <tr>
      <td valign="top" colspan="3">&nbsp;</td>
    </tr>

    </logic:iterate>

    </logic:notEmpty>
      
    <logic:present name="staticLinks">
    <tr valign="top">
      <td class="tableheader" colspan="3">Further Information
      </td>
    </tr>
    <tr>
      <td valign="top" class="tablecell"><bean:write name="staticLinks" property="description"/></td>
      <td valign="top" class="tablecell">&nbsp;</td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="staticLinks" property="patientLink01">
           <a href="<bean:write name="staticLinks" property="patientLink01"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="patientLink02">
           <a href="<bean:write name="staticLinks" property="patientLink02"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="patientLink03">
           <a href="<bean:write name="staticLinks" property="patientLink03"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="patientLink04">
           <a href="<bean:write name="staticLinks" property="patientLink04"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="patientLink05">
           <a href="<bean:write name="staticLinks" property="patientLink05"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="patientLink06">
           <a href="<bean:write name="staticLinks" property="patientLink06"/>" target="_blank"><bean:write name="staticLinks" property="patientLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
    </logic:present>

  </table>

</div>