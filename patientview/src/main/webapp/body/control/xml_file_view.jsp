<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
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
        <h1>Patient Data XML File Contents</h1>
    </div>

    <logic:present name="xmlFilename">
      <p>File Name: <b><bean:write name="xmlFilename"/></b></p>
    </logic:present>

    <logic:notPresent name="xmlContent">
      That file does not exist.
      <br /><br />
      The contents of uploaded files are only held for 20 days, so it may already have been deleted.
    </logic:notPresent>


    <logic:present name="xmlContent">

        <bean:define id="xmlContent" name="xmlContent" type="java.lang.String" />

        <p>----START XML FILE----</p>

      <p><%= StringEscapeUtils.escapeHtml(xmlContent).replace("\n", "<br />")%></p>

        <p>-----END XML FILE-----</p>
    </logic:present>
</div>

