<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.worthsoln.ibd.model.symptoms.SymptomsData" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:define id="graphData" name="graphData" type="java.util.ArrayList<SymptomsData>" />
<%
    List<Integer> graphScores = new ArrayList<Integer>();
    List<String> graphDates = new ArrayList<String>();

    for (SymptomsData symptomsData : graphData) {
        graphScores.add(symptomsData.getScore());
        graphDates.add(symptomsData.getDate());
    }
%>
{
    "error": "<bean:write name="graphDataError" />",
    "scores": [<%=StringUtils.join(graphScores.toArray(), ", ")%>]
    "dates": [<%=StringUtils.join(graphDates.toArray(), ", ")%>]
}