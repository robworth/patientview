<%@ page import="com.worthsoln.ibd.Ibd" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row" id="addMedication">
    <div class="span12">
        <div class="page-header">
            <h1>Add Medicine</h1>
        </div>

        <html:form action="/medication-update" styleClass="form-horizontal">
            <bean:define id="medicationTypeId" name="medicationForm" property="medicationTypeId" type="java.lang.Long" />
            <bean:define id="medicationId" name="medicationForm" property="medicationId" type="java.lang.Long" />
            <bean:define id="otherMedication" name="medicationForm" property="otherMedication" type="java.lang.String" />

            <html:errors/>

            <div class="control-group">
                <label class="control-label">Date you started the medicine:</label>
                <div class="controls">
                    <div class="input-append date datePicker" data-date="<bean:write name="medicationForm" property="dateStarted"/>">
                        <input name="dateStarted" class="span2" size="16" type="text" value="<bean:write name="medicationForm" property="dateStarted"/>" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                         <span class="help-block">Please use an estimate if the exact date is not known
</span>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Type:</label>
                <div class="controls">
                    <html:select property="medicationTypeId" styleId="medicationTypeId">
                        <html:option value="">Select</html:option>
                        <html:options collection="medicationTypeList" property="id" labelProperty="name"/>
                    </html:select>
                </div>
            </div>

            <div id="medicationContainer" <%=(medicationTypeId <= 0 ? "class=\"hidden\"":"")%>>
                <div class="control-group">
                    <label class="control-label">Medication:</label>
                    <div class="controls" id="medicationLists">
                        <logic:iterate name="medicationTypeList" id="medicationType" type="com.worthsoln.ibd.model.medication.MedicationType">
                            <bean:define id="medications" name="medicationType" property="medications"/>

                            <%
                            String medicationSelectId = "medicationType" + medicationType.getId() + "-medications";
                            String medicationSelectClass = !medicationType.getId().equals(medicationTypeId) ? "hidden":"";
                            boolean medicationSelectDisabled = !medicationType.getId().equals(medicationTypeId);
                            %>
                            <html:select property="medicationId" disabled="<%=medicationSelectDisabled%>" styleId="<%=medicationSelectId%>" styleClass="<%="medicationList " + medicationSelectClass%>">
                                <html:option value="">Select</html:option>
                                <html:options name="medicationType" collection="medications" property="id" labelProperty="name"/>
                                <%
                                if (medicationType.getId() == Ibd.OTHER_MEDICATION_TYPE_ID) {
                                %>
                                    <html:option value="-2">Other</html:option>
                                <%
                                }
                                %>
                            </html:select>
                        </logic:iterate>
                    </div>
                </div>

                <div id="medicationDoseContainer" <logic:lessThan value="1" name="medicationId">class="hidden"</logic:lessThan>>
                    <div class="control-group">
                        <label class="control-label">Dose:</label>
                        <div class="controls">
                            <logic:iterate name="medicationTypeList" id="medicationType">
                                <bean:define id="medications" name="medicationType" property="medications" />

                                <logic:iterate name="medications" id="medication" type="com.worthsoln.ibd.model.medication.Medication">
                                    <bean:define id="allowedDosages" name="medication" property="allowedDosages" />

                                    <%
                                    String medicationDoseSelectId = "medication" + medication.getId() + "-dosages";
                                    String medicationDoseSelectClass = !medication.getId().equals(medicationId) ? "hidden":"";
                                    boolean medicationDoseSelectDisabled = !medication.getId().equals(medicationId);
                                    %>
                                    <html:select property="medicationDoseId" disabled="<%=medicationDoseSelectDisabled%>" styleId="<%=medicationDoseSelectId%>" styleClass="<%="medicationDoseList " + medicationDoseSelectClass%>">
                                        <html:option value="">Select</html:option>
                                        <html:options name="medication" collection="allowedDosages" property="id" labelProperty="formattedValue"/>
                                    </html:select>
                                </logic:iterate>
                            </logic:iterate>
                        </div>
                    </div>
                </div>
                <%
                /*
                 this is really fidly with validation but want to show the other text field if
                 medId NOT GRT THAN 0 AND (medId == -2 OR otherMed.length > 0)
                  */
                boolean showOtherMedication = false;

                if (medicationId <= 0) {
                    if (medicationId == -2 || otherMedication.length() > 0) {
                        showOtherMedication = true;
                    }
                }
                %>
                <div class="<%=showOtherMedication ? "":"hidden"%>" id="medicationOtherContainer">
                    <div class="control-group">
                        <label class="control-label">Other Medication:</label>
                        <div class="controls">
                            <html:textarea property="otherMedication" styleId="otherMedication"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">Other Medication Dose:</label>
                        <div class="controls">
                            <html:textarea property="otherMedicationDose" styleId="otherMedicationDose"/>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">Frequency:</label>
                    <div class="controls">
                        <html:select property="medicationFrequencyId">
                            <html:option value="">Select</html:option>
                            <html:options collection="medicationFrequencyList" property="id" labelProperty="name"/>
                        </html:select>
                    </div>
                </div>
            </div>           
                <div class="form-actions">
                    <html:submit value="Save" styleClass="btn btn-primary"/>
                    <html:link action="/medications" styleClass="btn">Cancel</html:link>
                </div>
        </html:form>
    </div>
</div>
