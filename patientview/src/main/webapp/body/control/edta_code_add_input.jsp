<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Link Editing</p>

<html:errors />

<html:form action="/control/edtaCodeAdd">
  <html:hidden property="linkType" name="codeType" />
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Code</b></td>
      <td><html:text property="edtaCode" /></td>
    </tr>
    <tr>
      <td><b>Description</b></td>
      <td><html:text property="description" /></td>
    </tr>
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td></td>
      <td align="center"><b>Link (e.g. http:// etc.)</b></td>
      <td align="center"><b>Text Description</b></td>
    </tr>
    <tr>
      <td><b>Medical Link 1</b></td>
      <td><html:text property="medicalLink01" /></td>
      <td><html:text property="medicalLinkText01" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 2</b></td>
      <td><html:text property="medicalLink02" /></td>
      <td><html:text property="medicalLinkText02" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 3</b></td>
      <td><html:text property="medicalLink03" /></td>
      <td><html:text property="medicalLinkText03" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 4</b></td>
      <td><html:text property="medicalLink04" /></td>
      <td><html:text property="medicalLinkText04" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 5</b></td>
      <td><html:text property="medicalLink05" /></td>
      <td><html:text property="medicalLinkText05" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 6</b></td>
      <td><html:text property="medicalLink06" /></td>
      <td><html:text property="medicalLinkText06" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 1</b></td>
      <td><html:text property="patientLink01" /></td>
      <td><html:text property="patientLinkText01" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 2</b></td>
      <td><html:text property="patientLink02" /></td>
      <td><html:text property="patientLinkText02" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 3</b></td>
      <td><html:text property="patientLink03" /></td>
      <td><html:text property="patientLinkText03" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 4</b></td>
      <td><html:text property="patientLink04" /></td>
      <td><html:text property="patientLinkText04" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 5</b></td>
      <td><html:text property="patientLink05" /></td>
      <td><html:text property="patientLinkText05" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 6</b></td>
      <td><html:text property="patientLink06" /></td>
      <td><html:text property="patientLinkText06" /></td>
    </tr>
     <tr>
       <td><html:submit value="Add" styleClass="formbutton" /></td>
     </tr>
 </table>

</html:form>
