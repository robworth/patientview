<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="org.patientview.utils.LegacySpringUtils" %>
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
    <div class="page-header">
        <h1>Patients for Unit <logic:notEmpty name="unit"><bean:write name="unit" property="name"/></logic:notEmpty></h1>
    </div>



    <logic:empty name="patients">
        <p>No matching patients found.</p>
    </logic:empty>

    <% String context=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext();
       request.setAttribute("context", context);
    %>
    <logic:notEmpty name="patients">
        <div class="span10" style="margin-left: 20px;margin-bottom:15px;">
            <div class="row" style="float: left; font-weight:bold; font-size: 15px; color: blue;">
                <logic:equal value="false" name="patients" property="firstPage">
                    <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=first">&lt;&lt;&nbsp;First</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="firstPage">
                    |&nbsp;<a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=prev">&lt;&nbsp;Previous</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="firstPage">
                    <logic:equal value="false" name="patients" property="lastPage">
                        |&nbsp;
                    </logic:equal>
                </logic:equal>
                <logic:equal value="false" name="patients" property="lastPage">
                    <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=next">Next&nbsp;&gt;</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="lastPage">
                    |&nbsp;<a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=last">Last&nbsp;&gt;&gt;</a>
                </logic:equal>
            </div>
        </div>

        <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
            <tr>
                <th class="tableheader" onclick="sort('firstName')"><a href="#">Name<br />(edit)</a></th>
                <th class="tableheader" onclick="sort('nhsno')"><a href="#">NHS Number<br />(view patient)</a></th>
                <th class="tableheader" onclick="sort('dateofbirth')"><a href="#">DoB</a></th>
                <th class="tableheader" onclick="sort('unitcode')"><a href="#">Unit Code</a></th>
                <th class="tableheader" onclick="sort('treatment')"><a href="#">Treatment</a></th>
                <th class="tableheader" onclick="sort('email')"><a href="#">Email</a></th>
                <th class="tableheader" onclick="sort('emailverified')"><a href="#">Email Verified</a></th>
                <th class="tableheader" onclick="sort('lastlogon')"><a href="#">Last Logon</a></th>
                <th class="tableheader" onclick="sort('accountlocked')"><a href="#">Password</a></th>
                <th class="tableheader" onclick="sort('lastverificationdate')"><a href="#">Last Email Verification Date</a></th>
                <th class="tableheader" onclick="sort('rrtModality')"><a href="#">Modality</a></th>
                <th class="tableheader" onclick="sort('lastdatadate')"><a href="#">Last Data Received Date</a></th>
                <th colspan="5">&nbsp;</th>
            </tr>
            <logic:iterate id="patient" name="patients" type="org.patientview.patientview.logon.PatientLogonWithTreatment" property="pageList">

                <%
                    Map <String, String> patientKeyParams = new HashMap <String, String>();
                    patientKeyParams.put("nhsno", patient.getNhsno() );
                    patientKeyParams.put("unitcode", patient.getUnitcode());
                    patientKeyParams.put("username", patient.getUsername());
                    patientKeyParams.put("patientId", patient.getPatientId().toString());
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
                        <html:link action="/control/patientView" name="patientKeyParams">
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
                    <td class="tablecell">
                        <logic:notEmpty name="patient" property="email">
                             <bean:write name="patient" property="email"/></td>
                        </logic:notEmpty>
                    <td class="tablecell">
                        <logic:equal value="false" name="patient" property="emailverified">
                            <big><font color="red">&#10008;</font></big>
                        </logic:equal>
                        <logic:equal value="true" name="patient" property="emailverified">
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
                    <td class="tablecell"><bean:write name="patient" property="lastverificationdateFormatted"/></td>
                    <td class="tablecell"><bean:write name="patient" property="modality"/></td>
                    <td class="tablecell"><bean:write name="patient" property="lastdatadateFormatted"/></td>

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

                    <logic:present role="superadmin,unitadmin">
                        <td>
                            <bean:define id="username" name="patient" property="username" />
                            <logic:notEmpty name="patient" property="email">
                                <bean:define id="email" name="patient" property="email" />
                                <bean:define id="emailverified" name="patient" property="emailverified"/>
                            </logic:notEmpty>
                            <input type="button" value="Send Verification Email" class="btn formbutton" ${emailverified?"disabled":""} onclick="sendVerification('${username}','${email}', '/${context}/web/control/emailverification.do', this)">
                        </td>
                    </logic:present>

                </tr>
            </logic:iterate>
        </table>
        <div class="span10" style="margin-left: 20px;margin-top:-3px;">
            <div class="row" style="float: left; font-weight:bold; font-size: 15px; color: blue;">
                <logic:equal value="false" name="patients" property="firstPage">
                    <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=first">&lt;&lt;&nbsp;First</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="firstPage">
                    |&nbsp;<a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=prev">&lt;&nbsp;Previous</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="firstPage">
                    <logic:equal value="false" name="patients" property="lastPage">
                        |&nbsp;
                    </logic:equal>
                </logic:equal>
                <logic:equal value="false" name="patients" property="lastPage">
                    <a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=next">Next&nbsp;&gt;</a>
                </logic:equal>
                <logic:equal value="false" name="patients" property="lastPage">
                    |&nbsp;<a href="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/web/control/unitPatients?page=last">Last&nbsp;&gt;&gt;</a>
                </logic:equal>
            </div>
        </div>
    </logic:notEmpty>

</div>
<script src="/js/emailverification.js" type="text/javascript"></script>

<script type="text/javascript">
    function sort(property){
        window.location.href="./unitPatients?page=sort&property="+property;
    }
</script>