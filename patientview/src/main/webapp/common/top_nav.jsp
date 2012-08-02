<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<ul class="nav nav-pills">
    <li class="active first"><html:link action="/index" styleClass="<%= ("index".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Home</html:link></li>
    <li><html:link action="/patient/patient_details" styleClass="<%= ("patient_details".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My Details</html:link></li>
    <li><html:link action="/ibd/myibd" styleClass="<%= ("patient_view".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My IBD</html:link></li>
    <li><html:link action="/ibd/careplan" styleClass="<%= ("aboutme".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">My Care Plan</html:link></li>
    <li><html:link action="/patient/patient_entry" styleClass="<%= ("patient_entry".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Enter My Symptoms</html:link></li>
    <li><a href="#">Nutrition</a></li>
    <li><html:link action="/patient/results" styleClass="<%= ("results".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Results</html:link></li>
    <li><html:link action="/patient/medicines" styleClass="<%= ("medicines".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Medicines</html:link></li>
    <li><html:link action="/patient/letters" styleClass="<%= ("letters".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Letters</html:link></li>
    <li><html:link action="/patient/contact" styleClass="<%= ("contact".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Contact</html:link></li>
    <li><a href="#">Education</a></li>
</ul>
