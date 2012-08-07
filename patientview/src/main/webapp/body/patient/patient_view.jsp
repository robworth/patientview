<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="row">
    <div class="span12">

    <div class="page-header">
        <h1>Patient Info</h1>
    </div>

<div align="left">
    The links on this page are to information that is mainly written for patients, but it is often very useful for healthcare staff too. Almost all links are to other websites. These links have been chosen by us, but the information is not written by us.
</div>



  <logic:notEmpty name="patientDetails">

  <logic:iterate id="patientDetail" name="patientDetails">

    <logic:present name="patientDetail" property="unit">
    <logic:present name="patientDetail" property="patient">
    <h2 class="paragraphSizeTopMargin">Patient Details for
          <bean:write name="patientDetail" property="patient.forename"/>
          <bean:write name="patientDetail" property="patient.surname"/> <small>uploaded by unit: <bean:write name="patientDetail" property="unit.shortname"/></small>
    </h2>
    </logic:present>
    </logic:present>

    <table class="table table-bordered table-striped">
    <logic:notPresent name="patientDetail" property="edtaDiagnosis">
      <tr>
        <th valign="top">Primary Diagnosis</th>
        <td valign="top">No primary diagnosis has been loaded</td>
      </tr>
    </logic:notPresent>

    <logic:present name="patientDetail" property="edtaDiagnosis">
    <tr>
      <th valign="top" class="tablecell">Primary Diagnosis</th>
      <td valign="top" class="tablecell"><bean:write name="patientDetail" property="edtaDiagnosis.description"/></td>
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
      <th valign="top" class="tablecell">Treatment</th>
      <td valign="top" class="tablecell">No treatment has been uploaded</td>
    </tr>
    </logic:notPresent>

    <logic:present name="patientDetail" property="edtaTreatment">
    <tr>
      <th valign="top" class="tablecell">Treatment</th>
      <td valign="top" class="tablecell"><bean:write name="patientDetail" property="edtaTreatment.description"/></td>
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
    </table>


    </logic:iterate>

    </logic:notEmpty>
      
    <logic:present name="staticLinks">
    <h2>Further Information</h2>

    <table class="table table-bordered table-striped">
    <tr>
      <th valign="top" class="tablecell"><bean:write name="staticLinks" property="description"/></th>
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
</div>