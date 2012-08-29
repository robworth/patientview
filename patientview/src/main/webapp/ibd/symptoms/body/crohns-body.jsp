<%@ page import="com.worthsoln.ibd.model.enums.Severity" %>
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

                <input type="hidden" name="fromDate" class="fromDate" value=""/>
                <input type="hidden" name="toDate" class="toDate" value=""/>

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
                        <label class="control-label">How many times are your bowels open a day?</label>

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

        <jsp:include page="graph.jsp"/>

        <div class="span7">
            <logic:present name="myIbdSeverityLevel">
                <div>
                    <bean:define id="myIbdSeverityLevel" name="myIbdSeverityLevel"
                                 type="com.worthsoln.ibd.model.MyIbdSeverityLevel"/>

                    <h3 class="<%=myIbdSeverityLevel.getSeverity().name().toLowerCase()%>"><%=myIbdSeverityLevel.getSeverity().name()%>
                    </h3>

                    <%
                        if (myIbdSeverityLevel.getSeverity().equals(Severity.SEVERE)) {
                    %>
                    <p>Your symptoms suggest you are having a severe flare up of your Crohns. You need to speak to a
                        healthcare professional about your condition.</p>

                    <p>If your bowels are open <strong>more than 6 times a day and</strong> are experiencing <strong>one or more</strong> of the following
                        <strong>alarm symptoms</strong> you need to <strong>contact a healthcare professional immediately</strong>. A severe flare up is a
                        potentially life threatening illness.</p>

                    <p><strong>Alarm symptoms</strong></p>

                    <ul>
                        <li>Blood mixed in with the stool</li>
                        <li>Fever more than 37.8 °C</li>
                        <li>Severe abdominal pain and tenderness</li>
                        <li>Persistent vomiting</li>
                        <li>Significant weight loss (Greater than 5%)</li>
                        <li>High pulse rate (Pulse greater than 90 beats per minute)</li>
                        <li>CRP blood test level above 30 mg/L</li>
                        <li>New fistula</li>
                    </ul>

                    <p>Please <strong>do not</strong> take anti-diarrhoeal treatment as it could worsen your condition.</p>

                    <p><strong>Contact Details</strong></p>

                    <p>To contact the IBD nurses from Monday to Friday 0900-1700 call the IBD helpline on 0161 206 4023 or
                        email via the link here. Please leave you name and contact details. On the weekends you can either
                        call the local Out of Hours GP service or attend your local A&E department for assessment.</p>
                    <%
                    } else if (myIbdSeverityLevel.getSeverity().equals(Severity.MODERATE)) {
                    %>
                    <p>Your symptom score suggest you are having a flare up. You and your IBD team have made a plan of what
                        treatment you can start taking yourself, so you do not have to wait for a hospital or GP visit. To
                        learn more about treating flare ups click here.
                        The following treatment plan has been recommended.</p>

                    <p>You are welcome to contact us at anytime if you are worried about anything to do with your Crohn's
                        disease or if you want an appointment.</p>

                    <p><strong>You should definitely contact us in the following circumstances:</strong></p>

                    <ul>
                        <li>If your flare up comes back as soon as you stop or reduce treatment.</li>
                        <li>If you need to use more than two courses of oral steroids tablets a year.</li>
                        <li>If you are losing weight without dieting.</li>
                        <li>If you are losing bloods from your bowels between flare ups.</li>
                        <li>If you have any worrying symptoms</li>
                    </ul>
                    <%
                    } else if (myIbdSeverityLevel.getSeverity().equals(Severity.MILD)) {
                    %>
                    <p>
                        Excellent. Your Crohn's disease seems under good control. Please carry on taking your current
                        medicines. If you want to learn more about maintaining remission and health recommendations for
                        people with IBD click here.
                    </p>
                    <%
                        }

                        if (myIbdSeverityLevel.getTreatment() != null && myIbdSeverityLevel.getTreatment().length() > 0) {
                    %>
                    <h4>Flare Up Medication</h4>

                    <p>
                        <%=myIbdSeverityLevel.getTreatment()%>
                    </p>
                    <%
                        }
                    %>
                </div>
            </logic:present>
        </div>
    </div>
</div>
