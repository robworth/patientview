<%@ page import="com.worthsoln.ibd.model.medication.MedicationType" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row" id="addMedication">
    <div class="span12">
        <div class="page-header">
            <h1>Stop Medicine</h1>
        </div>

        <logic:present name="myMedication" scope="session">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Date Started</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dose</th>
                        <th>Frequency</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <bean:write name="myMedication" property="dateStarted" />
                        </td>
                        <td>
                            <bean:write name="myMedication" property="medicationType.name" />
                        </td>
                        <td>
                            <logic:present name="myMedication" property="medication">
                                <bean:write name="myMedication" property="medication.name" />
                            </logic:present>
                            <logic:present name="myMedication" property="otherMedication">
                                <bean:write name="myMedication" property="otherMedication" />
                            </logic:present>
                        </td>
                        <td>
                            <logic:present name="myMedication" property="medicationDose">
                                <bean:write name="myMedication" property="medicationDose.formattedValue" />
                            </logic:present>
                        </td>
                        <td>
                            <bean:write name="myMedication" property="medicationFrequency.name" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </logic:present>

        <html:form action="/medication-stop" styleClass="form-horizontal">
            <html:errors/>

            <html:hidden property="submit" value="true" />
            <html:hidden property="id" />

            <div class="control-group" >
                <label class="control-label">Reason for stopping:</label>
                <div class="controls">
                    <html:textarea property="reasonForStopping" />
                </div>
            </div>

            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
                <html:link action="/medications" styleClass="btn">Cancel</html:link>
            </div>
        </html:form>
    </div>
</div>
