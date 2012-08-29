<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="page-header">
    <logic:present name="myIbd">
        <html:link action="/myibd-edit" styleClass="btn pull-right">Edit</html:link>
    </logic:present>

    <h1>My IBD</h1>
</div>
<p>
        My IBD helps you keep a track of your inflammatory bowel disease. The information is provided from records held
         at the hospital. If there are any obvious errors please <html:link action="/patient/contact">contact the IBD
         team</html:link>. To complete an up-to-date personal record, please enter your medications details by
         <html:link action="/patient/patient_details">clicking here</html:link>.
     </p>

<hr/>
<logic:present name="myIbd">

    <div class="row paragraphSizeTopMargin">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Primary Diagnosis:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diagnosis.name"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Disease Extent:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diseaseExtent.name"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Year of Diagnosis:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="yearOfDiagnosisAsString"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Complications:</label>
                </div>
                <div class="span3 controls">
                    <bean:size id="complicationsListSize" name="myIbd" property="complications"/>
                    <logic:iterate name="myIbd" property="complications" id="complication" indexId="index">
                        <bean:write name="complication"
                                    property="name"/><%= (complicationsListSize == (index + 1) ? "" : ",") %>
                    </logic:iterate>
                </div>
            </div>
        </div>
        <div class="span6">
            <logic:present name="myIbd" property="diagnosis">
                <div class="medicalDiagram">
                    <img src="ibd/img/content/<bean:write name="myIbd" property="diseaseExtent.diagram"/>" alt="<bean:write name="myIbd" property="diseaseExtent.name"/>"/>
                    <dl class="medicalDiagramKey span4 pull-right">
                        <dt class="redKeyItem keyItem">Red</dt>
                        <dd class="span3">represents the part of the bowel affected by your condition.</dd>
                    </dl>
                </div>
            </logic:present>
        </div>
    </div>
    <div class="row">
        <div class="span3 control-label">
            Current Medications:
        </div>
        <div class="span9">
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
                    </tr>
                    </thead>
                    <tbody>
                    <logic:iterate name="currentMedications" id="myMedication" indexId="index">
                        <tr>
                            <td>
                                <bean:write name="myMedication" property="dateStartedAsString" />
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
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Other parts of the body affected:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="bodyPartAffected.name"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Year for Surveillance Colonoscopy:</label>
        </div>
        <div class="span3 controls">
            <bean:write name="myIbd" property="yearForSurveillanceColonoscopyAsString"/>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Named Consultant:</label>
        </div>
        <div class="span3 controls">
            <bean:write name="myIbd" property="namedConsultant"/>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Nurses:</label>
        </div>
        <div class="span3 controls">
            <bean:write name="myIbd" property="nurses"/>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <html:link action="/myibd-edit" styleClass="btn pull-right">Edit</html:link>
        </div>
    </div>
</logic:present>
<logic:notPresent name="myIbd">
    <div class="row">
        <div class="span12">
            <html:link action="/myibd-edit" styleClass="btn">Add</html:link>
        </div>
    </div>
</logic:notPresent>
