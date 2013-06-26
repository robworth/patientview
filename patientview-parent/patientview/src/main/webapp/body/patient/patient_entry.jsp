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

<html:xhtml/>

<div class="page-header">
    <h1>Enter My Own Results</h1>
</div>


<logic:notPresent role="patient">
   <logic:notPresent name="patientBeingViewedNhsNo" scope="session">
     <div class="alert alert-error">
       You are not viewing a patient and so cannot enter results.
     </div>
   </logic:notPresent>
     <logic:present name="patientBeingViewedNhsNo" scope="session">
       <html:link forward="patientEntryBloodPressure">Enter blood pressures for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <html:link forward="patientEntryGlucose">Enter glucose for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <html:link forward="patientEntryWeight">Enter weight for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <html:link forward="patientEntryResultComment">Enter comment for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
     </logic:present>
</logic:notPresent>

<logic:present role="patient">
<ul class="thumbnails">
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryBloodPressure" styleClass="thumbnail"><div class="launchPadTextAlternative renalResults">Blood Pressure</div></html:link>
            <div class="caption">
              <h5>Enter my blood pressure</h5>
              <p><html:link forward="patientEntryBloodPressure" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryGlucose" styleClass="thumbnail"><div class="launchPadTextAlternative renalResults">Glucose Values</div></html:link>
            <div class="caption">
              <h5>Enter my glucose values</h5>
              <p><html:link forward="patientEntryGlucose" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryWeight" styleClass="thumbnail"><div class="launchPadTextAlternative renalResults">Weight</div></html:link>
            <div class="caption">
              <h5>Enter my weight</h5>
              <p><html:link forward="patientEntryWeight" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryResultComment" styleClass="thumbnail"><div class="launchPadTextAlternative renalResults">Comment</div></html:link>
            <div class="caption">
              <h5>Enter a comment on my results</h5>
              <p><html:link forward="patientEntryResultComment" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
</ul>
</logic:present>
