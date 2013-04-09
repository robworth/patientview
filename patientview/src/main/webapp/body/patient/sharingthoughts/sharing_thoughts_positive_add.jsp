<%@ page import="com.worthsoln.patientview.sharingthoughts.SharingThoughts" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p align="right"><html:link action="/patient/sharingThoughts"><< Return to Sharing Thoughts start page</html:link></p>

<div class="page-header">
    <h1>Positive comment</h1><br />
</div>

<table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

  <html:form action="/patient/sharingThoughtSave">

    <html:hidden property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" value="1" />


    <logic:present name="<%=SharingThoughts.ERRORS_PARAM%>">
        <logic:notEmpty name="<%=SharingThoughts.ERRORS_PARAM%>">
            <p><h4>Please correct the following in your submission:</h4></p>
            <logic:iterate id="error" name="<%=SharingThoughts.ERRORS_PARAM%>">
                <p><font color="red"><bean:write name="error" /></font></p>
            </logic:iterate>
        </logic:notEmpty>
    </logic:present>

    <tr >
      <td width="300">Are you the patient on this Renal PatientView login?</td><td>
        Yes: <html:radio property="<%=SharingThoughts.IS_PATIENT%>" name="sharingThoughtsForm" value="true" />&nbsp;&nbsp;&nbsp;&nbsp;
        No: <html:radio property="<%=SharingThoughts.IS_PATIENT%>" name="sharingThoughtsForm" value="false" />&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>

    <tr >
      <td width="300">If no, which of these are you?<br />(You may tick more than one)</td><td>
         Principal carer: <html:checkbox property="<%=SharingThoughts.IS_PRINCIPAL_CARER%>" name="sharingThoughtsForm" />&nbsp;&nbsp;&nbsp;&nbsp;
         Relative: <html:checkbox property="<%=SharingThoughts.IS_RELATIVE%>" name="sharingThoughtsForm" />&nbsp;&nbsp;&nbsp;&nbsp;
         Friend: <html:checkbox property="<%=SharingThoughts.IS_FRIEND%>" name="sharingThoughtsForm" />&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>

    <tr >
      <td width="300">Who is this feedback form about?<br />(You may tick more than one)</td><td>
        Me: <html:checkbox property="<%=SharingThoughts.IS_ABOUT_ME%>" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
        Another patient: <html:checkbox property="<%=SharingThoughts.IS_ABOUT_OTHER%>" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>

    <tr >
      <td width="300">Would you prefer to remain anonymous?</td><td>
         Yes: <html:radio property="<%=SharingThoughts.IS_ANONYMOUS%>" value="true" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
          No: <html:radio property="<%=SharingThoughts.IS_ANONYMOUS%>" value="false" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>

    <tr >
      <td>Start date (dd/mm/yyyy)</td>
      <td><html:text name="sharingThoughtsForm" property="<%=SharingThoughts.START_DATE%>" size="50"/></td>
    </tr>

    <tr >
      <td>End date (dd/mm/yyyy)</td>
      <td><html:text name="sharingThoughtsForm" property="<%=SharingThoughts.END_DATE%>" size="50"/></td>
    </tr>

    <tr >
      <td width="300">Is this still going on?</td><td>
          Yes: <html:radio property="<%=SharingThoughts.IS_ONGOING%>" value="true" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
          No: <html:radio property="<%=SharingThoughts.IS_ONGOING%>" value="false" name="sharingThoughtsForm"/>&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>

    <tr >
      <td>Where did this happen?</td><td><html:text name="sharingThoughtsForm" property="<%=SharingThoughts.LOCATION%>"/></td>
    </tr>

    <tr >
      <td>Which unit does this relate to?</td><td><html:select property="unitId"><html:options collection="units" property="id" labelProperty="name"/></html:select></td>
    </tr>

    <tr >
      <td width="300">Please describe what was good about the care that you or others have received</td>
      <td><html:textarea property="<%=SharingThoughts.DESCRIPTION%>" name="sharingThoughtsForm" rows="10" cols="50" /></td>
    </tr>

    <tr >
      <td width="300">What can be done to make sure that patients always receive this quality of care?</td>
      <td><html:textarea property="<%=SharingThoughts.SUGGESTED_ACTION%>" name="sharingThoughtsForm" rows="10" cols="50" /></td>
    </tr>

    <tr >
      <td width="300">&nbsp;</td>
      <td>
        <input type="submit" value="Save Draft" label="Save Draft" name="Save Draft"/> &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="<%=SharingThoughts.SUBMIT%>" name="<%=SharingThoughts.SUBMIT%>"/>
      </td>
    </tr>

  </html:form>

    <tr >
      <td width="300">&nbsp;</td>
      <td>
        <html:form action="/patient/sharingThoughtsPositive"><input type="submit" value="Clear Form"/></html:form>
      </td>
    </tr>

</table>
