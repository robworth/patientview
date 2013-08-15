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

<div class="page-header">
    <h1>Please log in below</h1>
</div>
<form action="j_security_check" method="POST" class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="j_username">Username</label>
            <div class="controls">
                <input type="text" name="j_username" id="j_username" class="loginform input-xlarge"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="j_password">Password</label>
            <div class="controls">
                <input type="password" id="j_password" name="j_password" class="loginform" />
            </div>
        </div>
        <div class="form-actions">
                <input type="submit" value="Login" class="btn"/>
                <a href="forgotten-password.jsp"> Forgotten password?</a>
        </div>
    </fieldset>
</form>
