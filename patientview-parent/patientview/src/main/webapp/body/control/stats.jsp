<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

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

                <logic:iterate name="unitstats" id="stat" type="org.patientview.patientview.unitstat.UnitMonthStats">
                <tr>
                    <td width="23%" class="tablecellbold"><b><bean:write name="stat" property="yearmonth"/></b></td>

                    <logic:iterate name="statsHeadings" id="heading" type="org.patientview.patientview.unitstat.StatsHeading">
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
