<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">

    <div class="page-header">
        <h1>Units</h1>
    </div>

<table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed">

  <logic:present name="units">
    <logic:notEmpty name="units">
      <tr>
        <th class="tableheader">Unit Code</th>
        <th class="tableheader">Name</th>
        <th></th>
        <th></th>
      </tr>
      <logic:iterate id="unit" name="units">
        <tr>
          <td class="tablecell"><bean:write name="unit" property="unitcode"/></td>
          <td class="tablecell"><bean:write name="unit" property="name"/></td>

          <logic:present role="superadmin,unitadmin">
            <html:form action="/control/unitEdit">
              <html:hidden name="unit" property="unitcode"/>
              <td><html:submit value="Edit" styleClass="btn"/></td>
            </html:form>

            <html:form action="/control/unitStat">
              <html:hidden name="unit" property="unitcode"/>
              <td><html:submit value="Stats" styleClass="btn"/></td>
            </html:form>
          </logic:present>

        </tr>
      </logic:iterate>
    </logic:notEmpty>
  </logic:present>
</table>

  <logic:present role="superadmin">

      <html:form action="/control/unitAddInput">
        <html:submit value="Add new" styleClass="btn"/>
      </html:form>
  </logic:present>
</div>
</div>