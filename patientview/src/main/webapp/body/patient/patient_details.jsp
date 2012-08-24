<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="row">
    <div class="span12">


     <div class="page-header">
         <h1>My Details</h1>
     </div>

     <p>
         This section contains basic information about you. Please check the details are correct. If there are any
         errors please contact us by <html:link action="/patient/contact">clicking here</html:link>. We would be grateful if you could record your other medical
         conditions and your email address, thank you. 
     </p>

     <%
         if (request.getParameter("success") != null) {
     %>
     <div class="alert alert-success">Patient details updated</div>
     <%
         }
     %>
        
<logic:notPresent name="patientDetails">
  <div class="alert alert-error">Patient details not uploaded</div>
</logic:notPresent>

<logic:present name="patientDetails">

<logic:empty name="patientDetails">
  <div class="alert alert-error">Patient details not uploaded</div>
</logic:empty>

<logic:notEmpty name="patientDetails">

<logic:iterate id="patientDetail" name="patientDetails">

<p>
    Patient Details for
    <bean:write name="patientDetail" property="patient.forename"/>
    <bean:write name="patientDetail" property="patient.surname"/>, uploaded by unit: <bean:write name="patientDetail" property="unit.shortname"/>    
</p>

<html:form action="/patient/patient_details_update">


<table width="440" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

<tr valign="top">
  <th class="tablecellbold">Last Name</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.surname"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">First Name</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.forename"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">Date of Birth (dd/mm/yyyy)</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.formatedDateOfBirth"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">NHS Number</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.nhsno"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">Hospital Number</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.hospitalnumber"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">Address</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.address1"/>
    <br/>
    <bean:write name="patientDetail" property="patient.address2"/>
    <br/>
    <bean:write name="patientDetail" property="patient.address3"/>
  </td>
</tr>

<tr valign="top">
  <th class="tablecellbold">Postcode</th>

  <td class="tablecell">
    <bean:write name="patientDetail" property="patient.postcode"/>
  </td>
</tr>

<logic:notEmpty name="patientDetail" property="patient.telephone1">
  <tr valign="top">
    <th class="tablecellbold">Telephone 1</th>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.telephone1"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="patient.telephone2">
  <tr valign="top">
    <th class="tablecellbold">Telephone 2</th>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.telephone2"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="patient.mobile">
  <tr valign="top">
    <th class="tablecellbold">Mobile</th>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.mobile"/>
    </td>
  </tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="edtaDiagnosis">
<tr valign="top">
  <th class="tablecellbold">Diagnosis</th>
    <td class="tablecell">
      <bean:write name="patientDetail" property="edtaDiagnosis.description"/>
    </td>   
</tr>
</logic:notEmpty>

<logic:notEmpty name="patientDetail" property="edtaTreatment">
<tr align="top">
  <th class="tablecellbold">Treatment</th>
    <td class="tablecell">
      <bean:write name="patientDetail" property="edtaTreatment.description"/>
    </td>
</tr>
</logic:notEmpty>


<logic:present tenancy="rpv">
    <tr valign="top">
        <th class="tablecellbold">Transplant Status</th>

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
      <th class="tablecellbold">Other Conditions</th>

      <td class="tablecell">
          <logic:iterate id="otherDiagnosis" name="patientDetail" property="otherDiagnoses">
            <bean:write name="otherDiagnosis" property="diagnosis"/>
            <br/>
          </logic:iterate>
      </td>
    </tr>


</logic:present>



<logic:present tenancy="ibd">

    <html:hidden property="patientId"/>
    <tr>
        <th >Other Conditions</th>
        <td><html:textarea property="otherConditions" rows="5" cols="80"  styleClass="input-xlarge"/></td>
    </tr>

    <tr>
        <th>Email Address</th>
        <td><html:text property="email" /></td>
    </tr>



</logic:present>


</table>
    <logic:present tenancy="ibd">

    <html:errors/>
    
    <div class="form-actions">
        <html:submit value="Update" styleClass="btn btn-primary"/>
    </div>
    </logic:present>
    
</html:form>

</logic:iterate>
    

</logic:notEmpty>

</logic:present>

</div>
</div>

