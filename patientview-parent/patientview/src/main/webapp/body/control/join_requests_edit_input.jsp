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

<html:xhtml/>
<div class="span9">
    <div class="page-header">
        <h1>Join Request Edit</h1>
    </div>
    <html:errors/>
    <div class="form-horizontal">
        <form action="/web/control/joinRequestEdit" method="post">
            <div class="control-group">
                <label class="control-label">First Name</label>
                <div class="controls"><bean:write name="joinRequest" property="firstName"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Last Name</label>
                <div class="controls"><bean:write name="joinRequest" property="lastName"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Email Address</label>
                <div class="controls"><bean:write name="joinRequest" property="email"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Date of Request</label>
                <div class="controls"><bean:write name="joinRequest" property="dateOfRequestFormatted"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Completed</label>
                <div class="controls">
                    <html:checkbox name="joinRequest" property="isComplete" value="true"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Notes</label>
                <div class="controls">
                    <html:textarea name="joinRequest" property="notes" />
                </div>
            </div>

            <html:hidden name="joinRequest" property="id"/>

            <div class="form-actions">
                <html:submit value="Save" styleClass="pull-right btn btn-primary"/>
            </div>
        </form>

    </div>
</div>