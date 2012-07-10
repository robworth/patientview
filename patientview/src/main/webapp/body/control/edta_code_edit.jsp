<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Links Editing</p>

<html:errors />

<html:form action="/control/edtaCodeUpdate">
   <html:hidden property="linkType" name="edtaCode" />
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Code</b></td>
      <td><bean:write name="edtaCode" property="edtaCode" /></td>
      <html:hidden name="edtaCode" property="edtaCode" />
    </tr>
    <tr>
      <td><b>Description</b></td>
      <td><html:text name="edtaCode" property="description" /></td>
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
      <td><html:text name="edtaCode" property="medicalLink01" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText01" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 2</b></td>
      <td><html:text name="edtaCode" property="medicalLink02" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText02" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 3</b></td>
      <td><html:text name="edtaCode" property="medicalLink03" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText03" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 4</b></td>
      <td><html:text name="edtaCode" property="medicalLink04" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText04" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 5</b></td>
      <td><html:text name="edtaCode" property="medicalLink05" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText05" /></td>
    </tr>
    <tr>
      <td><b>Medical Link 6</b></td>
      <td><html:text name="edtaCode" property="medicalLink06" /></td>
      <td><html:text name="edtaCode" property="medicalLinkText06" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 1</b></td>
      <td><html:text name="edtaCode" property="patientLink01" /></td>
      <td><html:text name="edtaCode" property="patientLinkText01" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 2</b></td>
      <td><html:text name="edtaCode" property="patientLink02" /></td>
      <td><html:text name="edtaCode" property="patientLinkText02" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 3</b></td>
      <td><html:text name="edtaCode" property="patientLink03" /></td>
      <td><html:text name="edtaCode" property="patientLinkText03" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 4</b></td>
      <td><html:text name="edtaCode" property="patientLink04" /></td>
      <td><html:text name="edtaCode" property="patientLinkText04" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 5</b></td>
      <td><html:text name="edtaCode" property="patientLink05" /></td>
      <td><html:text name="edtaCode" property="patientLinkText05" /></td>
    </tr>
    <tr>
      <td><b>Patient Link 6</b></td>
      <td><html:text name="edtaCode" property="patientLink06" /></td>
      <td><html:text name="edtaCode" property="patientLinkText06" /></td>
    </tr>
    <tr>
      <td><html:submit value="Update" styleClass="formButton"/></td>
    </tr>
</html:form>









 </table>

