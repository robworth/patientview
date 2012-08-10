<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Medicines</h1>
</div>

<p>
    <bean:message key="cautionary.medicines" />
</p>
<p>
<bean:message key="link.medicines" />
</p>

<logic:empty name="medicines">
      <div class="alert">No medicines uploaded</div>
</logic:empty>

<logic:notEmpty name="medicines">

<logic:present name="user">

    <bean:define id="previousunit" value=""/>

      <h2 class="tableheader" colspan="4">Medicines for <bean:write name="user" property="name"/></h2>

<table width="650" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
    <thead>
      <tr>
        <th class="tablecellbold" width="75"><b>Start Date</b></th>
        <th class="tablecellbold">Medicine Name</th>
        <th class="tablecellbold">Dose</th>
        <th class="tablecellbold">Source</th>
      </tr>
    </thead>
    <tbody>
    <logic:iterate name="medicines" id="medicine">
        <tr>
          <td class="tablecell"><bean:write name="medicine" property="formattedStartDate"/></td> 
          <td class="tablecell"><bean:write name="medicine" property="name"/></td>
          <td class="tablecell"><bean:write name="medicine" property="dose"/></td>
          <td class="tablecell"><bean:write name="medicine" property="shortname"/></td>
        </tr>
    </logic:iterate>
    </tbody>
    </logic:present>

  </logic:notEmpty>

</table>
