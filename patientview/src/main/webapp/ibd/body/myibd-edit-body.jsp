<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
	<div class="span12">
		<h1>My IBD</h1>
	</div>
</div>
<html:form action="/ibd/myibd-update">
    <div class="row">
        <div class="span6">
            <logic:present name="diagnosisList" scope="session">
                <div class="row control-group">
                    <div class="span3">
                        <label class="control-label">Primary Diagnosis:</label>
                    </div>
                    <div class="span3 controls">
                        <html:select property="diagnosisId">
                            <html:option value="">Select</html:option>
                            <html:options collection="diagnosisList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="diseaseExtentList" scope="session">
                <div class="row control-group">
                    <div class="span3">
                        <label class="control-label">Disease Extent:</label>
                    </div>
                    <div class="span3 controls">
                        <html:select property="diseaseExtentId">
                            <html:option value="">Select</html:option>
                            <html:options collection="diseaseExtentList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="complicationList" scope="session">
                <div class="row control-group">
                    <div class="span3">
                        <label class="control-label">Complications:</label>
                    </div>
                    <div class="span3 controls">
                        <html:select property="complicationIds" multiple="true">
                            <html:options collection="complicationList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <logic:present name="bodyPartAffectedList" scope="session">
                <div class="row control-group">
                    <div class="span3">
                        <label class="control-label">Other parts of the body affected:</label>
                    </div>
                    <div class="span3 controls">
                        <html:select property="bodyPartAffectedId">
                            <html:options collection="bodyPartAffectedList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
        </div>
    </div>
    <div class="row control-group">
        <div class="span3">
            <label class="control-label">Weight:</label>
        </div>
        <div class="span3 controls">
            <html:text property="weight"/>
        </div>
    </div>
    <logic:present name="familyHistoryList" scope="session">
        <div class="row control-group">
            <div class="span3">
                <label class="control-label">IBD Related Family History:</label>
            </div>
            <div class="span3 controls">
                <html:select property="familyHistoryId">
                    <html:options collection="familyHistoryList" property="id" labelProperty="name"/>
                </html:select>
            </div>
        </div>
    </logic:present>
    <logic:present name="smokingList" scope="session">
        <div class="row control-group">
            <div class="span3">
                <label class="control-label">Smoking History:</label>
            </div>
            <div class="span3 controls">
                <html:select property="smokingId">
                    <html:options collection="smokingList" property="id" labelProperty="name"/>
                </html:select>
            </div>
        </div>
    </logic:present>
    <logic:present name="surgeryList" scope="session">
        <div class="row control-group">
            <div class="span3">
                <label class="control-label">Surgery History:</label>
            </div>
            <div class="span3 controls">
                <html:select property="surgeryId">
                    <html:options collection="surgeryList" property="id" labelProperty="name"/>
                </html:select>
            </div>
        </div>
    </logic:present>
    <logic:present name="vaccinationRecordList" scope="session">
        <div class="row control-group">
            <div class="span3">
                <label class="control-label">Vaccination History:</label>
            </div>
            <div class="span3 controls">
                <html:select property="vaccinationRecordId">
                    <html:options collection="vaccinationRecordList" property="id" labelProperty="name"/>
                </html:select>
            </div>
        </div>
    </logic:present>

    <div class="row">
        <div class="span12">
            <div class="error">
                <html:errors/>
            </div>

            <p>
                <html:submit value="Save" styleClass="btn"/>
                <html:link action="/ibd/myibd" styleClass="btn">Cancel</html:link>
            </p>
        </div>
    </div>
</html:form>