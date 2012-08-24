<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ page import="com.worthsoln.ibd.model.enums.Severity" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div id="symptomsContainer">
    <div class="page-header">
        <h1>Colitis</h1>
    </div>

    <div class="row">
        <div class="span5">
            <html:form action="/colitis-update" styleClass="form-horizontal" styleId="symptomsForm">
                <html:errors/>

                <input type="hidden" name="fromDate" class="fromDate" value="" />
                <input type="hidden" name="toDate" class="toDate" value="" />

                <div class="control-group">
                    <label class="control-label">Date</label>

                    <div class="controls">
                        <div class="input-append date datePicker"
                             data-date="<bean:write name="colitisSymptomsForm" property="symptomDate"/>">
                            <input name="symptomDate" class="span2" size="16" type="text"
                                   value="<bean:write name="colitisSymptomsForm" property="symptomDate"/>" readonly>
                            <span class="add-on"><i class="icon-th"></i></span>
                        </div>
                    </div>
                </div>

                <logic:present name="stoolsDayList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Number of Stools (Day)</label>

                        <div class="controls">
                            <html:select property="numberOfStoolsDaytimeId">
                                <html:options collection="stoolsDayList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="stoolsNightList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Number of Stools (Night)</label>

                        <div class="controls">
                            <html:select property="numberOfStoolsNighttimeId">
                                <html:options collection="stoolsNightList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="toiletTimingList" scope="session">
                    <div class="control-group">
                        <label class="control-label">When I go to the toilet?</label>

                        <div class="controls">
                            <html:select property="toiletTimingId">
                                <html:options collection="toiletTimingList" property="id" labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="presentBloodList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Is there blood present?</label>

                        <div class="controls">
                            <html:select property="presentBloodId">
                                <html:options collection="presentBloodList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="feelingList" scope="session">
                    <div class="control-group">
                        <label class="control-label">How do I feel?</label>

                        <div class="controls">
                            <html:select property="feelingId">
                                <html:options collection="feelingList" property="id"
                                              labelProperty="displayText"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <logic:present name="furtherComplicationList" scope="session">
                    <div class="control-group">
                        <label class="control-label">Do I have any further complications?</label>

                        <div class="controls">
                            <html:select property="complicationId">
                                <html:options collection="furtherComplicationList" property="id"
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
                    <p>Your symptoms suggest you are having a <strong>severe</strong> flare up. You need to speak to a healthcare professional within the next 24 hours.</p>

                    <p>If your bowels are open <strong>more than 6 times a day and</strong> are experiencing <strong>one or more</strong> of the following
                        <strong>alarm symptoms</strong> you need to <strong>contact a healthcare professional immediately</strong>. A severe flare up is a
                        potentially life threatening illness.</p>

                    <p><strong>Alarm symptoms</strong></p>

                    <ul>
                        <li>Blood mixed in with the stool</li>
                        <li>Fever more than 37.8 °C</li>
                        <li>High pulse rate (Pulse greater than 90 beats per minute)</li>
                        <li>Severe abdominal pain and tenderness</li>
                        <li>CRP blood test level above 30 mg/L</li>
                        <li>Significant weight loss (Greater than 5%)</li>
                    </ul>

                    <p>Please <strong>do not</strong> take anti-diarrhoeal treatment as it could worsen your condition.</p>

                    <p><strong>Contact Details</strong></p>

                    <p>To contact the IBD nurses from Monday to Friday 0900-1700 call the IBD helpline on 0161 206 4023 or email via the link here.  Please leave you name and contact details. On the weekends you can either call the local Out of Hours GP service or attend your local A&E department for assessment.</p>
                    <%
                    } else if (myIbdSeverityLevel.getSeverity().equals(Severity.MODERATE)) {
                    %>
                    <p>Your symptom score suggest you are having a flare up. You and your IBD team have made a plan of what
                        treatment you can start taking yourself, so you do not have to wait for a hospital or GP visit. To
                        learn more about treating flare ups click here.
                        The following treatment plan has been recommended.</p>

                    <p>You are welcome to contact us at anytime if you are worried about anything to do with your ulcerative colitis
                        or if you want an appointment.</p>

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
                        Excellent. Your ulcerative colitis seems under good control. Please carry on taking your current
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