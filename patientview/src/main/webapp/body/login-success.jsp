<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<logic:present name="splashPage">

    <p class="header"><bean:write name="splashPage" property="headline"/></p>

    <p style="white-space: pre-wrap;"><bean:write filter="false" name="splashPage" property="bodytext"/></p>

    <br />
    <hr class="thinblue" />

</logic:present>

<div class="page-header">
    <h1>Welcome <%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%>, you have logged in successfully</h1>
</div>

<div class="alert alert-info"><b>This is a confidential record.</b> If you should not be reading it please <html:link action="logout">log out</html:link> now.</div>


<table class="table table-striped table-bordered table-condensed">
    <tbody>

    <%
        if (request.getAttribute("lastLogin") != null) {
    %>
          <tr>
            <th>Last login recorded</th>
            <td><%=request.getAttribute("lastLogin")%></td>
          </tr>
        <%
        }
        %>

        <%
        if (request.getAttribute("lastDataDate") != null) {
        %>
        <tr>
            <th>Last data received for this record</th>
            <td>On <%=request.getAttribute("lastDataDate")%>
            <%
              if (request.getAttribute("lastDataFrom") != null) {
            %>
            from <%=request.getAttribute("lastDataFrom")%>
            <%
              }
            %>
            </td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>


<%
  if (request.getAttribute("isPatient") != null) {
%>
<form action="/patient/patient_details.do">
    <input type="submit" value="Continue" class="btn" tabindex="3" />
</form>

<%
} else {
%>
<form action="/control/index.jsp">
   <input type="submit" value="Continue" class="btn" style="border-style: outset;" tabindex="3" />
</form>
<%
  }
%>


<html:link href="/password-advice.jsp">Find out more about secure passwords.</html:link>
