<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Enter My Own Results</p>


<logic:notPresent role="patient">
   <logic:notPresent name="patientBeingViewedNhsNo" scope="session">
     <p>You are not viewing a patient and so cannot enter results.</p>    
   </logic:notPresent>
     <logic:present name="patientBeingViewedNhsNo" scope="session">
       <html:link forward="patientEntryBloodPressure">Enter blood pressures for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <br />
       <br />
       <html:link forward="patientEntryGlucose">Enter glucose for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <br />
       <br />
       <html:link forward="patientEntryWeight">Enter weight for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
       <br />
       <br />
       <html:link forward="patientEntryResultComment">Enter comment for patient with NHS No: <bean:write name="patientBeingViewedNhsNo" scope="session"/></html:link>
     </logic:present>
</logic:notPresent>

<logic:present role="patient">
  <html:link forward="patientEntryBloodPressure">Enter my blood pressure</html:link>
  <br />
  <br />
  <html:link forward="patientEntryGlucose">Enter my glucose values</html:link>
  <br />
  <br />
  <html:link forward="patientEntryWeight">Enter my weight</html:link>
  <br />
  <br />
  <html:link forward="patientEntryResultComment">Enter a comment on my results</html:link>
</logic:present>