<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<option value="">Select</option>

<logic:notEmpty name="unitAdminRecipients">
    <option></option>

    <optgroup label="Unit Admins">
        <logic:present role="unitadmin">
            <option value="allAdmins">All <bean:write name="unit_name"/> Admins</option>
        </logic:present>
        <logic:iterate name="unitAdminRecipients" id="recipient" indexId="index">
            <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
        </logic:iterate>
    </optgroup>
</logic:notEmpty>

<logic:notEmpty name="unitStaffRecipients">
    <option></option>

    <optgroup label="Unit Staff">
        <logic:present role="unitadmin">
            <option value="allStaff">All <bean:write name="unit_name"/> Staff</option>
        </logic:present>
        <logic:iterate name="unitStaffRecipients" id="recipient" indexId="index">
            <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
        </logic:iterate>
    </optgroup>
</logic:notEmpty>

<logic:notEmpty name="unitPatientRecipients">
    <option></option>

    <optgroup label="Patients">
        <logic:present role="unitadmin">
            <option value="allPatients">All <bean:write name="unit_name"/> Patients</option>
        </logic:present>
        <logic:iterate name="unitPatientRecipients" id="recipient" indexId="index">
            <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
        </logic:iterate>
    </optgroup>
</logic:notEmpty>