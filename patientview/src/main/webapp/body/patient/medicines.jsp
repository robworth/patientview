<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="650" border="0" cellspacing="1" cellpadding="3">

  <tr valign="top">
    <td colspan="5"><img src="images/space.gif" height="5"/></td>
  </tr>

  <tr valign="top">
    <td colspan="5"><bean:message key="cautionary.medicines" /></td>
  </tr>

  <tr valign="top">
    <td colspan="5"><bean:message key="link.medicines" /></td>
  </tr>

  <tr valign="top">
    <td colspan="5"><img src="images/space.gif" height="5"/></td>
  </tr>

  <logic:empty name="medicines">
    <tr valign="top">
      <td class="tableheader" colspan="10">No medicines uploaded</td>
    </tr>
  </logic:empty>

  <logic:notEmpty name="medicines">

    <logic:present name="user">

        <bean:define id="previousunit" value=""/>

        <tr valign="top">
          <td class="tableheader" colspan="4"><b>Medicines for <bean:write name="user" property="name"/></b></td>
        </tr>



      <tr>
        <td class="tablecellbold" width="75"><b>Start Date</b></td>
        <td class="tablecellbold">Medicine Name</td>
        <td class="tablecellbold">Dose</td>
        <td class="tablecellbold">Source</td>
      </tr>

        <logic:iterate name="medicines" id="medicine">
        <tr>
          <td class="tablecell"><bean:write name="medicine" property="formattedStartDate"/></td> 
          <td class="tablecell"><bean:write name="medicine" property="name"/></td>
          <td class="tablecell"><bean:write name="medicine" property="dose"/></td>
          <td class="tablecell"><bean:write name="medicine" property="shortname"/></td>
        </tr>
      </logic:iterate>

    </logic:present>

  </logic:notEmpty>

</table>

