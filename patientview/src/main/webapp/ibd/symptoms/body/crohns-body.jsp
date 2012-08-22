<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div id="symptomsContainer">
    <div class="page-header">
        <h1>Crohns</h1>
    </div>

    <div class="row">
        <div class="span5">
            <html:form action="/crohns-update" styleClass="form-horizontal" styleId="symptomsForm">
                <html:errors/>

                <input type="hidden" name="fromDate" class="fromDate" value="" />
                <input type="hidden" name="toDate" class="toDate" value="" />

                <div class="control-group">
                    <label class="control-label">Date</label>

                    <div class="controls">
                        <div class="input-append date datePicker"
                             data-date="<bean:write name="crohnsSymptomsForm" property="symptomDate"/>">
                            <input name="symptomDate" class="span2" size="16" type="text"
                                   value="<bean:write name="crohnsSymptomsForm" property="symptomDate"/>" readonly>
                            <span class="add-on"><i class="icon-th"></i></span>
                        </div>
                    </div>
                </div>

                <logic:present name="abdominalPainList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Do you have any abdominal pain at present?</label>
                        <div class="controls">
                            <html:select property="abdominalPainId">
                                <html:options collection="abdominalPainList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="openBowelList" scope="session">
                    <div class="control-group">
                        <label class="control-label">What number of times are your bowels open per day?</label>
                        <div class="controls">
                            <html:select property="openBowels">
                                <html:options collection="openBowelList" property="value" name="value"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="feelingList" scope="session">
                    <div class="control-group">
                        <label class="control-label">How are you feeling?</label>
                        <div class="controls">
                            <html:select property="feelingId">
                                <html:options collection="feelingList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="crohnsComplicationList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Do you have any complications from your IBD?</label>
                        <div class="controls">
                            <html:select property="complicationId">
                                <html:options collection="crohnsComplicationList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>

                        </div>
                    </div>
                </logic:present>

                <logic:present name="massInTummyList" scope="session">
                    <div class="control-group">
                        <label class="control-label">
                            Has the Doctor informed you about any mass in your tummy?
                        </label>
                        <div class="controls">
                            <html:select property="massInTummyId">
                                <html:options collection="massInTummyList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>

                        </div>
                    </div>
                </logic:present>

                <div class="form-actions">
                    <html:submit value="Save" styleClass="btn btn-primary"/>
                </div>
            </html:form>
        </div>

        <jsp:include page="graph.jsp" />
    </div>
</div>
