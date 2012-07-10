<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div align="center">

   <table width="440">
     <tr>
       <td><p class="header">Patient Details</p></td>
     </tr>
   </table>

   <br />
   
<logic:notPresent name="patientDetails">
  <table width="440" border="0" cellspacing="1" cellpadding="3">
    <tr valign="top">
      <td class="tableheader" colspan="2">Patient details not uploaded</td>
    </tr>
  </table>
</logic:notPresent>

<logic:present name="patientDetails">

<logic:empty name="patientDetails">
  <table width="440" border="0" cellspacing="1" cellpadding="3">
    <tr valign="top">
      <td class="tableheader" colspan="2">Patient details not uploaded</td>
    </tr>
  </table>
</logic:empty>

<logic:notEmpty name="patientDetails">

<logic:iterate id="patientDetail" name="patientDetails">

<table width="440" border="0" cellspacing="1" cellpadding="3">

<tr valign="top">
  <td class="tableheader" colspan="2">Patient Details for
    <bean:write name="patientDetail" property="patient.forename"/>
    <bean:write name="patientDetail" property="patient.surname"/>, uploaded by unit: <bean:write name="patientDetail" property="unit.shortname"/>      
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Last Name</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.surname"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">First Name</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.forename"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Date of Birth (yyyy-mm-dd)</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.dateofbirth"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">NHS Number</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.nhsno"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Hospital Number</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.hospitalnumber"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Address</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.address1"/>
    <br/>
    <bean:write name="patientDetail" property="patient.address2"/>
    <br/>
    <bean:write name="patientDetail" property="patient.address3"/>
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Postcode</td>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.postcode"/>
  </td>
</tr>

<logic:notEmpty name="patientDetail" property="patient.telephone1">
  <tr valign="top">
    <td class="tablecellbold">Telephone 1</td>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.telephone1"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="patient.telephone2">
  <tr valign="top">
    <td class="tablecellbold">Telephone 2</td>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.telephone2"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="patient.mobile">
  <tr valign="top">
    <td class="tablecellbold">Mobile</td>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.mobile"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="edtaDiagnosis">
<tr valign="top">
  <td class="tablecellbold">Diagnosis</td>
    <td class="tablecell">
      <bean:write name="patientDetail" property="edtaDiagnosis.description"/>
    </td>   
</tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="edtaTreatment">
<tr align="top">
  <td class="tablecellbold">Treatment</td>
    <td class="tablecell">
      <bean:write name="patientDetail" property="edtaTreatment.description"/>
    </td>
</tr>
</logic:notEmpty>



<tr valign="top">
  <td class="tablecellbold">Transplant Status</td>

  <td class="tablecell">




      <logic:equal value="" name="patientDetail" property="uktStatus.uktkidney">
        <bean:message key="ukt.status.none"/>
      </logic:equal>


        <logic:notEqual value="" name="patientDetail" property="uktStatus.uktkidney">
          Kidney :
          <bean:write name="patientDetail" property="uktStatus.uktkidney"/>
        </logic:notEqual>

        <logic:notEqual value="" name="patientDetail" property="uktStatus.uktpancreas">
          <logic:notEqual value="Not on list" name="patientDetail" property="uktStatus.uktpancreas">
            <br/>
             Pancreas :
             <bean:write name="patientDetail" property="uktStatus.uktpancreas"/>
          </logic:notEqual>
        </logic:notEqual>



    <br/>(<a href="http://www.renal.org/rixg/transplant.html" target="_blank">Explain this</a>)
  </td>
</tr>

<tr valign="top">
  <td class="tablecellbold">Other Conditions</td>

  <td class="tablecell">
      <logic:iterate id="otherDiagnosis" name="patientDetail" property="otherDiagnoses">
        <bean:write name="otherDiagnosis" property="diagnosis"/>
        <br/>
      </logic:iterate>
  </td>
</tr>

</table>
<br /><br />

</logic:iterate>

</logic:notEmpty>

</logic:present>

</div>


