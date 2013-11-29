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

<div class="row" id="editCarePlan">
    <div class="span12">
        <div class="page-header">
            <h1>Checkups</h1>
        </div>
    </div>
    <%
        if (request.getAttribute("eyeCheckup") == null && request.getAttribute("footCheckup") == null) {
    %>
            <div class="alert">No Checkups uploaded</div>
    <%
        }
    %>

    <logic:notEmpty name="eyeCheckup">
        <logic:present name="eyeCheckup">
            <div class="span12">
                <h3>Eye Screening</h3>
                <p></p>
            </div>

            <div class="span12">
                <table width="650" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
                    <tr>
                        <th class="tablecellbold" colspan="3">Date of last retinal screening: <bean:write name="eyeCheckup" property="lastRetinalDateFormatted"/></th>
                        <th class="tablecellbold" colspan="3">Place of last retinal screening: <bean:write name="eyeCheckup" property="lastRetinalPlace"/></th>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="3">Right Eye</td>
                        <td class="tablecell" colspan="3">Left Eye</td>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="1">R Grade</td>
                        <td class="tablecell" colspan="1">M Grade</td>
                        <td class="tablecell" colspan="1">VA</td>
                        <td class="tablecell" colspan="1">R Grade</td>
                        <td class="tablecell" colspan="1">M Grade</td>
                        <td class="tablecell" colspan="1">VA</td>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="rightRGrade"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="rightMGrade"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="rightVA"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="leftRGrade"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="leftMGrade"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="eyeCheckup" property="leftVA"/></td>
                    </tr>
                </table>
            </div>
        </logic:present>
    </logic:notEmpty>

    <logic:notEmpty name="footCheckup">
        <logic:present name="footCheckup">
            <div class="span12">
                <h3>Feet</h3>
                <p></p>
            </div>

            <div class="span12">
                <table width="650" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
                    <tr>
                        <th class="tablecellbold" colspan="4">Date foot check: <bean:write name="footCheckup" property="footCheckDateFormatted"/></th>
                        <th class="tablecellbold" colspan="4">Place of foot check: <bean:write name="footCheckup" property="footCheckPlace"/></th>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="4">Right Foot</td>
                        <td class="tablecell" colspan="4">Left Foot</td>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="1">DP Pulse</td>
                        <td class="tablecell" colspan="1">PT Pulse</td>
                        <td class="tablecell" colspan="1">10g Monofilament</td>
                        <td class="tablecell" colspan="1">Risk score</td>
                        <td class="tablecell" colspan="1">DP Pulse</td>
                        <td class="tablecell" colspan="1">PT Pulse</td>
                        <td class="tablecell" colspan="1">10g Monofilament</td>
                        <td class="tablecell" colspan="1">Risk score</td>
                    </tr>
                    <tr>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="rightDpPulse"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="rightPtPulse"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="rightMonofilament"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="rightRiskScore"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="leftDpPulse"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="leftPtPulse"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="leftMonofilament"/></td>
                        <td class="tablecell" colspan="1"><bean:write name="footCheckup" property="leftRiskScore"/></td>
                    </tr>
                </table>
            </div>
        </logic:present>
    </logic:notEmpty>
</div>