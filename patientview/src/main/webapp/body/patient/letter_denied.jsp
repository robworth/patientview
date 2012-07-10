<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="400" border="0" cellspacing="1" cellpadding="3">
      <tr valign="top">
        <td><img src="images/space.gif" width="15"  height="1"/></td>
        <td align="left" colspan="2">
            <html:link action="/patient/letters"><< back to letters</html:link>
        </td>
      </tr>

  <tr valign="top">
    <td colspan="10"><img src="images/space.gif"/></td>
  </tr>

  <tr>
    <td><img src="images/space.gif" width="15"  height="1"/></td>
    <td class="tablecellbold">You do not have permission to view this letter.</td>
  </tr>

</table>

