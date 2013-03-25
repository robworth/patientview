<%@ page import="com.worthsoln.patientview.sharingthoughts.SharingThoughts" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Partially completed feedback</h1>
</div>

<table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

    <tr>
        <th>Date Updated</th>
        <th>Positive/Negative</th>
        <th>Date of Experience</th>
        <th>Location</th>
        <th>Description</th>
    </tr>

<logic:present name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
    <logic:notEmpty name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
        <logic:iterate id="thought" name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
            <tr>
                <td><bean:write name="thought" property="<%=SharingThoughts.DATE_LAST_SAVED_FORMATTED_DATE_TIME%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.DATE_OF_EXPERIENCE_FORMATTED_DATE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.LOCATION%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.DESCRIPTION_BEGINNING%>" /></td>
                <td><html:form action="/patient/sharingThoughtsEditThought"><html:hidden name="thought" property="id" /><html:submit value="Edit"/></html:form></td>
                <td><html:form action="/patient/sharingThoughtsDeleteThought"><html:hidden name="thought" property="id" /><html:submit value="Delete"/></html:form></td>
            </tr>
        </logic:iterate>
    </logic:notEmpty>
</logic:present>

</table>



<div class="page-header">
    <h1>Submitted feedback</h1>
</div>

<logic:present name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
    <logic:notEmpty name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
        <logic:iterate id="thought" name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
            <tr>
                <td><bean:write name="thought" property="<%=SharingThoughts.DATE_LAST_SAVED_FORMATTED_DATE_TIME%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.DATE_OF_EXPERIENCE_FORMATTED_DATE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.LOCATION%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.DESCRIPTION_BEGINNING%>" /></td>
                <td><html:form action="/patient/sharingThoughtsViewThought"><html:hidden name="thought" property="id" /><html:submit value="View"/></html:form></td>
            </tr>
        </logic:iterate>
    </logic:notEmpty>
</logic:present>

<p align="right"><html:link action="/patient/sharingThoughtsPositiveNegative">Add New Comment >></html:link></p>

<p align="right"><a href="summary_1a.html">Feedback Summary</a></p>

<p align="right"><a href="already_done_7.html">Action Taken</a></p>