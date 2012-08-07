<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Medicines</h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="span12">
        <html:link action="/medication-add" styleClass="btn">Add New Medicine</html:link>
    </div>
</div>

<div class="row">
    <div class="span12">
        <h2>Current Medicines</h2>

        <logic:present name="currentMedications">
            <logic:iterate name="currentMedications" id="medication" indexId="index">

            </logic:iterate>
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

            <logic:iterate name="stoppedMedications" id="medication" indexId="index">

            </logic:iterate>
        </div>
    </div>
</logic:present>

<div class="row">
    <div class="span6">

    </div>
</div>
