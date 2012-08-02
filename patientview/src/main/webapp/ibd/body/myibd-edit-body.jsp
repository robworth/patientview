<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
	<div class="span12">
        <div class="page-header">
		    <h1>My IBD</h1>
        </div>

        <html:form action="/myibd-update" styleClass="form-horizontal">
            <html:errors/>

            <logic:present name="diagnosisList" scope="session">
                <div class="control-group">
                        <label class="control-label">Primary Diagnosis:</label>
                    <div class="controls">
                        <html:select property="diagnosisId">
                            <html:option value="">Select</html:option>
                            <html:options collection="diagnosisList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="diseaseExtentList" scope="session">
                <div class="control-group">
                        <label class="control-label">Disease Extent:</label>
                    <div class="controls">
                        <html:select property="diseaseExtentId">
                            <html:option value="">Select</html:option>
                            <html:options collection="diseaseExtentList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="complicationList" scope="session">
                <div class="control-group">
                    <label class="control-label">Complications:</label>
                    <div class="controls">
                        <html:select property="complicationIds" multiple="true">
                            <html:options collection="complicationList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="bodyPartAffectedList" scope="session">
                <div class="control-group">
                    <label class="control-label">Other parts of the body affected:</label>
                    <div class="controls">
                        <html:select property="bodyPartAffectedId">
                            <html:options collection="bodyPartAffectedList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <div class="control-group">
                    <label class="control-label">Weight:</label>
                <div class="controls">
                    <html:text property="weight"/>
                </div>
            </div>
            <logic:present name="familyHistoryList" scope="session">
                <div class="control-group">
                    <label class="control-label">IBD Related Family History:</label>
                    <div class="controls">
                        <html:select property="familyHistoryId">
                            <html:options collection="familyHistoryList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="smokingList" scope="session">
                <div class="control-group">
                        <label class="control-label">Smoking History:</label>
                    <div class="controls">
                        <html:select property="smokingId">
                            <html:options collection="smokingList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="surgeryList" scope="session">
                <div class="control-group">
                    <label class="control-label">Surgery History:</label>
                    <div class="controls">
                        <html:select property="surgeryId">
                            <html:options collection="surgeryList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>
            <logic:present name="vaccinationRecordList" scope="session">
                <div class="control-group">
                    <label class="control-label">Vaccination History:</label>
                    <div class="controls">
                        <html:select property="vaccinationRecordId">
                            <html:options collection="vaccinationRecordList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </logic:present>

            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
                <html:link action="/myibd" styleClass="btn">Cancel</html:link>
            </div>
        </html:form>
	</div>
</div>
