<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<logic:present name="graphData">
    <div class="span7" id="graphContainer">
        <form action="#" class="form-inline" id="graphForm">
            <input type="hidden" name="graphType" id="graphType" value="1" />

            <label class="control-label" for="graphDataFromDate">From:</label>
            <div class="input-append date datePicker" data-date="<bean:write name="fromDate" />">
                <input name="fromDate" id="graphDataFromDate" class="fromDate span2" size="16" type="text" value="<bean:write name="fromDate" />" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            <label class="control-label" for="graphDataToDate">To:</label>
            <div class="input-append date datePicker" data-date="<bean:write name="toDate" />">
                <input name="toDate" id="graphDataToDate" class="toDate span2" size="16" type="text" value="<bean:write name="toDate" />" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            <input type="submit" class="btn btn-primary" value="Update" />
            <button id="clearData" class="btn" value="Clear">Clear</button>
        </form>

        <div id="graph" class="linegraph"></div>
    </div>

    <bean:define id="symptomsGraphData" name="graphData" type="com.worthsoln.ibd.model.symptoms.SymptomsGraphData" />
    <%
        List<Integer> graphScores = new ArrayList<Integer>();
        List<String> graphDates = new ArrayList<String>();

        for (SymptomsData symptomsData : symptomsGraphData.getGraphData()) {
            graphScores.add(symptomsData.getScore());
            graphDates.add(symptomsData.getDate());
        }
    %>
    <script type="text/javascript">
        $(function() {
            IBD.Symptoms.graphData = [<%=StringUtils.join(graphScores.toArray(), ", ")%>];
            IBD.Symptoms.graphDates = ['<%=StringUtils.join(graphDates.toArray(), "', '")%>'];
            IBD.Symptoms.alertMarkers.severe = <%=symptomsGraphData.getSevereLevel()%>;
            IBD.Symptoms.alertMarkers.moderate = <%=symptomsGraphData.getModerateLevel()%>;
            IBD.Symptoms.alertMarkers.mild = <%=symptomsGraphData.getMildLevel()%>;
        });
    </script>
</logic:present>


