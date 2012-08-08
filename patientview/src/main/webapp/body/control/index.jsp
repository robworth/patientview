<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
 <logic:present name="newses">
  <logic:notEmpty name="newses">
    <div  class="page-header">
        <h1>News</h1>
    </div>


    <table cellpadding="3" width="90%">

      <tr>
        <td colspan="2"><hr class="thinblue"/></td>
      </tr>

    <logic:iterate id="news" name="newses" >
      <tr>
        <td><b><bean:write name="news" property="headline" /></b></td>
        <td align="right" valign="center"><bean:write name="news" property="formattedDatestamp"/></td>
      </tr>
      <tr>
        <td colspan="2" ><bean:write name="news" property="bodyForHtml" filter="false"/></td>
      </tr>
      <tr>
        <td colspan="2"><hr class="thinblue"/></td>
      </tr>
    </logic:iterate>

    </table>

  </logic:notEmpty>

  <logic:empty name="newses">
    <p class="header">No news.</p>
  </logic:empty>
</logic:present>
</div>

</div> <%--Close row from top_nav.jsp--%>