<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<div class="page-header">
    <h1>Join Requests</h1>
</div>

    <div class="span10">
        <div class="row">
            <form action="/web/control/joinRequestList" method="post" id="displayForm">
                <input type="hidden" name="page" id="page" value=""/>
                <html:submit value="Show incomplete" styleId="incomplete" styleClass="btn" style="float:left;margin-left:5px;"/>
                <html:submit value="Show complete" styleClass="btn" styleId="complete" style="float:left;margin-left:5px;"/>
                <html:submit value="Show all" styleClass="btn" styleId="all" style="float:left;margin-left:5px;"/>
            </form>

        </div>
    </div>
    <div class="span10" style="margin-left: 10px;margin-bottom:5px;">
        <div class="row" style="float: right;">
            <a href="/web/control/joinRequestList?page=prev">Prev</a>&nbsp;
            <a href="/web/control/joinRequestList?page=next">Next</a>
        </div>
    </div>

<table cellpadding="3" class="table table-bordered table-striped ">


    <logic:notEmpty name="joinRequests">

        <tr class="tableheader">
            <th class="tableheader">First Name</th>
            <th class="tableheader">Last Name</th>
            <th class="tableheader">Date of Birth</th>
            <th class="tableheader">NHS Number</th>
            <th class="tableheader">Unit Code</th>
            <th class="tableheader">Email</th>
            <th class="tableheader">Date of Request</th>
            <th class="tableheader">Completed</th>
            <th class="tableheader">Notes</th>
            <th class="tableheader"></th>
        </tr>

        <c:forEach var="item" items="${joinRequests.pageList}">
            <tr>
                <td class="tablecell"><c:out value="${item.firstName}"/></td>
                <td class="tablecell"><c:out value="${item.lastName}"/></td>
                <td class="tablecell"><c:out value="${item.dateOfBirthFormatted}"/></td>
                <td class="tablecell"><c:out value="${item.nhsNo}"/></td>
                <td class="tablecell"><c:out value="${item.unitcode}"/></td>
                <td class="tablecell"><c:out value="${item.email}"/></td>
                <td class="tablecell"><c:out value="${item.dateOfRequestFormatted}"/></td>
                <td class="tablecell">
                    <logic:equal value="false" name="item" property="isComplete">
                        <big><font color="red">&#10008;</font></big>
                    </logic:equal>
                    <logic:equal value="true" name="item" property="isComplete">
                        <big><font color="green">&#10004;</font></big>
                    </logic:equal>
                </td>
                <td class="tablecell"><bean:write name="item" property="notes"/></td>
                <logic:present role="superadmin,unitadmin">
                    <td>
                        <form action="/web/control/joinRequestEditInput" method="post">
                            <html:hidden name="item" property="id"/>
                            <html:submit value="Edit" styleClass="btn" />
                        </form>
                    </td>
                </logic:present>
            </tr>
        </c:forEach>


    </logic:notEmpty>
</table>



    <div class="span10">
        <div class="row">
            <form action="/web/control/joinRequestList" method="post" id="displayForm">
                <input type="hidden" name="page" id="page" value=""/>
                <html:submit value="Show incomplete" styleId="incomplete" styleClass="btn" style="float:left;margin-left:5px;"/>
                <html:submit value="Show complete" styleClass="btn" styleId="complete" style="float:left;margin-left:5px;"/>
                <html:submit value="Show all" styleClass="btn" styleId="all" style="float:left;margin-left:5px;"/>
            </form>
        </div>
    </div>



</div>
</div>

<script src="/js/joinrequest.js" type="text/javascript"></script>

