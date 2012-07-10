<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Patient Data XML File Contents</p>

<logic:present name="xmlFilename">
  <p>File Name: <b><bean:write name="xmlFilename"/></b></p>
</logic:present>

<logic:notPresent name="xmlContent">
  That file does not exist.
  <br /><br />
  The contents of uploaded files are only held for 20 days, so it may already have been deleted.
</logic:notPresent>

<logic:present name="xmlContent">
  ----START XML FILE----<br />
  <bean:write name="xmlContent"/>
  <br />-----END XML FILE-----
</logic:present>

