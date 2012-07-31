<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:xhtml/>

<%
    if (LegacySpringUtils.getSecurityUserManager().isLoggedIn()) {
%>

<p class="header">Welcome to Renal PatientView</p>

<p>Renal PatientView is a project of <a href="http://www.renal.org/rixg">RIXG</a> a UK group representing renal patients and the renal team.  It aims to provide online information about renal patients' diagnosis, treatment, and their latest test results.  Patients can share this information with anyone they want, and view it from anywhere in the world.</p>

<p>PatientView is only available from some UK renal units, and for patients who have chosen to participate.  Here is a <a href="http://www.renal.org/rixg/units.html">list of units</a> involved so far.  The information comes directly from existing databases within units, so if you suspect a mistake, you should check with your own unit.  Links are provided after you log in.</p>

<p>You can view our <html:link action="/infoLinks">information links</html:link> without logging in.</p>

<p>You can view a <html:link action="/demo">demo</html:link> as if you were a patient.</p>

<p>RPV is funded by contributions from renal units in England and Wales and by the Scottish Government in Scotland. Development funding has come from the Department of Health in England, Scotland and Wales, and from NHS Kidney Care.</p>
<%
    } else {
%>
<p class="header">Welcome to PatientView</p>

<p>Some generic text describing the multi-speciality product</p>
<%
    }
%>