<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="90%" border="0" cellspacing="1" cellpadding="3">

  <tr valign="top">
    <td colspan="5"><img src="images/space.gif" height="5"/></td>
  </tr>

  <tr valign="top">
    <td colspan="5"><bean:message key="cautionary.letters" /></td>
  </tr>

  <tr valign="top">
    <td colspan="5"><img src="images/space.gif" height="5"/></td>
  </tr>

  <logic:empty name="letters">
    <tr valign="top">
      <td class="tableheader" colspan="10">No letters uploaded</td>
    </tr>
  </logic:empty>

  <logic:notEmpty name="letters">

    <logic:present name="user">

      <tr valign="top">
        <td><img src="images/space.gif" width="15"  height="1"/></td>
        <td class="tableheader" colspan="3"><b>Letters for <bean:write name="user" property="name"/></b></td>
      </tr>

      <tr>
        <td><img src="images/space.gif" width="15"  height="1"/></td>
        <td width="100" class="tablecellbold"><b>Date</b></td>
        <td class="tablecellbold">Letter Type</td>
        <td class="tablecellbold">&nbsp;</td>
      </tr>

      <logic:iterate name="letters" id="letter">
        <tr>
          <td><img src="images/space.gif" width="15" height="1"/></td>
           <td class="tablecell"><bean:write name="letter" property="formattedDate"/></td>
          <td class="tablecell"><bean:write name="letter" property="type"/></td>
          <td class="tablecell"><html:link action="/patient/letterDetail" paramName="letter" paramProperty="id" paramId="letterId">read letter...</html:link>&nbsp;</td>
        </tr>
      </logic:iterate>

    </logic:present>

  </logic:notEmpty>

</table>

