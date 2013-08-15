<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

<logic:present name="splashPage">

    <p class="header"><bean:write name="splashPage" property="headline"/></p>

    <p style="white-space: pre-wrap;"><bean:write filter="false" name="splashPage" property="bodyTextForHtml"/></p>

    <br />
    <hr class="thinblue" />

</logic:present>

<div class="page-header">
    <h1>Welcome <%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%>, you have logged in successfully</h1>
</div>

<div class="details-tbl-width pull-left">
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
            <td><%=request.getAttribute("lastDataDate")%>
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

<logic:present specialty="ibd">
    <p>The information within this site is intended to provide advice only. Any information entered by yourself, is for your personal use and is not monitored by the IBD team.</p>

    <p>If you have any questions about the website or suspect a mistake, please do not hesitate to contact us through the IBD helpline on 0161 206 4023 or via the email address: <a href="mailto:myibdportal@srft.nhs.uk">myibdportal@srft.nhs.uk</a> (We aim to review and respond to messages by the end of the next working day).</p>

    <p>If you have any urgent health concerns and are unable to contact IBD team you should contact your GP, NHS Direct or GP Out of Hours services.</p>
</logic:present>

<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/patient/patient_details.do">
    <input type="submit" value="Continue" class="btn" tabindex="3" />
</form>

<%
} else {
%>
<form action="/<%=LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty().getContext()%>/control/index.jsp">
   <input type="submit" value="Continue" class="btn" style="border-style: outset;" tabindex="3" />
</form>
<%
  }
%>

<a href="/password-advice.jsp">Find out more about secure passwords.</a>
</div>
<jsp:include page="../common/include/news_list.jsp"/>
