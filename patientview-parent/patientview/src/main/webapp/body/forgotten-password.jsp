<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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

<div class="page-header">
    <h1>Forgotten password</h1>
</div>
<logic:notEmpty name="nullUser">
    <div class="alert alert-info">Please enter your user name and email address.</div>
</logic:notEmpty>
<logic:equal name="foundUser" value="false">
    <div class="alert alert-block">No match was found for the user and email combination you entered.</div>
</logic:equal>
<logic:notEmpty name="nullEmail">
    <div class="alert alert-error">That username does not have an email set. Please contact an administrator.</div>
</logic:notEmpty>
<logic:notEmpty name="noMatch">
    <div class="alert alert-error">No match was found for the user and email combination you entered.</div>
</logic:notEmpty>

<form action="forgotten-password.do" class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="username">Username</label>
            <div class="controls">
                <input type="text" name="username" id="username" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email address</label>
            <div class="controls">
                <input type="text" name="email" id="email" />
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" value="Submit" class="btn"/>
        </div>
    </fieldset>
</form>

