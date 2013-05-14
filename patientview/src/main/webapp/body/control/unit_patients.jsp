<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
                <th class="tableheader">Password Locked</th>
                <th colspan="4">&nbsp;</th>
            </tr>
            </thead>
            <logic:iterate id="patient" name="patients" type="com.worthsoln.patientview.logon.PatientLogon">

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