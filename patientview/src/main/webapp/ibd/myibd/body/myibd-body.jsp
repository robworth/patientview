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

<div class="page-header">
    <h1>My IBD</h1>
</div>
<ul class="padded-list">
    <li>
        My IBD helps you keep a track of your inflammatory bowel disease. The information is provided from the records held
        at the hospital. If there are any obvious errors, please contact us by <html:link action="/patient/ibd-contact">clicking
        here</html:link>.
    </li>
    <li>
        The hospital is not able to keep an accurate list of your IBD medications. To complete your own personal up-to-date
         record, please enter your current medicines by clicking on the <span class="btn">Add New Medicine</span>  icon in
         the <html:link action="/medications">Medicines</html:link> section.
    </li>
</ul>
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
                    <logic:present name="myIbd" property="complications">
                        <bean:write name="myIbd" property="formattedComplications" filter="false"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="complications">
                        None
                    </logic:notPresent>

                    <logic:present name="complicationLink">
                        <a href="<bean:write name="complicationLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Other parts of the body affected:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="myIbd" property="bodyPartAffected">
                        <bean:write name="myIbd" property="bodyPartAffected"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="bodyPartAffected">
                        None
                    </logic:notPresent>

                    <logic:present name="bodyPartAffectedLink">
                        <a href="<bean:write name="bodyPartAffectedLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Weight:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="weight">
                        <bean:write name="weight" />
                    </logic:present>
                    <logic:notPresent name="weight">
                        Unknown
                    </logic:notPresent>

                    <logic:present name="weightLink">
                        <a href="<bean:write name="weightLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">IBD Related Family History:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="myIbd" property="familyHistory">
                        <bean:write name="myIbd" property="familyHistory"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="familyHistory">
                        None
                    </logic:notPresent>

                    <logic:present name="familyHistoryLink">
                        <a href="<bean:write name="familyHistoryLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Surgical History:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="myIbd" property="surgery">
                        <bean:write name="myIbd" property="surgery"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="surgery">
                        No previous operations
                    </logic:notPresent>

                    <logic:present name="surgeryLink">
                        <a href="<bean:write name="surgeryLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Smoking History:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="myIbd" property="smoking">
                        <bean:write name="myIbd" property="smoking"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="smoking">
                        Never smoked
                    </logic:notPresent>

                    <logic:present name="smokingLink">
                        <a href="<bean:write name="smokingLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
                </div>
            </div>

            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Vaccination Record:</label>
                </div>

                <div class="span3 controls">
                    <logic:present name="myIbd" property="vaccinationRecord">
                        <bean:write name="myIbd" property="vaccinationRecord"/>
                    </logic:present>
                    <logic:notPresent name="myIbd" property="vaccinationRecord">
                        Not known
                    </logic:notPresent>

                    <logic:present name="vaccinationRecordLink">
                        <a href="<bean:write name="vaccinationRecordLink" />" target="_blank"><i class="icon-info-sign"></i></a>
                    </logic:present>
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
                    <label class="control-label">Nurses:</label>
                </div>

                <div class="span3 controls">
                    <bean:write name="myIbd" property="nurses"/>

                    <logic:present name="nursesLink">
                        <a href="<bean:write name="nursesLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
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

</logic:present>
<logic:notPresent name="myIbd">
    <div class="row">
        <div class="span12">
            <p>No data received from unit.</p>
        </div>
    </div>
</logic:notPresent>
