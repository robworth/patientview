<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div id="symptomsContainer">
    <div class="page-header">
        <h1>Colitis</h1>
    </div>

    <div class="row">
        <div class="span5">
            <html:form action="/colitis-update" styleClass="form-horizontal">
                <html:errors/>
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
    </div>
</div>

<bean:define id="graphData" name="graphData" type="java.util.ArrayList<SymptomsData>" />
<%
    List<Integer> graphScores = new ArrayList<Integer>();
    List<String> graphDates = new ArrayList<String>();

    for (SymptomsData symptomsData : graphData) {
        graphScores.add(symptomsData.getScore());
        graphDates.add(symptomsData.getDate());
    }
%>
<script type="text/javascript">
    $(function() {
        IBD.Symptoms.graphData = [<%=StringUtils.join(graphScores.toArray(), ", ")%>];
        IBD.Symptoms.graphDates = ['<%=StringUtils.join(graphDates.toArray(), "', '")%>'];
    });
</script>


