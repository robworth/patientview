<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

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

<div class="page-header"><h1>About Me</h1></div>

<div class="row">
<logic:present name="aboutme">
<logic:notEmpty name="aboutme">
        <jsp:useBean id="aboutme" class="org.patientview.patientview.model.Aboutme" scope="request"/>
    <div class="span3">
        <img src="aboutmeimage/<%= aboutme.getNhsno() %>" alt="" width="200">
    </div>

<div class="span9">
        <h2>Things people should know about me</h2>
        <p><bean:write name="aboutme" property="aboutme" /></p>
        <h2>Things I'd like to talk about</h2>
        <p><bean:write name="aboutme" property="talkabout" /></p>
        <html:form action="/patient/aboutmeEdit">
            <div class="form-actions">
                <html:submit value="Edit" styleClass="btn btn-primary"/>
                <input type="button" class="btn" value=" Print this page " onclick="window.print();return false;" />
            </div>
        </html:form>
</div>

</logic:notEmpty>
</logic:present>
</div>


