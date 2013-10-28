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
<div class="span10">
<div class="page-header">

    <h1>To Do List
    <c:if test="${inCompletedNumber != null}">
        <span class="badge badge-important numberNote"><c:out value="${inCompletedNumber}"/></span>
    </c:if>
    </h1>

</div>

    <div class="span10">
        <div class="row">
            <form action="/<c:out value="${specialty}"/>/web/control/joinRequestList" method="post" id="displayFormTop">
                <input type="hidden" name="page" id="page" value=""/>
                <html:submit value="Show incomplete" styleId="incompleteTop" styleClass="btn" style="float:left;margin-left:5px;"/>
                <html:submit value="Show complete" styleClass="btn" styleId="completeTop" style="float:left;margin-left:5px;"/>
                <html:submit value="Show all" styleClass="btn" styleId="allTop" style="float:left;margin-left:5px;"/>
            </form>

        </div>
    </div>

    <div class="span10" style="margin-left: 0px;margin-bottom:5px;">
        <div class="row" style="float: right;">

            <c:choose>
                <c:when test="${firstPage}">
                    &nbsp;
                </c:when>
                <c:otherwise>
                    <a href="/<c:out value="${specialty}"/>/web/control/joinRequestList?page=prev">Prev</a>&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${lastPage}">
                    &nbsp;
                </c:when>
                <c:otherwise>
                    <a href="/<c:out value="${specialty}"/>/web/control/joinRequestList?page=next">Next</a>&nbsp;
                </c:otherwise>
            </c:choose>

        </div>
    </div>


<table cellpadding="3" class="table table-bordered table-striped ">


    <logic:notEmpty name="joinRequests">

        <tr class="tableheader">
            <th class="tableheader"><a href="#" onclick="sort('firstName')">First Name</a></th>
            <th class="tableheader"><a href="#" onclick="sort('lastName')">Last Name</a></th>
            <th class="tableheader"><a href="#" onclick="sort('dateOfBirth')">Date of Birth</a></th>
            <th class="tableheader"><a href="#" onclick="sort('nhsNo')">NHS Number</a></th>
            <th class="tableheader"><a href="#" onclick="sort('unitcode')">Unit Code</a></th>
            <th class="tableheader"><a href="#" onclick="sort('email')">Email</a></th>
            <th class="tableheader"><a href="#" onclick="sort('dateOfRequest')">Date of Request</a></th>
            <th class="tableheader"><a href="#" onclick="sort('taskTitle')">Task</a></th>
            <th class="tableheader"><a href="#" onclick="sort('isComplete')">Completed</a></th>
            <th class="tableheader">Notes</th>
            <th class="tableheader" style="width: 50px;"></th>
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
                <td class="tablecell"><c:out value="${item.taskTitle}"/></td>
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
                        <form action="/<c:out value="${specialty}"/>/web/control/joinRequestEditInput" method="post">
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
            <form action="/<c:out value="${specialty}"/>/web/control/joinRequestList" method="post" id="displayFormBottom">
                <input type="hidden" name="page" id="page" value=""/>
                <html:submit value="Show incomplete" styleId="incompleteBottom" styleClass="btn" style="float:left;margin-left:5px;"/>
                <html:submit value="Show complete" styleClass="btn" styleId="completeBottom" style="float:left;margin-left:5px;"/>
                <html:submit value="Show all" styleClass="btn" styleId="allBottom" style="float:left;margin-left:5px;"/>
            </form>
        </div>
    </div>



</div>
</div>

<script src="/js/joinrequest.js" type="text/javascript"></script>

