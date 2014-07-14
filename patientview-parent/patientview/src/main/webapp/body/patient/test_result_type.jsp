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
    <h1>Select Test Result Type</h1>
</div>


<logic:present name="resultsHeadings">
    <html:form action="/patient/enterTestResult" styleId="form">
    <h3>Select the test to entre:</h3>
    <select name="patientResultName" id="resultType">
        <logic:iterate name="resultsHeadings" id="heading">
            <option value="<bean:write name="heading" property="headingcode"/>" ><bean:write name="heading" property="heading"/></option>
        </logic:iterate>
    </select>
    </html:form>
</logic:present>

<script>

    $(document).ready(function() {
        $('#resultType').change(function(){
            var testType = this.value;
            if (testType == "bpsys" || testType == "bpdia") {
                location.href='patient_entry_bp.jsp';
                return;
            } else if (testType == "glucose") {
                location.href='patient_entry_glucose.jsp';
                return;
            } else if (testType == "weight") {
                location.href='patient_entry_weight.jsp';
                return;
            } else {
                $('#form').submit();
            }
        })
    });
</script>
