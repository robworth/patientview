<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p align="right"><html:link action="/patient/sharingThoughts"><< Return to Sharing Thoughts start page</html:link></p>


<div class="page-header">
    <h1>Type of comment</h1>
</div>


<ul>
    <li><p><font color="green">Do you want to share something <b>positive or good</b> about your care or the care of other patients?</font> &nbsp;&nbsp;&nbsp;&nbsp;<html:link action="/patient/sharingThoughtsPositive">Yes</html:link>
    </p><br /><br />
    <li><p><font color="red">Do you want to share a quality or safety <b>concern</b> about your care or the care of other patients?</font>&nbsp;&nbsp;&nbsp;&nbsp;<html:link action="/patient/sharingThoughtsNegative">Yes</html:link>
</ul>