<%@ page import="com.worthsoln.patientview.sharingthoughts.SharingThoughts" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p align="right"><html:link action="/patient/sharingThoughts"><< Return to Sharing Thoughts start page</html:link></p>

<div class="page-header">
  <logic:equal value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
     <h1>Positive comment</h1><br />
  </logic:equal>
  <logic:notEqual value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
     <h1>Quality or safety concern</h1><br />
  </logic:notEqual>

</div>

<table border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
<%--
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
--%>
        <tr >
            <th width="300">Question asked</th>
            <th>Response</th>
        </tr>
        
        <tr >
          <td width="300">Are you the patient on this Renal PatientView login?</td>
          <td>
              <logic:equal value="true" property="<%=SharingThoughts.IS_PATIENT%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                  I am the patient
              </logic:equal>
              <logic:equal value="false" property="<%=SharingThoughts.IS_PATIENT%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                  I am not the patient
              </logic:equal>
          </td>
        </tr>

        <tr >
            <td width="300">If no, which of these are you?<br />(You may tick more than one)</td><td>
                <logic:equal value="true" property="<%=SharingThoughts.IS_PRINCIPAL_CARER%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Principal carer
                </logic:equal>
                <logic:equal value="true" property="<%=SharingThoughts.IS_RELATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    <br />Relative
                </logic:equal>
                <logic:equal value="true" property="<%=SharingThoughts.IS_FRIEND%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    <br />Friend
                </logic:equal>
        </td>
        </tr>

        <tr >
            <td width="300">Who is this feedback form about?<br />(You may tick more than one)</td>
            <td>
                <logic:equal value="true" property="<%=SharingThoughts.IS_ABOUT_ME%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Me
                </logic:equal>
                <logic:equal value="true" property="<%=SharingThoughts.IS_ABOUT_OTHER%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    <br />Another patient
                </logic:equal>
            </td>
        </tr>

        <tr >
            <td width="300">Would you prefer to remain anonymous?</td>
            <td>
              <logic:equal value="true" property="<%=SharingThoughts.IS_ANONYMOUS%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                  Yes
              </logic:equal>
              <logic:equal value="false" property="<%=SharingThoughts.IS_ANONYMOUS%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                  No
              </logic:equal>
            </td>
        </tr>

        <tr >
            <td>Start date (dd-mm-yyyy)</td>
            <td><bean:write name="<%=SharingThoughts.THOUGHT_PARAM%>" property="<%=SharingThoughts.START_DATE_FORMATTED_DATE%>" /></td>
        </tr>

        <tr >
            <td>End date (dd-mm-yyyy)</td>
            <td><bean:write name="<%=SharingThoughts.THOUGHT_PARAM%>" property="<%=SharingThoughts.END_DATE_FORMATTED_DATE%>" /></td>
        </tr>

    <tr >
        <td width="300">Is this still ongoing?</td>
        <td>
            <logic:equal value="true" property="<%=SharingThoughts.IS_ONGOING%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                Yes
            </logic:equal>
            <logic:equal value="false" property="<%=SharingThoughts.IS_ONGOING%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                No
            </logic:equal>
        </td>
    </tr>

    <tr >
            <td>Where did this happen?</td><td><bean:write name="<%=SharingThoughts.THOUGHT_PARAM%>" property="<%=SharingThoughts.LOCATION%>"/></td>
        </tr>

        <tr >
            <logic:equal value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                <td width="300">Please describe what was good about the care that you or others have received</td>
            </logic:equal>
            <logic:notEqual value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                <td width="300">Please tell us what happened</td>
            </logic:notEqual>
            <td><bean:write property="<%=SharingThoughts.DESCRIPTION%>" name="<%=SharingThoughts.THOUGHT_PARAM%>"  /></td>
        </tr>

        <logic:notEmpty property="<%=SharingThoughts.CONCERN_REASON%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
        <tr >
            <td width="300">Why do you feel this was a concern for you?</td>
            <td><bean:write property="<%=SharingThoughts.CONCERN_REASON%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" /></td>
        </tr>
        </logic:notEmpty>

        <logic:notEqual value="0" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
        <tr>
          <td width="300">Do you think this happened before?</td>
            <td>
                <logic:equal value="1" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Definitely yes
                </logic:equal>
                <logic:equal value="2" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Probably yes
                </logic:equal>
                <logic:equal value="3" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Probably not
                </logic:equal>
                <logic:equal value="4" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Definitely not
                </logic:equal>
                <logic:equal value="5" property="<%=SharingThoughts.LIKELIHOOD_0F_RECURRENCE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                    Don't know
                </logic:equal>
          </td>
        </tr>
        </logic:notEqual>

        <logic:notEmpty property="<%=SharingThoughts.SUGGESTED_ACTION%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
        <tr >
            <logic:equal value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                <td width="300">What can be done to make sure that patients always receive this quality of care?</td>
            </logic:equal>
            <logic:notEqual value="1" property="<%=SharingThoughts.POSITIVE_NEGATIVE%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
                <td width="300">What do you think could be done to stop this from happening again to you or other patients?</td>
            </logic:notEqual>
            <td><bean:write property="<%=SharingThoughts.SUGGESTED_ACTION%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" /></td>
        </tr>
        </logic:notEmpty>

        <logic:notEqual value="0"  property="<%=SharingThoughts.HOW_SERIOUS%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" >
        <tr>
            <td width="300">On a scale of 1-5, how serious do you think your concern was?<br />1 = Less Serious, 5 = More Serious</td>
            <td>
                <bean:write property="<%=SharingThoughts.HOW_SERIOUS%>" name="<%=SharingThoughts.THOUGHT_PARAM%>" />
            </td>
        </tr>
        </logic:notEqual>

<%--
        <tr >
            <td width="300">&nbsp;</td>
            <td>
                <bean:write value="Save Draft" label="Save Draft" name="Save Draft"/> &nbsp;&nbsp;&nbsp;&nbsp;
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
    --%>
</table>
