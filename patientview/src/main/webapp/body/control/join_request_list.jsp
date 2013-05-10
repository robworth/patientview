<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Join Requests</h1>
</div>

<table cellpadding="3" class="table table-bordered table-striped">


    <logic:notEmpty name="joinRequests">

        <tr class="tableheader">
            <th class="tableheader">First Name</th>
            <th class="tableheader">Last Name</th>
            <th class="tableheader">Date of Birth</th>
            <th class="tableheader">NHS Number</th>
            <th class="tableheader">Unit Code</th>
            <th class="tableheader">Email</th>
            <th class="tableheader">Date of Request</th>
        </tr>

        <logic:iterate id="joinrequest" name="joinRequests" >
            <tr>
                <td class="tablecell"><bean:write name="joinrequest" property="firstName" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="lastName" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="dateOfBirthFormatted" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="nhsNo" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="unitcode" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="email" /></td>
                <td class="tablecell"><bean:write name="joinrequest" property="dateOfRequestFormatted" /></td>
            </tr>
        </logic:iterate>

    </logic:notEmpty>
</table>


</div>
</div>

