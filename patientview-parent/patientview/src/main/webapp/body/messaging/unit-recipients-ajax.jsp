<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

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

<logic:empty name="unitAdminRecipients">
    <logic:present role="unitadmin">
        <option></option>
        <optgroup label="Unit Admins">
            <option value="allAdmins">All <bean:write name="unit_name"/> Admins</option>
        </optgroup>
    </logic:present>
</logic:empty>

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

<logic:empty name="unitStaffRecipients">
    <logic:present role="unitadmin">
        <option></option>
        <optgroup label="Unit Staff">
            <option value="allStaff">All <bean:write name="unit_name"/> Staff</option>
        </optgroup>
    </logic:present>
</logic:empty>

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
