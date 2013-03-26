<%@ page import="com.worthsoln.patientview.sharingthoughts.SharingThoughts" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Partially completed feedback</h1>
</div>



<logic:present name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
    <logic:notEmpty name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
        <table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
            <tr>
                <th>Date Updated</th>
                <th>Positive/Negative</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Location</th>
                <th>Description</th>
            </tr>
            <logic:iterate id="thought" name="<%=SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM%>">
                <tr>
                    <td><bean:write name="thought" property="<%=SharingThoughts.DATE_LAST_SAVED_FORMATTED_DATE_TIME%>" /></td>
                    <td>
                        <logic:equal value="1" name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>">
                            Positive
                        </logic:equal>
                        <logic:notEqual value="1" name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>">
                            Negative
                        </logic:notEqual>
                    </td>
                    <td><bean:write name="thought" property="<%=SharingThoughts.START_DATE_FORMATTED_DATE%>" /></td>
                    <td><bean:write name="thought" property="<%=SharingThoughts.END_DATE_FORMATTED_DATE%>" /></td>
                    <td><bean:write name="thought" property="<%=SharingThoughts.LOCATION%>" /></td>
                    <td><bean:write name="thought" property="<%=SharingThoughts.DESCRIPTION_BEGINNING%>" /></td>
                    <td><html:form action="/patient/sharingThoughtsEditThought"><html:hidden name="thought" property="id" /><html:submit value="Edit"/></html:form></td>
                    <td><html:form action="/patient/sharingThoughtsDeleteThought"><html:hidden name="thought" property="id" /><html:submit value="Delete"/></html:form></td>
                </tr>
            </logic:iterate>
        </table>
    </logic:notEmpty>
</logic:present>


<br />


<div class="page-header">
    <h1>Submitted feedback</h1>
</div>

<logic:present name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
    <logic:notEmpty name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
        <table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
        <tr>
            <th>Date Updated</th>
            <th>Positive/Negative</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Location</th>
            <th>Description</th>
        </tr>
        <logic:iterate id="thought" name="<%=SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM%>">
            <tr>
                <td><bean:write name="thought" property="<%=SharingThoughts.DATE_LAST_SAVED_FORMATTED_DATE_TIME%>" /></td>
                <td>
                    <logic:equal value="1" name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>">
                        Positive
                    </logic:equal>
                    <logic:notEqual value="1" name="thought" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>">
                        Negative
                    </logic:notEqual>
                </td>
                <td><bean:write name="thought" property="<%=SharingThoughts.START_DATE_FORMATTED_DATE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.END_DATE_FORMATTED_DATE%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.LOCATION%>" /></td>
                <td><bean:write name="thought" property="<%=SharingThoughts.DESCRIPTION_BEGINNING%>" /></td>
                <td><html:form action="/patient/sharingThoughtsViewThought"><html:hidden name="thought" property="id" /><html:submit value="View"/></html:form></td>
            </tr>
        </logic:iterate>
        </table>
    </logic:notEmpty>
</logic:present>

<p align="right"><html:link action="/patient/sharingThoughtsPositiveNegative">Add New Comment >></html:link></p>

<p align="right"><a href="summary_1a.html">Feedback Summary</a></p>

<p align="right"><a href="already_done_7.html">Action Taken</a></p>