<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Information</h1>
</div>

<p><bean:message key="education.intro" /></p>


<logic:empty name="edtaCode">
    <div class="alert">No education links found</div>
</logic:empty>

<logic:notEmpty name="edtaCode">

    <h2>Links for IBD</h2>

    <ul class="paragraphSizeTopMargin">
        <logic:notEmpty name="edtaCode" property="medicalLink01">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="medicalLink01"/>"><bean:write name="edtaCode" property="medicalLinkText01"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink02">
            <li><a target="_blank" class="ext" href="<bean:write name="edtaCode" property="medicalLink02"/>"><bean:write name="edtaCode" property="medicalLinkText02"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink03">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="medicalLink03"/>"><bean:write name="edtaCode" property="medicalLinkText03"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink04">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="medicalLink04"/>"><bean:write name="edtaCode" property="medicalLinkText04"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink05">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="medicalLink05"/>"><bean:write name="edtaCode" property="medicalLinkText05"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="medicalLink06">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="medicalLink06"/>"><bean:write name="edtaCode" property="medicalLinkText06"/></a></li>
        </logic:notEmpty>

        <logic:notEmpty name="edtaCode" property="patientLink01">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink01"/>"><bean:write name="edtaCode" property="patientLinkText01"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink02">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink02"/>"><bean:write name="edtaCode" property="patientLinkText02"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink03">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink03"/>"><bean:write name="edtaCode" property="patientLinkText03"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink04">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink04"/>"><bean:write name="edtaCode" property="patientLinkText04"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink05">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink05"/>"><bean:write name="edtaCode" property="patientLinkText05"/></a></li>
        </logic:notEmpty>
        <logic:notEmpty name="edtaCode" property="patientLink06">
            <li><a target="_blank" href="<bean:write name="edtaCode" property="patientLink06"/>"><bean:write name="edtaCode" property="patientLinkText06"/></a></li>
        </logic:notEmpty>
    </ul>

</logic:notEmpty>


        