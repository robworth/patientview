<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
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
    <a class="pull-right" href="http://www.renal.org/rixg/adminhelp.html" target="_blank">Help</a>
    <logic:present name="unit">
        <div class="page-header"><h1>Patients for Unit <bean:write name="unit" property="name"/></h1></div>
    </logic:present>



    <logic:empty name="patients">
        <p>No matching patients found.</p>
    </logic:empty>


    <logic:notEmpty name="patients">
        <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
                <th class="tableheader">Name<br />(edit)</th>
                <th class="tableheader">NHS Number<br />(view patient)</th>
                <th class="tableheader">DoB</th>
                <th class="tableheader">Unit Code</th>
                <th class="tableheader">Treatment</th>
                <th class="tableheader">Email</th>
                <th class="tableheader">Email Verified</th>
                <th class="tableheader">Last Logon</th>
                <th class="tableheader">Password</th>
                <th colspan="4">&nbsp;</th>
            </tr>
            </thead>
            <logic:iterate id="patient" name="patients" type="org.patientview.patientview.logon.PatientLogon">

                <%
                    Map <String, String> patientKeyParams = new HashMap <String, String>();
                    patientKeyParams.put("nhsno", patient.getNhsno() );
                    patientKeyParams.put("unitcode", patient.getUnitcode());
                    patientKeyParams.put("username", patient.getUsername());
                    request.setAttribute("patientKeyParams", patientKeyParams);
                %>

                <tr>
                    <td class="tablecell">
                        <logic:present role="superadmin,unitadmin">
                            <html:link action="/control/patientEditInput" name="patientKeyParams">
                                <bean:write name="patient" property="name"/>
                            </html:link>
                        </logic:present>

                        <logic:present role="unitstaff">
                            <bean:write name="patient" property="name"/>
                        </logic:present>
                    </td>
                    <td class="tablecell">
                        <html:link action="/control/patientView" paramId="username" paramName="patient" paramProperty="username" >
                            <bean:write name="patient" property="nhsno"/>
                        </html:link>
                    </td>
                    <td class="tablecell"><bean:write name="patient" property="dateofbirthFormatted"/></td>
                    <td class="tablecell">
                        <bean:write name="patient" property="unitcode"/>
                    </td>
                    <td class="tablecell">
                        <logic:empty name="patient" property="treatment">(no record)</logic:empty>
                        <logic:notEmpty name="patient" property="treatment">
                            <bean:write name="patient" property="treatment"/>
                        </logic:notEmpty>
                    </td>
                    <td class="tablecell"><bean:write name="patient" property="email"/></td>
                    <td class="tablecell">
                        <logic:equal value="false" name="patient" property="emailverfied">
                            <big><font color="red">&#10008;</font></big>
                        </logic:equal>
                        <logic:equal value="true" name="patient" property="emailverfied">
                            <big><font color="green">&#10004;</font></big>
                        </logic:equal>
                    </td>
                    <td class="tablecell"><bean:write name="patient" property="lastlogonFormatted"/></td>
                    <td class="tablecell">
                        <logic:equal value="true" name="patient" property="accountlocked">
                            <font color="red">locked</font>
                        </logic:equal>
                        <logic:equal value="false" name="patient" property="accountlocked">
                            <big><font color="green">&#10004;</font></big>
                        </logic:equal>
                    </td>

                    <logic:present role="superadmin,unitadmin">
                        <td>
                            <html:form action="/control/logViewForPatient">
                                <html:hidden name="patient" property="nhsno" />
                                <html:submit value="Log" styleClass="btn formbutton" />
                            </html:form>
                        </td>
                    </logic:present>

                    <logic:present role="superadmin,unitadmin">
                        <td>
                            <html:form action="/control/viewsOfPatient">
                                <html:hidden name="patient" property="nhsno" />
                                <html:submit value="Views" styleClass="btn formbutton" />
                            </html:form>
                        </td>
                    </logic:present>

                    <logic:present role="superadmin,unitadmin">
                        <td>
                            <html:form action="/control/dataLoadsForPatient">
                                <html:hidden name="patient" property="nhsno" />
                                <html:submit value="Data" styleClass="btn formbutton" />
                            </html:form>
                        </td>
                    </logic:present>

                    <logic:present role="superadmin,unitadmin">
                        <td>
                            <html:form action="/control/activityByUser">
                                <html:hidden name="patient" property="username" />
                                <html:submit value="Activity" styleClass="btn formbutton" />
                            </html:form>
                        </td>
                    </logic:present>

                    <logic:present specialty="ibd">
                        <td>
                            <html:form action="/control/ibduser-edit" method="get" style="margin-bottom: 0">
                                <input type="hidden" name="nhsNo" value="<bean:write name="patient" property="nhsno" />" />
                                <html:submit value="Manage Ibd Settings" styleClass="btn formbutton" />
                            </html:form>
                        </td>
                    </logic:present>

                </tr>
            </logic:iterate>
        </table>
    </logic:notEmpty>
</div>
</div>
