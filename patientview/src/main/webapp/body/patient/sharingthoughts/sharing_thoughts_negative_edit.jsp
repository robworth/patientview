<%@ page import="com.worthsoln.patientview.sharingthoughts.SharingThoughts" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p align="right"><html:link action="/patient/sharingThoughts"><< Return to Sharing Thoughts start page</html:link></p>

<div class="page-header">
    <h1>Quality or safety concern</h1><br />
</div>

<table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

    <html:form action="/patient/sharingThoughtSave">

        <html:hidden name="<%=SharingThoughts.THOUGHT_PARAM%>" property="<%=SharingThoughts.ID%>" />
        <html:hidden property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" value="-1" />

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
            Yes: <html:radio property="<%=SharingThoughts.IS_PATIENT%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="true" />&nbsp;&nbsp;&nbsp;&nbsp;
            No: <html:radio property="<%=SharingThoughts.IS_PATIENT%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="false" />&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        </tr>

        <tr >
            <td width="300">If no, which of these are you?<br />(You may tick more than one)</td><td>
            Principal carer: <html:checkbox property="<%=SharingThoughts.IS_PRINCIPAL_CARER%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" />&nbsp;&nbsp;&nbsp;&nbsp;
            Relative: <html:checkbox property="<%=SharingThoughts.IS_RELATIVE%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" />&nbsp;&nbsp;&nbsp;&nbsp;
            Friend: <html:checkbox property="<%=SharingThoughts.IS_FRIEND%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" />&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        </tr>

        <tr >
            <td width="300">Who is this feedback form about?<br />(You may tick more than one)</td><td>
            Me: <html:checkbox property="<%=SharingThoughts.IS_ABOUT_ME%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
            Another patient: <html:checkbox property="<%=SharingThoughts.IS_ABOUT_OTHER%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        </tr>

        <tr >
            <td width="300">Would you prefer to remain anonymous?</td><td>
            Yes: <html:radio property="<%=SharingThoughts.IS_ANONYMOUS%>" value="true"  name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
            No: <html:radio property="<%=SharingThoughts.IS_ANONYMOUS%>" value="false"  name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        </tr>

        <bean:define id="startDate" name="<%=SharingThoughts.THOUGHT_PARAM%>" property="startDateExperienceFormattedDate" />
        <tr >
            <td>Start date (dd-mm-yyyy)</td>
            <td><input type="text" name="<%=SharingThoughts.START_DATE%>" value="<bean:write name='<%=SharingThoughts.THOUGHT_PARAM%>' property='<%=SharingThoughts.START_DATE_FORMATTED_DATE%>' />" size="50"  /></td>
        </tr>

        <bean:define id="endDate" name="<%=SharingThoughts.THOUGHT_PARAM%>" property="endDateExperienceFormattedDate" />
        <tr >
            <td>End date (dd-mm-yyyy)</td>
            <td><input type="text" name="<%=SharingThoughts.END_DATE%>" value="<bean:write name='<%=SharingThoughts.THOUGHT_PARAM%>' property='<%=SharingThoughts.END_DATE_FORMATTED_DATE%>' />" size="50"  /></td>
        </tr>

        <tr >
          <td width="300">Is this still going on?</td><td>
            Yes: <html:radio property="<%=SharingThoughts.IS_ONGOING%>" value="true" name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
            No: <html:radio property="<%=SharingThoughts.IS_ONGOING%>" value="false" name="<%=SharingThoughts.THOUGHT_PARAM%>"/>&nbsp;&nbsp;&nbsp;&nbsp;
          </td>
        </tr>

        <tr >
            <td>Where did this happen?</td><td><html:text name="<%=SharingThoughts.THOUGHT_PARAM%>" property="<%=SharingThoughts.LOCATION%>"/></td>
        </tr>

        <tr >
            <td width="300">Please tell us what happened</td>
            <td><html:textarea property="<%=SharingThoughts.DESCRIPTION%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" rows="10" cols="500" /></td>
        </tr>

        <tr >
            <td width="300">Why do you feel this was a concern for you?</td>
            <td><html:textarea property="<%=SharingThoughts.CONCERN_REASON%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" rows="10" cols="500" /></td>
        </tr>

        <tr>
          <td width="300">Do you think this happened before?</td><td>
            Definitely yes: <html:radio property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" value="1" />&nbsp;&nbsp;&nbsp;&nbsp;
            Probably yes: <html:radio property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" value="2" />&nbsp;&nbsp;&nbsp;&nbsp;
            Probably not:  <html:radio property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" value="3" />&nbsp;&nbsp;&nbsp;&nbsp;
            Definitely not: <html:radio property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" value="4" /><br /><br />
            Don't know: <html:radio property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" value="5" />&nbsp;&nbsp;&nbsp;&nbsp;
          </td>
        </tr>

        <tr >
            <td width="300">What do you think could be done to stop this from happening again to you or other patients?</td>
            <td><html:textarea property="<%=SharingThoughts.SUGGESTED_ACTION%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" rows="10" cols="500" /></td>
        </tr>

        <tr>
            <td width="300">On a scale of 1-5, how serious do you think your concern was?</td><td>
            Less serious&nbsp;&nbsp;1:<html:radio property="<%=SharingThoughts.HOW_SERIOUS%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="1" />&nbsp;&nbsp;
            2: <html:radio property="<%=SharingThoughts.HOW_SERIOUS%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="2" />&nbsp;&nbsp;
            3:  <html:radio property="<%=SharingThoughts.HOW_SERIOUS%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="3" />&nbsp;&nbsp;
            4: <html:radio property="<%=SharingThoughts.HOW_SERIOUS%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="4" />&nbsp;&nbsp;
            5: <html:radio property="<%=SharingThoughts.HOW_SERIOUS%>"  name="<%=SharingThoughts.THOUGHT_PARAM%>" value="5" />&nbsp;&nbsp;More serious
        </td>
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
            <html:form action="/patient/sharingThoughtsNegative"><input type="submit" value="Clear Form"/></html:form>
        </td>
    </tr>

</table>
