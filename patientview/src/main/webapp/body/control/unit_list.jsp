<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Units</p>

<table cellpadding="3" border="0">

  <logic:present name="units">
    <logic:notEmpty name="units">
      <tr>
        <td class="tableheader">Unit Code</td>
        <td class="tableheader">Name</td>
      </tr>
      <logic:iterate id="unit" name="units">
        <tr>
          <td class="tablecell"><bean:write name="unit" property="unitcode"/></td>
          <td class="tablecell"><bean:write name="unit" property="name"/></td>

          <logic:present role="superadmin,unitadmin">
            <html:form action="/control/unitEdit">
              <html:hidden name="unit" property="unitcode"/>
              <td><html:submit value="Edit" styleClass="formbutton"/></td>
            </html:form>

            <html:form action="/control/unitStat">
              <html:hidden name="unit" property="unitcode"/>
              <td><html:submit value="Stats" styleClass="formbutton"/></td>
            </html:form>
          </logic:present>

        </tr>
      </logic:iterate>
    </logic:notEmpty>
  </logic:present>

  <tr>
    <td>&nbsp;</td>
  </tr>
  <logic:present role="superadmin">
    <tr>

      <html:form action="/control/unitAddInput">
        <td><html:submit value="Add new" styleClass="formbutton"/></td>
      </html:form>
    </tr>
  </logic:present>
</table>
