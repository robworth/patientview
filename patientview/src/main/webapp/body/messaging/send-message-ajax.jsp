<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page contentType="application/json" %>
{
    "errors": <logic:present name="errors"><bean:write name="errors" filter="false" /></logic:present><logic:notPresent name="errors">[]</logic:notPresent>,
    "message": <logic:present name="message"><bean:write name="message" filter="false"/></logic:present><logic:notPresent name="message">null</logic:notPresent>
}