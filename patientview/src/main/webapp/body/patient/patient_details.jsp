<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="row">
    <div class="span12">


     <div class="page-header">
         <h1>My Details</h1>
     </div>
     <ul class="padded-list">
         <li>
             My details contains basic information from your hospital records. Please check that your details are correct.
             If there are any errors please contact us by <html:link action="/patient/ibd-contact">clicking here</html:link>.
         </li>
         <li>
             To complete a full summary - please enter any other health conditions and your email address in the box
             below. This is for your own personal record and does not become part of the hospital record.
         </li>
         <li>
            <strong>If your details are correct please click on the tabs above to navigate to areas of interest to you.</strong>
         </li>
     </ul>
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
    <bean:write name="patientDetail" property="patient.surname"/>
    <logic:present tenancy="rpv">
        , uploaded by unit: <bean:write name="patientDetail" property="unit.shortname"/>
    </logic:present>
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
  <th class="tablecellbold">Date of Birth (dd-mm-yyyy)</th>

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

  <tr valign="top">
    <th class="tablecellbold">Telephone <logic:present tenancy="rpv">1</logic:present></th>
      <logic:notEmpty name="patientDetail" property="patient.telephone1">
        <td class="tablecell">
            <bean:write name="patientDetail" property="patient.telephone1"/>
        </td>
      </logic:notEmpty>
      <logic:empty name="patientDetail" property="patient.telephone1">
        <td class="tablecell">
            None uploaded
        </td>
      </logic:empty>
  </tr>

<logic:present tenancy="rpv">
<logic:notEmpty name="patientDetail" property="patient.telephone2">
  <tr valign="top">
    <th class="tablecellbold">Telephone 2</th>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.telephone2"/>
    </td>
  </tr>
</logic:notEmpty>
</logic:present>

<logic:notEmpty name="patientDetail" property="patient.mobile">
  <tr valign="top">
    <th class="tablecellbold">Mobile</th>

    <td class="tablecell">
      <bean:write name="patientDetail" property="patient.mobile"/>
    </td>
  </tr>
</logic:notEmpty>

<tr valign="top">
  <th class="tablecellbold">Diagnosis</th>
    <td class="tablecell">
        <logic:present tenancy="rpv">
            <logic:notEmpty name="patientDetail" property="edtaDiagnosis">
                <bean:write name="patientDetail" property="edtaDiagnosis.description"/><br />
            </logic:notEmpty>
        </logic:present>
        <logic:present name="myIbdDiagnosis" scope="session">
            <bean:write name="myIbdDiagnosis" scope="session"/><br />
        </logic:present>
    </td>
</tr>

<tr valign="top">
    <th class="tablecellbold">GP name</th>
    <td class="tablecell">
        <bean:write name="patientDetail" property="patient.gpname"/>
    </td>
</tr>

<tr valign="top">
    <th class="tablecellbold">GP telephone</th>
    <td class="tablecell">
        <bean:write name="patientDetail" property="patient.gptelephone"/>
    </td>
</tr>

<tr valign="top">
    <th class="tablecellbold">GP address</th>
    <td class="tablecell">
        <bean:write name="patientDetail" property="patient.gpaddress1"/>
        <br />
        <bean:write name="patientDetail" property="patient.gpaddress2"/>
        <br />
        <bean:write name="patientDetail" property="patient.gpaddress3"/>
        <br />
        <bean:write name="patientDetail" property="patient.gppostcode"/>
    </td>
</tr>

<logic:present tenancy="rpv">
    <logic:notEmpty name="patientDetail" property="edtaTreatment">
        <tr align="top">
            <th class="tablecellbold">Treatment</th>
            <td class="tablecell">
                <bean:write name="patientDetail" property="edtaTreatment.description"/>
            </td>
        </tr>
    </logic:notEmpty>

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

