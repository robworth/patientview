<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div align="center">

<table width="440">
    <tr>
        <td><p class="header">LaunchPad</p></td>
    </tr>
</table>

<br />

<logic:notPresent name="tenancyUserRoles">
    <table width="440" border="0" cellspacing="1" cellpadding="3">
        <tr valign="top">
            <td class="tableheader" colspan="2">Sorry you do not have the correct permissions to login</td>
        </tr>
    </table>
</logic:notPresent>

<logic:present name="tenancyUserRoles">

    <table width="440" border="0" cellspacing="1" cellpadding="3">

        <tr valign="top">
            <td class="tableheader" colspan="2">Available Specialities, please select one:</td>
        </tr>

    <logic:iterate id="tenancyUserRole" name="tenancyUserRoles">
        <tr valign="top">
            <td class="tablecellbold">Speciality</td>

            <td class="tablecell">
                <a href="launchpad-select.do?tenancyId=<bean:write name="tenancyUserRole" property="tenancy.id"/>"><bean:write name="tenancyUserRole" property="tenancy.name"/> (<bean:write name="tenancyUserRole" property="role"/>)</a>
            </td>
        </tr>
    </logic:iterate>

    </table>
    <br /><br />

</logic:present>

</div>


