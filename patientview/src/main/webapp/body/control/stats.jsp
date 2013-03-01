<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">
    <%--
  <tr valign="top">
  <td colspan="10"><img src="images/space.gif"/></td>
  </tr>
    --%>
    <logic:empty name="unitstats">
        <p>No unit statistics available</p>
    </logic:empty>

    <logic:notEmpty name="unitstats">

        <logic:present name="unit">

            <div class="page-header">
                <logic:notPresent role="superadmin">
                    <h1>Usage statistics for <bean:write name="unit" property="name"/></h1>
                </logic:notPresent>
                <logic:present role="superadmin">
                    <h1>Usage statistics for <bean:write name="unit" property="name"/></h1>
                </logic:present>
            </div>

            <table cellpadding="3" border="0" class="table table-striped table-bordered table-condensed table-massive">
                <thead>
                <tr>
                    <th width="23%" class="tablecellbold"><b>Month</b></th>
                    <logic:iterate name="statsHeadings" id="heading">
                        <th width="7%" class="tablecell" align="center"><bean:write name="heading" property="heading"/></th>
                    </logic:iterate>
                    <logic:present role="superadmin">
                        <th width="7%" class="tablecell" align="center">Download CSV </br> (right-click and save as...)</th>
                    </logic:present>
                </tr>
                </thead>

                <logic:iterate name="unitstats" id="stat" type="com.worthsoln.patientview.unitstat.UnitMonthStats">
                <tr>
                    <td width="23%" class="tablecellbold"><b><bean:write name="stat" property="yearmonth"/></b></td>

                    <logic:iterate name="statsHeadings" id="heading" type="com.worthsoln.patientview.unitstat.StatsHeading">
                    <td width="7%" class="tablecell" align="center"><%= stat.getValue(heading.getHeading()) %>
                    </td>
                    </logic:iterate>

                    <logic:present role="superadmin">
                    <td width="7%" class="tablecell" align="center"><a
                            href="unitstatfiles/<bean:write name="stat" property="downloadFilename"/>" type="text/csv"><bean:write
                            name="stat" property="downloadFilename"/></a>
                    </td>
                    </logic:present>

                    </logic:iterate>
            </table>
        </logic:present>

    </logic:notEmpty>

</div>
</div>