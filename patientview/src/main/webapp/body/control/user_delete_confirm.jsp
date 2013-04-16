<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>
<div class="span9">
    <div class="page-header">
        <h1 class="header">User Deleted</h1>
    </div>

    <p>On <dt:format pattern="d MMM yyyy"><dt:currentTime/></dt:format> you successfully deleted the user with the following details:</p>

    <table cellpadding="3" >
        <tr>
            <td><b>User Name</b></td>
            <td><bean:write name="patient" property="username" /></td>
        </tr>
        <tr>
            <td><b>Name</b></td>
            <td><bean:write name="patient" property="name" /></td>
        </tr>
    </table>

    <p>From the unit called <strong><bean:write name="unit" property="shortname"/></strong></p>
</div>
</div>