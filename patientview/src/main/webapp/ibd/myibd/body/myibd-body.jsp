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
    My IBD helps you keep a track of your inflammatory bowel disease. The information is provided from the records held
    at the hospital. If there are any obvious errors, please contact us by <html:link action="/patient/contact">clicking
    here</html:link>.
 </p>
 <p>
    The hospital is not able to keep an accurate list of your IBD medications. To complete your own personal up-to-date
     record, please enter your current medicines by clicking on the <span class="btn">Add New Medicine</span>  icon in
     the <html:link action="/patient/medicines">Medicines</html:link> section.
</p>
<p>
    (can you make sure this hyperlink takes to Medicines as goes to My details at present, thanks)
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

                    <logic:present name="primaryDiagnosisLink">
                        <a href="<bean:write name="primaryDiagnosisLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Disease Extent:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diseaseExtent.name"/>

                    <logic:present name="diseaseExtentLink">
                        <a href="<bean:write name="diseaseExtentLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Year of Diagnosis:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="yearOfDiagnosisAsString"/>

                    <logic:present name="yearOfDiagnosisLink">
                        <a href="<bean:write name="yearOfDiagnosisLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
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

                    <logic:present name="complicationLink">
                        <a href="<bean:write name="complicationLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
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

                    <logic:present name="bodyPartAffectedLink">
                        <a href="<bean:write name="bodyPartAffectedLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
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

            <logic:present name="yearForSurveillanceColonoscopyLink">
                <a href="<bean:write name="yearForSurveillanceColonoscopyLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Named Consultant:</label>
        </div>
        <div class="span3 controls">
            <bean:write name="myIbd" property="namedConsultant"/>

            <logic:present name="namedConsultantLink">
                <a href="<bean:write name="namedConsultantLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Nurses:</label>
        </div>
        <div class="span3 controls">
            <bean:write name="myIbd" property="nurses"/>

            <logic:present name="nursesLink">
                <a href="<bean:write name="nursesLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">General:</label>
                </div>
                <div class="span3 controls">
                    <p>
                        Weight: <bean:write name="myIbd" property="weight"/>

                        <logic:present name="weightLink">
                            <a href="<bean:write name="weightLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                        </logic:present>
                    </p>

                    <p>
                        IBD Related Family History: <bean:write name="myIbd" property="familyHistory.name"/>

                        <logic:present name="familyHistoryLink">
                            <a href="<bean:write name="familyHistoryLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                        </logic:present>
                    </p>

                    <p>
                        Smoking History: <bean:write name="myIbd" property="smoking.name"/>

                        <logic:present name="smokingLink">
                            <a href="<bean:write name="smokingLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                        </logic:present>
                    </p>

                    <p>
                        Surgical History: <bean:write name="myIbd" property="surgery.name"/>

                        <logic:present name="surgeryLink">
                            <a href="<bean:write name="surgeryLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                        </logic:present>
                    </p>

                    <p>
                        Vaccination Record: <bean:write name="myIbd" property="vaccinationRecord.name"/>

                        <logic:present name="vaccinationRecordLink">
                            <a href="<bean:write name="vaccinationRecordLink" />" target="_blank"><i class="icon-info-sign"></i></a>
                        </logic:present>
                    </p>
                </div>
            </div>
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
