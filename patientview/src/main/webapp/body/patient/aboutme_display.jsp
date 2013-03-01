<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<div class="page-header"><h1>About Me</h1></div>

<div class="row">
<logic:present name="aboutme">
<logic:notEmpty name="aboutme">
        <jsp:useBean id="aboutme" class="com.worthsoln.patientview.model.Aboutme" scope="request"/>
    <div class="span3">
        <img src="aboutmeimage/<%= aboutme.getNhsno() %>" alt="" width="200">
    </div>
    
<div class="span9">
        <h2>Things people should know about me</h2>
        <p><bean:write name="aboutme" property="aboutme" /></p>
        <h2>Things I'd like to talk about</h2>
        <p><bean:write name="aboutme" property="talkabout" /></p>
        <html:form action="/patient/aboutmeEdit">
            <div class="form-actions">
                <html:submit value="Edit" styleClass="btn btn-primary"/>
                <input type="button" class="btn" value=" Print this page " onclick="window.print();return false;" />
            </div>
        </html:form>
</div>

</logic:notEmpty>
</logic:present>
</div>


