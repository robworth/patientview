<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div align="left">
The links on this page are to information that is written for people with some medical knowledge. It may seem complicated and could even be misleading for readers without that sort of background. Almost all links are to other websites. These links have been chosen by us, but the information is not written by us.
</div>

<br/>

<div align="center">

  <table border="0"  width="690">
    <logic:notPresent name="edtaCode">
    <tr>
      <td valign="top" class="tablecell">Primary Diagnosis:</td>
      <td valign="top" class="tablecell"><b>No primary diagnosis has been loaded</b></td>
    </tr>
    </logic:notPresent>

    <logic:present name="patient">
      <tr valign="top">
        <td class="tableheader" colspan="3">Medical Information Links for <bean:write name="patient" property="forename"/> <bean:write name="patient" property="surname"/></td>
      </tr>
    </logic:present>

    <logic:present name="edtaCode">
    <tr>
      <td valign="top" class="tablecell">Primary Diagnosis:</td>
      <td valign="top" class="tablecell"><b><bean:write name="edtaCode" property="description"/></b></td>
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
    </logic:present>


    <logic:notPresent name="treatmentCode">
    <tr>
      <td valign="top" class="tablecell">Treatment:</td>
      <td valign="top" class="tablecell"><b>No treatment has been uploaded</b></td>
    </tr>
    </logic:notPresent>

    <logic:present name="treatmentCode">
    <tr>
      <td valign="top" class="tablecell">Treatment:</td>
      <td valign="top" class="tablecell"><b><bean:write name="treatmentCode" property="description"/></b></td>
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
    </logic:present>

    <logic:present name="staticLinks">
    <logic:notEmpty name="staticLinks">
    <tr>
      <td valign="top" class="tablecell"><bean:write name="staticLinks" property="description"/></td>
      <td valign="top" class="tablecell">&nbsp;</td>
      <td valign="top" class="tablecell">
        <logic:notEmpty name="staticLinks" property="medicalLink01">
           <a href="<bean:write name="staticLinks" property="medicalLink01"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText01"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="medicalLink02">
           <a href="<bean:write name="staticLinks" property="medicalLink02"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText02"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="medicalLink03">
           <a href="<bean:write name="staticLinks" property="medicalLink03"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText03"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="medicalLink04">
           <a href="<bean:write name="staticLinks" property="medicalLink04"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText04"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="medicalLink05">
           <a href="<bean:write name="staticLinks" property="medicalLink05"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText05"/></a><br />
        </logic:notEmpty>
        <logic:notEmpty name="staticLinks" property="medicalLink06">
           <a href="<bean:write name="staticLinks" property="medicalLink06"/>" target="_blank"><bean:write name="staticLinks" property="medicalLinkText06"/></a><br />
        </logic:notEmpty>
      </td>
    </tr>
    </logic:notEmpty>
    </logic:present>

  </table>

</div>