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
        <th class="tableheader">Treatment</th>
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
            <%--
            <html:link action="/control/patientEditInput" paramId="username" paramName="patient" paramProperty="username">
              <bean:write name="patient" property="name"/>
            </html:link>
--%>
              <html:link action="/control/patientEditInput" name="patientKeyParams">
                <bean:write name="patient" property="name"/>
              </html:link>

          </logic:present>

            <logic:present role="unitstaff">
              <bean:write name="patient" property="name"/>
          </logic:present>
        </td>
        <td class="tablecell">
       <%--   <html:link action="/control/patientView" paramId="nhsno" paramName="patient" paramProperty="nhsno" >   --%>
       <%--   <html:link action="/control/patientView" name="patientKeyParams" > --%>
        <html:link action="/control/patientView" paramId="username" paramName="patient" paramProperty="username" >
            <bean:write name="patient" property="nhsno"/>
          </html:link>
        </td>
        <td class="tablecell">
          <logic:empty name="patient" property="treatment">(no record)</logic:empty>
          <logic:notEmpty name="patient" property="treatment">
            <bean:write name="patient" property="treatment"/>
          </logic:notEmpty>
        </td>
<%--
        <logic:present role="superadmin,unitadmin">
          <html:form action="/control/patientEditInput">
            <html:hidden name="patient" property="username" />
            <td><html:submit value="Edit" styleClass="formbutton" /></td>
          </html:form>
        </logic:present>

        <logic:present role="superadmin,unitadmin,unitstaff">
          <html:form action="/control/patientView">
            <html:hidden name="patient" property="nhsno" />
            <td><html:submit value="Patient" styleClass="formbutton" /></td>
          </html:form>
        </logic:present>
--%>

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