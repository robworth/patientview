<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<p class="header">About Me</p>


<logic:present name="aboutme">
<logic:notEmpty name="aboutme">
        <jsp:useBean id="aboutme" class="com.worthsoln.patientview.model.Aboutme" scope="request"/>
    
    <img src="aboutmeimage/<%= aboutme.getNhsno() %>" alt="" width="200">
       <br /><br />
    
    
<table cellpadding="3" >


    <tr>
        <td align="center">
            <p><b>Things people should know about me</b></p>
        </td>
    </tr>
    <tr>
        <td style="white-space: pre-line;">
                <bean:write name="aboutme" property="aboutme" />
        </td>
    </tr>
    <tr>
        <td >&nbsp;</td>
    </tr>

    <tr>
        <td >
            <p><b>Things I'd like to talk about</b></p>
        </td>
    </tr>
    <tr>
        <td style="white-space: pre-line;">
           <bean:write name="aboutme" property="talkabout" />
        </td>
    </tr>
    <tr>
        <td >&nbsp;</td>
    </tr>
    <tr>
        <td ><html:form action="/patient/aboutmeEdit">
        <html:submit value="Edit" styleClass="formButton"/>
        </html:form></td>

        <td><form><input type="button" class="formButton" value=" Print this page "
onclick="window.print();return false;" /></form> </td>
    </tr>

</table>

</logic:notEmpty>
</logic:present>


