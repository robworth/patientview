<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>

<p class="header">About Me</p>




    <html:errors/>

    <br />

    <html:form action="/patient/aboutmeEdit">
        <html:submit value="Try again" styleClass="formButton"/>
    </html:form>





