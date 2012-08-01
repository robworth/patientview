<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>
<%--<tr>
  <td colspan="10">
    <table width="100%">
      <tr>
          <logic:present role="any_user,patient,demo,superadmin,unitadmin,unitstaff">
              <td class="infostrip" align="left">logged in as: <b><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%>
              </b> <html:link action="logout">log out</html:link></td>
          </logic:present>

        <logic:notPresent role="any_user,patient,demo,superadmin,unitadmin,unitstaff">
          <td class="infostrip" align="left">&nbsp;&nbsp;<html:link action="/logged_in">log in</html:link></td>
        </logic:notPresent>

        <logic:present role="patient">
          <logic:notPresent user="testhd">
            <logic:notPresent user="testpd">
              <logic:notPresent user="testgen">
                <logic:notPresent user="testtx">
                  <td class="infostrip" align="left"><html:link
                      forward="patientPasswordChangeInput">change password</html:link></td>
                </logic:notPresent>
              </logic:notPresent>
            </logic:notPresent>
          </logic:notPresent>
        </logic:present>

        <logic:present role="superadmin,unitadmin,unitstaff">
          <td class="infostrip" align="left"><html:link
              forward="controlPasswordChangeInput">change password</html:link></td>
        </logic:present>

        <logic:present role="superadmin,unitadmin,unitstaff">
          <td class="infostrip"><html:link forward="control">back to Admin Area</html:link></td>
        </logic:present>

          <jsp:include page="include/tenancy_switcher.jsp"/>

        <td class="infostrip" align="right"><dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format></td>
      </tr>
    </table>
  </td>
</tr>--%>
