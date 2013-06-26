<%@ page import="org.patientview.ibd.model.enums.Severity" %>
<%@ page import="org.patientview.ibd.model.enums.Diagnosis" %>
<%@ page import="org.patientview.ibd.Ibd" %>
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

<div class="row">
    <div class="span7">
        <div class="page-header">
            <h1>IBD Patient Settings</h1>
        </div>

        <logic:present name="myIbd">
            <%
                if (request.getAttribute(Ibd.DIAGNOSIS_ID_PARAM) != null &&
                        request.getAttribute(Ibd.DIAGNOSIS_ID_PARAM).equals(Diagnosis.CROHNS.getId())) {
            %>
                <p>
                    <strong>Defaults:</strong>
                    <br/>
                    Severe - <%=Severity.SEVERE.getCrohnsDefaultLevel()%>, Moderate
                    - <%=Severity.MODERATE.getCrohnsDefaultLevel()%>, Mild - <%=Severity.MILD.getCrohnsDefaultLevel()%>
                </p>
            <%
                } else {
            %>
                <p>
                    <strong>Defaults:</strong>
                    <br/>
                    Severe - <%=Severity.SEVERE.getColitisDefaultLevel()%>, Moderate
                    - <%=Severity.MODERATE.getColitisDefaultLevel()%>, Mild - <%=Severity.MILD.getColitisDefaultLevel()%>
                </p>
            <%
                }
            %>

            <p>
                <em>Leave values blank to use defaults</em>
                <br/>
            </p>

            <html:form action="/control/ibduser-edit">
                <html:errors/>

                <html:hidden property="submit" value="true"/>

                <html:hidden property="nhsNo"/>

                <div class="control-group">
                    <label class="control-label">Severe level:</label>

                    <div class="controls">
                        <html:text property="severeLevel"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Severe level treatment:</label>

                    <div class="controls">
                        <html:textarea property="severeTreatment"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Moderate level:</label>

                    <div class="controls">
                        <html:text property="moderateLevel"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Moderate level treatment:</label>

                    <div class="controls">
                        <html:textarea property="moderateTreatment"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Mild level:</label>

                    <div class="controls">
                        <html:text property="mildLevel"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Mild level treatment:</label>

                    <div class="controls">
                        <html:textarea property="mildTreatment"/>
                    </div>
                </div>

                <div class="form-actions">
                    <html:submit value="Save" styleClass="btn btn-primary"/>
                </div>
            </html:form>
        </logic:present>
        <logic:notPresent name="myIbd">
            <div class="row">
                <div class="span12">
                    <p>No data received from unit.</p>
                </div>
            </div>
        </logic:notPresent>
    </div>
</div>
