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
    <h1>Medicines</h1>
</div>
<ul class="padded-list">
    <li>
        This section is for your own personal use, to help you keep a track of your current and previous IBD medications.
    </li>
    <li>
        The hospital is not able to keep an accurate list of your IBD medications. In order to complete your own personal
        up-to-date record, please enter your current medicines by clicking on the <span class="btn">Add New Medicine</span>
        icon.
    </li>
    <li>
        If for any reason you discontinue or complete a course of treatment, you can record this by clicking the
        <a class="btn" href="javascript:;">Stop</a> icon.
    </li>
    <li>
        If you take a medication not present in the drop down boxes, you can enter the drug using the dropdown box
        labelled- Other to complete your medicines record.
    </li>
    <li>
        For more information about common medicines used click on the links below.
    </li>
</ul>


<h3>Links for IBD Medicines:</h3>
<ul>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#introduction">Introduction to drugs used in IBD</a></li>
    <li><a target="_blank" href="http://myibdportal.org/guide-to-using-suppositories-enemas">Guide to using suppositories / enemas</a></li>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#how-are-aminosalicylates-5-asas-used">Aminosalicylates</a></li>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#what-are-corticosteroids">Steroids</a></li>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#how-are-immunossuppressants-used-in-ibd">Immunomodulators</a></li>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#how-are-biologics-used-in-ibd">Biologics / Anti- TNFs</a></li>
    <li><a target="_blank" href="http://www.myibdportal.org/drugs-used-in-ibd#how-are-antibiotics-used-in-ibd">Other drugs used in IBD</a></li>
    <li><a target="_blank" href="http://www.nhs.uk/conditions/inflammatory-bowel-disease/pages/clinical-trial.aspx">Research Trials in IBD</a></li>
</ul>

<hr/>

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
                        <th>Frequency</th>
                        <th>Actions</th>
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
                                <logic:present name="myMedication" property="otherMedicationDose">
                                    <bean:write name="myMedication" property="otherMedicationDose" />
                                </logic:present>
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
                        <th>Date Stopped</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dose</th>
                        <th>Frequency</th>
                        <th>Reason for stopping</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate name="stoppedMedications" id="myMedication" indexId="index">
                        <tr>
                            <td>
                                <bean:write name="myMedication" property="dateStartedAsString" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="dateStoppedAsString" />
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
                                <logic:present name="myMedication" property="otherMedicationDose">
                                    <bean:write name="myMedication" property="otherMedicationDose" />
                                </logic:present>
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
