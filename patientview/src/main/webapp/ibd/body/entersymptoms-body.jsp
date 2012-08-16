<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ page import="com.worthsoln.ibd.model.enums.Diagnosis" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Enter Symptoms</h1>
</div>

<logic:present role="patient">
    <ul class="thumbnails">

        <%
            Diagnosis loggedInUserDiagnosis = LegacySpringUtils.getIbdManager().getLoggedInUserDiagnosis();

            if (Diagnosis.COLITIS_UNSPECIFIED == loggedInUserDiagnosis ||
                    Diagnosis.ULCERATIVE_COLITIS == loggedInUserDiagnosis) {

        %>
        <li class="span3">
            <div class="thumbnail">
                <html:link action="/colitis-edit"><img src="http://placehold.it/260x180" alt=""></html:link>
                <div class="caption">
                    <h5>Enter your Colitis values</h5>

                    <p><html:link action="/colitis-edit" styleClass="btn">Enter</html:link></p>
                </div>
            </div>
        </li>
        <%
        } else if (Diagnosis.CROHNS == loggedInUserDiagnosis) {
        %>
        <li class="span3">
            <div class="thumbnail">
                <html:link action="/crohns-edit"><img src="http://placehold.it/260x180" alt=""></html:link>
                <div class="caption">
                    <h5>Enter your Crohns values</h5>

                    <p><html:link action="/crohns-edit" styleClass="btn">Enter</html:link></p>
                </div>
            </div>
        </li>
        <%
        } else {
        %>
        <li class="span3">
            <h5>You don't have any primary diagnosis. Please add one in My IBD section.</h5>
        </li>
        <%
            }
        %>


    </ul>
</logic:present>