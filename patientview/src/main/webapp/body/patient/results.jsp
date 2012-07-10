<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="690" border="0" cellspacing="1" cellpadding="3">
      <tr valign="top">
        <td colspan="12" align="left">
            <div id="resultsPanelsLabel">
                Result panels :
                &nbsp;
                <logic:equal value="" name="panelNav" property="previousPanel">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </logic:equal>
                <logic:notEqual value="" name="panelNav" property="previousPanel">
                    <html:link action="/patient/results"  paramId="panel" paramName="panelNav" paramProperty="firstPanel" styleClass="paginate">|&lt;</html:link>
                    &nbsp;
                    <html:link action="/patient/results"  paramId="panel" paramName="panelNav" paramProperty="previousPanel" styleClass="paginate">&lt;</html:link>
                    &nbsp;
                </logic:notEqual>
            </div>
            <ul class="resultsPanels">
            <logic:iterate id="panel" name="panelNav" property="panels"  >
                <logic:equal value="true" name="panel" property="currentPanel">
                    <li><bean:write name="panel" property="panel" /></li>
                </logic:equal>
                <logic:notEqual value="true" name="panel" property="currentPanel">
                    <li>
                        <html:link action="/patient/results"  paramId="panel" paramName="panel" paramProperty="panel"><bean:write name="panel" property="panel" />
                        <span class="hover">
                            <logic:iterate name="panel" property="resultHeadings" id="heading" type="com.worthsoln.patientview.resultheading.ResultHeading" >
                                <%= heading.getHeadingcode() %>
                            </logic:iterate>
                        </span>
                        </html:link>
                    </li>
                </logic:notEqual>
            </logic:iterate>
            </ul>
            <div class="pagination">
                &nbsp;
                <logic:equal value="" name="panelNav" property="nextPanel">
                    &nbsp;&nbsp;&nbsp;
                </logic:equal>
                <logic:notEqual value="" name="panelNav" property="nextPanel">
                    <html:link action="/patient/results"  paramId="panel" paramName="panelNav" paramProperty="nextPanel" styleClass="paginate"><strong>More results</strong></html:link>
                    &nbsp;
                    <html:link action="/patient/results"  paramId="panel" paramName="panelNav" paramProperty="lastPanel" styleClass="paginate">&gt;|</html:link>
                </logic:notEqual>
                &nbsp;
            </div>
        </td>
      </tr>
</table>

<table width="600" border="0" cellspacing="1" cellpadding="3">
          <%--
  <tr valign="top">
    <td colspan="10"><img src="images/space.gif"/></td>
  </tr>
            --%>
  <logic:empty name="results">
    <tr valign="top">
      <td class="tableheader" colspan="10">No results uploaded</td>
    </tr>
  </logic:empty>

  <logic:notEmpty name="results">

    <logic:present name="user">

      <tr valign="top">
        <td class="tableheader" colspan="14"><b>Test results for <bean:write name="user" property="name"/></b></td>
      </tr>

      <tr>
        <td width="" class="tablecellbold"><b>Date and time</b></td>
          <td width="" class="tablecell">Label</td>
            <logic:iterate name="resultsHeadings" id="heading">
              <td width="" class="tablecell"><a href="<bean:write name="heading" property="link"/>" target="_blank" title="<bean:write name="heading" property="rollover"/>"><bean:write name="heading" property="heading"/></a></td>
            </logic:iterate>
          <td width="" class="tablecellbold">Source</td>
      </tr>

      <logic:iterate name="results" id="result" type="com.worthsoln.patientview.Result" length="resultsPerPage" offset="resultsOffset" >
        <tr>
          <td width="" class="tablecellbold" nowrap="true"><b><bean:write name="result" property="formattedTimeStamp"/></b></td>
          <td width="" class="tablecellbold"><bean:write name="result" property="prepost"/></td>
            <logic:iterate name="resultsHeadings" id="heading" type="com.worthsoln.patientview.resultheading.ResultHeading" >
              <bean:define id="content" value="<%= result.getValue(heading.getHeadingcode()) %>" />

              <logic:empty name="content">
                <td width="" class="tablecell"></td>
              </logic:empty>

              <logic:notEmpty name="content">
                <logic:equal value="resultcomment" name="heading" property="headingcode">
                  <td width="" class="tablecell"> <html:link action="/patient/patientEnteredCommentDisplay" paramId="commentId" paramName="content" >read...</html:link> </td>
                </logic:equal>

                <logic:notEqual value="resultcomment" name="heading" property="headingcode">
                  <td width="" class="tablecell"><bean:write name="content"/></td>
                </logic:notEqual>
              </logic:notEmpty>

            </logic:iterate>
          <td width="" class="tablecellbold" nowrap="true"><bean:write name="result" property="shortname"/></td>
        </tr>
      </logic:iterate>

    </logic:present>

  </logic:notEmpty>

</table>
<table width="600" border="0" cellspacing="1" cellpadding="3">
      <tr valign="top">
        <td align="right">
          More results :
                &nbsp;
                <logic:equal value="" name="pageNav" property="previousPanel">
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </logic:equal>
                <logic:notEqual value="" name="pageNav" property="previousPanel">
                   <a href='results.do?panel=<%= request.getParameter("panel")%>&page=<bean:write name="pageNav" property="firstPanel" />' class="paginate">|&lt;</a>
                   &nbsp;
                   <a href='results.do?panel=<%= request.getParameter("panel")%>&page=<bean:write name="pageNav" property="previousPanel" />' class="paginate">&lt;</a>
                   &nbsp;
                </logic:notEqual>

          <logic:iterate id="pag" name="pages">
            <logic:equal value="true" name="pag" property="currentPanel">
              <bean:write name="pag" property="panel" />&nbsp;
            </logic:equal>
            <logic:notEqual value="true" name="pag" property="currentPanel">
              <a href='results.do?panel=<%= request.getParameter("panel")%>&page=<bean:write name="pag" property="panel" />'><bean:write name="pag" property="panel" /></a>&nbsp;
            </logic:notEqual>
          </logic:iterate>

                &nbsp;
                <logic:equal value="" name="pageNav" property="nextPanel">
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </logic:equal>
                <logic:notEqual value="" name="pageNav" property="nextPanel">
                   <a href='results.do?panel=<%= request.getParameter("panel")%>&page=<bean:write name="pageNav" property="nextPanel" />' class="paginate">&gt;</a>
                   &nbsp;
                   <a href='results.do?panel=<%= request.getParameter("panel")%>&page=<bean:write name="pageNav" property="lastPanel" />' class="paginate">&gt;|</a>
                   &nbsp;
                </logic:notEqual>
        </td>
      </tr>
</table>

