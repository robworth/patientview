<%@ page import="com.worthsoln.ibd.model.enums.Severity" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div id="symptomsContainer">
    <div class="page-header">
        <h1>Crohns</h1>
    </div>
    <ul class="padded-list">
        <li>
            The symptom checker can be used to keep a personal record of your Crohn's symptoms over time.
        </li>
        <li>
            The scores generated from the questions are based on previous validated research studies.
        </li>
        <li>
            If you have 3 days of worsening symptoms from your bowels, record your symptoms every day on the chart for 2
            weeks.
        </li>
        <li>
            Please note the symptom checker is not reviewed by healthcare professionals and is for your personal use
            only.
        </li>
        <li>
            Guidance generated by the chart is advice only. If you have any concerns about your condition, please
            do not hesitate to <html:link action="/patient/ibd-contact">contact us by email</html:link> or via the IBD
            helpline on 0161 20 60423.
        </li>
        <li>
            For more information about flare-ups <a target="_blank" href="http://www.myibdportal.org/about-flare-ups">
            click here.</a>
        </li>
    </ul>
    <hr/>

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
                            Has the Doctor informed you about any mass (lump) in your abdomen?
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


                    <%
                        if (myIbdSeverityLevel.getSeverity().equals(Severity.SEVERE)) {
                    %>

                    <h3 class="<%=myIbdSeverityLevel.getSeverity().name().toLowerCase()%>">
                        Very Active
                    </h3>


                    <p>
                        Your symptom score suggests your Crohn's disease is very active and you are experiencing a
                        severe flare-up. We advise you to monitor your symptoms daily on the chart and contact the IBD
                        team or speak to your GP within the next 24 hours.
                    </p>
                    <p>
                        If this is not possible and you are concerned then call <a target="_blank" href="http://www.nhsdirect.nhs.uk/">NHS Direct</a> on 0845 46 47, your local GP
                        out-of-hours service or attend your local A&E department.
                    </p>

                    <p><strong>Common Symptoms from a severe flare-up</strong></p>

                    <p>
                        The symptoms from a severe flare-up depend on which part and how much of the gut is affected.
                    </p>

                    <p>
                        With a severe flare up one or more of the following symptoms are commonly described:
                    </p>

                    <ul>
                        <li>Significant abdominal pain and tenderness</li>
                        <li>Worsening diarrhoea over several days</li>
                        <li>Blood mixed in with diarrhoea</li>
                        <li>High fever (temperature greater than 37.8 &deg;C)</li>
                        <li>Losing weight without dieting</li>
                        <li>Persistent vomiting</li>
                        <li>Severe joint aches and swelling</li>
                    </ul>

                    <p>Please <strong>do not</strong> take anti-diarrhoeal treatment as it could worsen your condition.</p>


                    <p><strong>Strictures</strong></p>

                    <p>
                        If you have a known stricture and you are experiencing persistent vomiting with cramping
                        abdominal pain, you may be suffering from bowel obstruction.  Further symptoms from a blockage
                        include constipation and increased abdominal swelling. In these circumstances please contact us.
                    </p>

                    <p><strong>Contact Details</strong></p>

                    <p>
                        To contact the IBD nurses from Monday to Friday 0900-1700 call the IBD helpline on 0161 206 4023
                        or email via the About Your Health box on the Contact page.  Please leave your:
                    </p>

                    <ul>
                        <li>Name</li>
                        <li>Hospital number</li>
                        <li>Contact details</li>
                        <li>A short message</li>
                    </ul>

                    <%
                    } else if (myIbdSeverityLevel.getSeverity().equals(Severity.MODERATE)) {
                    %>

                    <h3 class="<%=myIbdSeverityLevel.getSeverity().name().toLowerCase()%>">
                        Active
                    </h3>

                    <p>
                        Your symptom score suggests your Crohn's disease is active and you are experiencing a flare-up.
                    </p>

                    <p>
                        Please login and monitor your symptoms daily for at least 14 days. If your symptoms deteriorate
                        or are no better within 5 days you need to contact us by <html:link action="/patient/contact">email</html:link> or alternatively via the IBD
                        telephone helpline (0161 206 4023).
                    </p>

                    <p>Please <strong>do not</strong> take anti-diarrhoeal treatment as it could worsen your condition.</p>

                    <p><strong>You should definitely contact us in the following circumstances:</strong></p>

                    <ul>
                        <li>If your flare up comes back as soon as you stop or reduce treatment.</li>
                        <li>If you need to use more than two courses of oral steroids tablets a year.</li>
                        <li>If you are losing weight without dieting.</li>
                        <li>If you are losing bloods from your bowels between flare ups.</li>
                        <li>If you have any worrying symptoms</li>
                    </ul>

                    <p>
                        In carefully selected individuals dependent on you type of Crohns, the IBD team in clinic may
                        have made a self-management plan if you are experiencing a flare-up. If so, the following
                        treatment has been recommended:
                    </p>
                    <%
                        if (myIbdSeverityLevel.getTreatment() != null && myIbdSeverityLevel.getTreatment().length() > 0) {
                    %>
                    <p>
                        <%=myIbdSeverityLevel.getTreatment()%>
                    </p>

                    <%
                        }
                    %>

                    <p><strong>Strictures</strong></p>

                    <p>
                        If you have a known stricture and you are experiencing persistent vomiting with cramping
                        abdominal pain, you may be suffering from bowel obstruction.  Further symptoms from a blockage
                        include constipation and increased abdominal swelling. In these circumstances please contact us.
                    </p>

                    <p><strong>Contact Details</strong></p>

                    <p>
                        To contact the IBD nurses from Monday to Friday 0900-1700 call the IBD helpline on 0161 206 4023
                        or email via the About Your Health box on the Contact page.  Please leave your:
                    </p>

                    <ul>
                        <li>Name</li>
                        <li>Hospital number</li>
                        <li>Contact details</li>
                        <li>A short message</li>
                    </ul>

                    <%
                    } else if (myIbdSeverityLevel.getSeverity().equals(Severity.MILD)) {
                    %>

                    <h3 class="<%=myIbdSeverityLevel.getSeverity().name().toLowerCase()%>">
                        Inactive (Remission)
                    </h3>

                    <p>
                        Excellent. Your Crohn's disease seems under good control. Please carry on taking your current
                        medicines. If you want to learn more about maintaining remission and
                        <a href="http://www.myibdportal.org/staying-well-with-ibd">staying well with IBD, click here.
                        </a>
                    </p>
                    <%
                        }
                    %>
                </div>
            </logic:present>
        </div>
    </div>
</div>
