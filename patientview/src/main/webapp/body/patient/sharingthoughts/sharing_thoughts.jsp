<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Sharing thoughts about my care</h1><br /><h3>The renal patient anonymous quality and safety feedback system</h3>
</div>

<ul>
    <li><p>This part of RPV is being developed and tested as part of a three year research project, funded by the National Institute for Health Research (NIHR). It has been designed to help you share any positive thoughts and/or concerns you may have about the quality and safety of your care or the care of other patients within the NHS.  It has been developed with kidney patients and has the support of your staff.
    </p>
    <li><p><b>If you believe that your quality and safety concern requires urgent action or may be life-threatening, <b><u>YOU MUST TELL A STAFF MEMBER</u></b>.</p>
        <p>This is a research project and we cannot manage immediate safety concerns. However, you can tell us about urgent concerns as well <u>but please tell us after</u> you have told the staff.</b>
        </p>

    <li><p>We will share the information you give us with the staff who care for you, but you must be aware that not all concerns will be solved quickly.
    </p>
</ul>

<br />

<p align="center"><html:link action="/patient/sharingThoughtsPositiveNegative"><html:submit value="Share a thought" styleClass="btn btn-primary formbutton" /></html:link></p>

<br />

<p align="center">
    <html:link action="/patient/sharingThoughtsUserList"><html:submit value="Your saved comments" styleClass="btn formbutton" /></html:link>
    &nbsp;&nbsp;&nbsp;
    <html:link action="/patient/sharingThoughtsUserList"><html:submit value="Monthly feedback summary" styleClass="btn formbutton" /></html:link>
</p>