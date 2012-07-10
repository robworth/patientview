<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

        <td class="left">
          <table>
            <logic:present role="superadmin">
              <tr>
                <td><html:link action="/control/edtaCodeDisplay">EDTA Codes</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/treatmentCodeDisplay">Treatment Codes</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/staticLinkEdit">Static Links</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/resultHeadingDisplay">Result Headings</html:link></td>
              </tr>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
            </logic:present>
            <logic:present role="superadmin,unitadmin">
              <tr>
                <td><html:link action="/control/unitDisplay">Renal Units</html:link></td>
              </tr>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link action="/control/unitAdminAddInput">Add Unit User</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/unitUsersUnitSelect">Users In Unit</html:link></td>
              </tr>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
            </logic:present>
            <tr>
              <td><html:link action="/control/unitPatientsUnitSelect">Patients In Unit</html:link></td>
            </tr>
            <logic:present role="superadmin,unitadmin">
              <tr>
                <td><html:link action="/control/patientAddInput">Add Patient</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/logView">View Log</html:link></td>
              </tr>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link action="/control/feedbackUnitSelect">Feedback</html:link></td>
              </tr>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link action="/patient/patient_details">News</html:link></td>
              </tr>
              <tr>
                <td><html:link action="/control/newsList">News Edit</html:link></td>
              </tr>
            </logic:present>
            <logic:present role="superadmin">
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link action="/control/splashPageEdit">Splash Page</html:link></td>
              </tr>
            </logic:present>
            <logic:present role="superadmin">
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link action="/control/version">RPV Version</html:link></td>
              </tr>
            </logic:present>
              <tr>
                <td><hr class="thinblue"/></td>
              </tr>
              <tr>
                <td><html:link href="/forums/list.page">Forum</html:link></td>
              </tr>
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
        </td>
