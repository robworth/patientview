<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Users for Unit <bean:write name="unit" property="name"/></h1>
</div>

<logic:notEmpty name="unitUsers">
  <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
      <tr>
        <th class="tableheader">Name</th>
        <th class="tableheader">Username</th>
        <th class="tableheader">Role</th>
        <th class="tableheader">Email</th>
        <th class="tableheader">Email Verified</th>
        <th class="tableheader">Last Login</th>
        <th class="tableheader">Password</th>
        <th></th>
        <th></th>
      </tr>
    <logic:iterate id="unitUser" name="unitUsers">
      <tr>
        <td class="tablecell"><bean:write name="unitUser" property="name"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="username"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="displayRole"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="email"/></td>
        <td class="tablecell">
            <logic:equal value="false" name="unitUser" property="emailverfied">
                <big><font color="red">&#10008;</font></big>
            </logic:equal>
            <logic:equal value="true" name="unitUser" property="emailverfied">
                <big><font color="green">&#10004;</font></big>
            </logic:equal>
        </td>
        <td class="tablecell"><bean:write name="unitUser" property="lastlogonFormatted"/></td>
          <td class="tablecell">
              <logic:equal value="true" name="unitUser" property="accountlocked">
                  <font color="red">locked</font>
              </logic:equal>
              <logic:equal value="false" name="unitUser" property="accountlocked">
                  <big><font color="green">&#10004;</font></big>
              </logic:equal>
          </td>

          <logic:present role="superadmin,unitadmin">
            <td>
              <html:form action="/control/unitUserEditInput">
                <html:hidden name="unitUser" property="username" />
                <html:hidden name="unit" property="unitcode" />
                <html:submit value="Edit" styleClass="btn" />
              </html:form>
            </td>
        </logic:present>

        <logic:present role="superadmin,unitadmin">
            <td>
              <html:form action="/control/activityByUser">
                <html:hidden name="unitUser" property="username" />
                <html:submit value="Activity" styleClass="btn" />
              </html:form>
            </td>
        </logic:present>

      </tr>
    </logic:iterate>
   </table>
 </logic:notEmpty>
</div>
