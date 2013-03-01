<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:define id="symptomsGraphData" name="graphData" type="com.worthsoln.ibd.model.symptoms.SymptomsGraphData" />
<%
    String scoresString = "";
    String datesString = "";

    List<Integer> graphScores = new ArrayList<Integer>();
    List<String> graphDates = new ArrayList<String>();

    for (SymptomsData symptomsData : symptomsGraphData.getGraphData()) {
        graphScores.add(symptomsData.getScore());
        graphDates.add(symptomsData.getDate());
    }

    if (!graphScores.isEmpty()) {
        scoresString = StringUtils.join(graphScores.toArray(), ", ");
    }

    if (!graphDates.isEmpty()) {
        datesString = "\"" + StringUtils.join(graphDates.toArray(), "\", \"") + "\"";
    }
%>
{
"error": "<%=symptomsGraphData.getError()%>",
"scores": [<%=scoresString%>],
"dates": [<%=datesString%>]
}