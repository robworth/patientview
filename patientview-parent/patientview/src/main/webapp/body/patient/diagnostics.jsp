<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    <div class="span12">
        <div class="page-header">
            <h1>Diagnostics</h1>
        </div>

        <ul class="padded-list">
            <li>
                The Diagnostics section lists your previous imaging and endoscopy tests direct from the hospital record.
            </li>
            <li>
                Please note only the date and type of test is recorded. The full report is not available.
            </li>
            <li>
                If you have any further questions about your tests please contact us by
                <html:link action="/patient/ibd-contact">clicking here.</html:link>
            </li>
            <li>
                To learn more about the investigations used commonly in IBD click on the following links:
            </li>
        </ul>



        <h3>Links about Diagnostics</h3>
        <ul>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#endoscopy">Endoscopy (Colonosocopy / Sigmoidoscopy/ Gastroscopy)</a></li>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#plain-x-rays">X-Rays- Plain and Barium tests</a></li>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#other-x-ray-tests-and-scans">CT scans and MRI scans</a></li>
        </ul>

        <%--<p><bean:message key="diagnostics.intro" /></p>--%>
        <hr/>

            <h2>Imaging</h2>

        <table class="table table-bordered table-striped paragraphSizeTopMargin">
            <thead>
            <tr>
                <th>Exam Date</th>
                <th>Exam</th>
            </tr>
            </thead>
            <tbody>
            <logic:iterate name="diagnosticsImaging" id="image" type="org.patientview.patientview.model.Diagnostic">
                <tr>
                    <td>
                        <bean:write name="image" property="formattedDatestamp"/>
                    </td>

                    <td>
                        <bean:write name="image" property="description"/>
                    </td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
            <h2>Endoscopy</h2>
        <table class="table table-bordered table-striped paragraphSizeTopMargin">
            <thead>
            <tr>
                <th>Procedure Date</th>
                <th>Procedure</th>
            </tr>
            </thead>
            <tbody>
            <logic:iterate name="diagnosticsEndoscopy" id="endoscopy" type="org.patientview.patientview.model.Diagnostic">
                <tr>
                    <td>
                        <bean:write name="endoscopy" property="formattedDatestamp"/>
                    </td>

                    <td>
                        <bean:write name="endoscopy" property="description"/>
                    </td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
    </div>
</div>
