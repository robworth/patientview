<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>



<div class="page-header">
    <h1>Letters</h1>
</div>

<html:link action="/patient/letters" styleClass="btn"><< back to letters</html:link>

<table width="470" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped paragraphSizeTopMargin">
    <thead>
        <tr>
            <th width="100" class="tablecellbold">Date</th>
            <th class="tablecellbold">Letter Type</th>
        </tr>
    </thead>

  <tr>
    <td class="tablecell"><bean:write name="letter" property="formattedDate"/></td>
    <td class="tablecell"><bean:write name="letter" property="type"/></td>
  </tr>
</table>
<div>
    <bean:write name="letter" property="formattedContent" filter="false"/>
</div>
