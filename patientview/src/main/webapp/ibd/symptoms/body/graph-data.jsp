<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<bean:define id="graphScores" name="graphScores" type="java.util.ArrayList" />
{
    "error": "<bean:write name="graphDataError" />",
    "scores": [<%=StringUtils.join(graphScores.toArray(), ", ")%>]
}