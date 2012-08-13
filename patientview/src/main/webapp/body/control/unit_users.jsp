<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Users for Unit <bean:write name="unit" property="name"/></h1>
</div>

<logic:notEmpty name="unitUsers">
  <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">
      <tr>
        <td class="tableheader">Name</td>
        <td class="tableheader">Username</td>
        <td class="tableheader">Role</td>
        <td></td>
        <td></td>
      </tr>
    <logic:iterate id="unitUser" name="unitUsers">
      <tr>
        <td class="tablecell"><bean:write name="unitUser" property="name"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="username"/></td>
        <td class="tablecell"><bean:write name="unitUser" property="displayRole"/></td>

        <logic:present role="superadmin,unitadmin">
          <html:form action="/control/unitUserEditInput">
            <html:hidden name="unitUser" property="username" />
            <html:hidden name="unit" property="unitcode" />
            <td><html:submit value="Edit" styleClass="btn" /></td>
          </html:form>
        </logic:present>

        <logic:present role="superadmin,unitadmin">
          <html:form action="/control/activityByUser">
            <html:hidden name="unitUser" property="username" />
            <td><html:submit value="Activity" styleClass="btn" /></td>
          </html:form>
        </logic:present>

      </tr>
    </logic:iterate>
   </table>
 </logic:notEmpty>
</div>