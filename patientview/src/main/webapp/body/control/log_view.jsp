<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="600" border="0" cellspacing="1" cellpadding="3">

  <tr height="20">
    <td colspan="10">You must enter a start and an end date and at least one other search criteria. Dates in format
      dd/mm/yyyy
    </td>
  </tr>

  <html:form action="/control/logView">

    <tr>
      <td><b>Start Date</b></td>
      <td><html:text property="startdate"/></td>
      <td><b>End Date</b></td>
      <td><html:text property="enddate"/></td>
    </tr>

    <tr>
      <td><b>NHS No</b></td>
      <td><html:text property="nhsno"/></td>
      <td><b>User</b></td>
      <td><html:text property="user"/></td>
    </tr>

    <tr>
      <td><b>Actor</b></td>
      <td><html:text property="actor"/></td>
      <td><b>Action</b></td>
      <td>
        <html:select property="action">
          <html:option value=""/>
          <html:option value="password reset"/>
          <html:option value="password reset forgotten"/>
          <html:option value="password change"/>
          <html:option value="patient data load"/>
          <html:option value="patient data fail"/>
          <html:option value="patient data remove"/>
          <html:option value="logon"/>
          <html:option value="patient add"/>
          <html:option value="patient delete"/>
          <html:option value="patient view"/>
          <html:option value="admin add"/>
          <html:option value="ukt data"/>
          <html:option value="password locked"/>
          <html:option value="password unlocked"/>
          <html:option value="email verified"/>
        </html:select>
      </td>
    </tr>

    <tr>
      <td><b>Unit</b></td>
      <td colspan="3"><html:select property="unitcode">
        <logic:present role="superadmin">
          <html:option value="">-- All units --</html:option>
        </logic:present>
        <html:options collection="units" property="unitcode" labelProperty="unitNamePlusCode"/>
      </html:select></td>
    </tr>

    <tr align="left">
      <td>&nbsp;</td>
      <td><html:submit value="Search" styleClass="formbutton"/></td>
    </tr>

  </html:form>
</table>


<table width="600" border="0" cellspacing="1" cellpadding="1">
  <tr valign="top">
    <td colspan="10"><img src="images/space.gif"/></td>
  </tr>

  <logic:empty name="log">
    <tr valign="top">
      <td class="tableheader">No log entries found.</td>
    </tr>
  </logic:empty>

  <logic:notEmpty name="log">

    <tr>
      <td class="tablecellbold">Date and time</td>
      <td class="tablecellbold">NHS No</td>
      <td class="tablecellbold">User</td>
      <td class="tablecellbold">Action</td>
      <td class="tablecellbold">Actor</td>
      <td class="tablecellbold">Unit</td>
      <td class="tablecellbold">Extra Info</td>
    </tr>

    <logic:iterate name="log" id="logentry">
      <tr>
        <td class="tablecell"><b><bean:write name="logentry" property="formattedDate"/></b></td>
        <td class="tablecell"><bean:write name="logentry" property="nhsno"/></td>
        <td class="tablecell"><bean:write name="logentry" property="user"/></td>
        <td class="tablecell"><bean:write name="logentry" property="action"/></td>
        <td class="tablecell"><bean:write name="logentry" property="actor"/></td>
        <td class="tablecell"><bean:write name="logentry" property="unitcode"/></td>
        <logic:notEqual value="patient data load" name="logentry" property="action" >
          <logic:notEqual value="patient data fail" name="logentry" property="action">
            <td class="tablecell"><bean:write name="logentry" property="extrainfo"/></td>
          </logic:notEqual>
        </logic:notEqual>
        <logic:equal value="patient data load" name="logentry" property="action">
          <td class="tablecell"><html:link action="/control/xmlFileView" paramId="xmlfile" paramName="logentry" paramProperty="xmlfilename"><bean:write name="logentry" property="extrainfo"/></html:link></td>
        </logic:equal>
        <logic:equal value="patient data fail" name="logentry" property="action">
          <td class="tablecell"><html:link action="/control/xmlFileView" paramId="xmlfile" paramName="logentry" paramProperty="xmlfilename"><bean:write name="logentry" property="xmlfilename"/></html:link><bean:write name="logentry" property="extrainfolessxmlfilename"/></td>
        </logic:equal>
      </tr>
    </logic:iterate>

  </logic:notEmpty>

</table>

