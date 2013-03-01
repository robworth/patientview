<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
            <html:link forward="patientEntryBloodPressure" styleClass="thumbnail"><div class="launchPadTextAlternative rpvResults">Blood Pressure</div></html:link>
            <div class="caption">
              <h5>Enter my blood pressure</h5>
              <p><html:link forward="patientEntryBloodPressure" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryGlucose" styleClass="thumbnail"><div class="launchPadTextAlternative rpvResults">Glucose Values</div></html:link>
            <div class="caption">
              <h5>Enter my glucose values</h5>
              <p><html:link forward="patientEntryGlucose" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryWeight" styleClass="thumbnail"><div class="launchPadTextAlternative rpvResults">Weight</div></html:link>
            <div class="caption">
              <h5>Enter my weight</h5>
              <p><html:link forward="patientEntryWeight" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
    <li class="span3">
        <div class="thumbnail">
            <html:link forward="patientEntryResultComment" styleClass="thumbnail"><div class="launchPadTextAlternative rpvResults">Comment</div></html:link>
            <div class="caption">
              <h5>Enter a comment on my results</h5>
              <p><html:link forward="patientEntryResultComment" styleClass="btn">Enter</html:link></p>
            </div>
        </div>
    </li>
</ul>
</logic:present>