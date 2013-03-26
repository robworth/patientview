<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p align="right"><html:link action="/patient/sharingThoughts"><< Return to Sharing Thoughts start page</html:link></p>


<div class="page-header">
    <h1>Type of comment</h1>
</div>

<br />

<p align="center">
    <html:link action="/patient/sharingThoughtsPositive"><html:submit value="Share positive comment" styleClass="btn btn-success formbutton" /></html:link>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <html:link action="/patient/sharingThoughtsNegative"><html:submit value="Share quality or safety concern" styleClass="btn btn-danger formbutton" /></html:link>
</p>