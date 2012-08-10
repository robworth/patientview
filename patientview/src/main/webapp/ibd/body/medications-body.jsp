<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Medicines</h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="span12">
        <html:link action="/medication-add" styleClass="btn">Add New Medicine</html:link>
    </div>
</div>

<div class="row">
    <div class="span12">
        <h2 class="paragraphSizeTopMargin">Current Medicines</h2>

        <logic:present name="currentMedications">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Date Started</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dose</th>
                        <th>No Tabs/Granules</th>
                        <th>Frequency</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="currentMedications" id="myMedication" indexId="index">
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
                                <bean:write name="myMedication" property="medicationNoOf.name" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="medicationFrequency.name" />
                            </td>
                            <td>
                                <html:form action="/medication-stop" method="get">
                                    <html:hidden property="id" name="myMedication" />
                                    <html:submit value="Stop" styleClass="btn" />
                                </html:form>
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>
        <logic:notPresent name="currentMedications">
            <p>No current medicines</p>
        </logic:notPresent>
    </div>
</div>

<logic:present name="stoppedMedications">
    <div class="row">
        <div class="span12">
            <h2>Stopped Medicines</h2>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Date Started</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dose</th>
                        <th>No Tabs/Granules</th>
                        <th>Frequency</th>
                        <th>Reason for stopping</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="stoppedMedications" id="myMedication" indexId="index">
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
                                <bean:write name="myMedication" property="medicationNoOf.name" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="medicationFrequency.name" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="reasonForStopping" />
                            </td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </div>
    </div>
</logic:present>

<div class="row">
    <div class="span6">

    </div>
</div>
