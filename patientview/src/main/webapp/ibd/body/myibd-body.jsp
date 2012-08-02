<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>My IBD</h1>
        </div>
    </div>
</div>
<logic:present name="myIbd">
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Primary Diagnosis:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diagnosis.name"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Disease Extent:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diseaseExtent.name"/>
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
                </div>
            </div>
        </div>
        <div class="span6">
            <div class="medicalDiagram">
                <img src="img/content/proctitis.jpg" alt="Proctitis"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span3">
            Current Medications:
        </div>
        <div class="span9">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Date Started</th>
                    <th>Type</th>
                    <th>Medication</th>
                    <th>Dose</th>
                    <th>No Tabs/Granules</th>
                    <th>Frequency</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>12/12/2012</td>
                    <td>Oral 5 Aminosalicylate</td>
                    <td>Pentasa</td>
                    <td>400mg</td>
                    <td>4</td>
                    <td>Frequencey</td>
                </tr>
                <tr>
                    <td>12/12/2012</td>
                    <td>Oral 5 Aminosalicylate</td>
                    <td>Pentasa</td>
                    <td>400mg</td>
                    <td>4</td>
                    <td>Frequencey</td>
                </tr>
                <tr>
                    <td>12/12/2012</td>
                    <td>Oral 5 Aminosalicylate</td>
                    <td>Pentasa</td>
                    <td>400mg</td>
                    <td>4</td>
                    <td>Frequencey</td>
                </tr>
                </tbody>
            </table>
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
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">General:</label>
                </div>
                <div class="span3 controls">
                    <p>Weight: <bean:write name="myIbd" property="weight"/></p>

                    <p>IBD Related Family History: <bean:write name="myIbd" property="familyHistory.name"/></p>

                    <p>Smoking History: <bean:write name="myIbd" property="smoking.name"/></p>

                    <p>Surgical History: <bean:write name="myIbd" property="surgery.name"/></p>

                    <p>Vaccination Record: <bean:write name="myIbd" property="vaccinationRecord.name"/></p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <html:link action="/ibd/myibd-edit">Edit</html:link>
        </div>
    </div>
</logic:present>
<logic:notPresent name="myIbd">
    <div class="row">
        <div class="span12">
            <html:link action="/ibd/myibd-edit">Add</html:link>
        </div>
    </div>
</logic:notPresent>
