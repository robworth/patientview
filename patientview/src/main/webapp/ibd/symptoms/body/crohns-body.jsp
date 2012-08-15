<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div id="symptomsContainer">
    <div class="page-header">
        <h1>Crohns</h1>
    </div>

    <div class="row">
        <div class="span5">
            <html:form action="/crohns-update" styleClass="form-horizontal">

                <html:errors/>

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
                        <label class="control-label">I have currently got</label>
                        <div class="controls">
                            <html:select property="abdominalPainId">
                                <html:options collection="abdominalPainList" property="id"
                                              labelProperty="displayText"/>
                            </html:select> abdominal pain at present
                        </div>
                    </div>
                </logic:present>

                <logic:present name="openBowelList" scope="session">
                    <div class="control-group">
                        <label class="control-label"> My bowels are open</label>
                        <div class="controls">
                            <html:select property="openBowels">
                                <html:options collection="openBowelList" property="value" name="value"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="feelingList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Currently I am feeling</label>
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
                        <label class="control-label">I have</label>
                        <div class="controls">
                            <html:select property="complicationId">
                                <html:options collection="crohnsComplicationList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                            complications from my IBD
                        </div>
                    </div>
                </logic:present>

                <logic:present name="massInTummyList" scope="session">
                    <div class="control-group">
                        <label class="control-label">
                            The Doctor has said I have
                        </label>
                        <div class="controls">
                            <html:select property="massInTummyId">
                                <html:options collection="massInTummyList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                            mass in my tummy
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