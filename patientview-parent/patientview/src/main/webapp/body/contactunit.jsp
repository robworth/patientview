<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

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

<html:xhtml/>

<div class="page-header">
    <h1>Forgotten Password Request</h1>
</div>

<p>Please enter your details. You need to be a patient of a renal unit.</p>
<p>Ensure they are accurate since they will be used by your unit's staff to verify who you are.</p>
<br />

<html:form action="/contactUnitSubmit" styleClass="form-horizontal">

    <html:errors/>

    <div class="control-group">
        <label class="control-label">First name</label>

        <div class="controls"><html:text property="firstName"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Last name</label>

        <div class="controls"><html:text property="lastName"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Date of birth</label>

        <div class="date datePicker controls" data-date="<bean:write name="joinForm" property="dateOfBirth"/>">
            <input name="dateOfBirth" class="span2" size="16" type="text"
                   value="<bean:write name="joinForm" property="dateOfBirth"/>">
            <span class="add-on"><i class="icon-th"></i></span>
            <span class="help-inline">dd-mm-yyyy</span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">NHS Number</label>

        <div class="controls"><html:text property="nhsNo"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Unit</label>

        <div class="controls">
            <html:select property="unitcode">
                <option value="-1" selected="selected">-- Select your unit --</option>
                <html:options collection="units" property="unitcode" labelProperty="name"/>
            </html:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Email address</label>

        <div class="controls"><html:text property="email"/></div>
    </div>

    <div class="control-group">
        <label class="control-label">Security Question</label>


        <div class="controls">
            <div class="help-block top-help-block"><%=request.getSession().getAttribute("antiSpamQuestion")%> = ?</div>

            <html:text property="antiSpamAnswer"/>
        </div>
    </div>

    <div class="control-group">
        <div class="controls"><html:submit value="Send request" styleClass="btn"/></div>
    </div>

</html:form>
