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

<logic:present tenancy="ibd">
    <p>The information within this site is intended to provide advice only. Any information entered by yourself, is for your personal use and is not monitored by the IBD team.</p>

    <p>If you have any questions about the website or suspect a mistake, please do not hesitate to contact us through the IBD helpline on 0161 206 4023 or via the email address: <a href="mailto:myibdportal@srft.nhs.uk">myibdportal@srft.nhs.uk</a> (We aim to review and respond to messages by the end of the next working day).</p>

    <p>If you have any urgent health concerns and are unable to contact IBD team you should contact your GP, NHS Direct or GP Out of Hours services.</p>
</logic:present>

<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy().getContext()%>/patient/patient_details.do">
    <input type="submit" value="Continue" class="btn" tabindex="3" />
</form>

<%
} else {
%>
<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy().getContext()%>/control/index.jsp">
   <input type="submit" value="Continue" class="btn" style="border-style: outset;" tabindex="3" />
</form>
<%
  }
%>

<a href="/password-advice.jsp">Find out more about secure passwords.</a>
