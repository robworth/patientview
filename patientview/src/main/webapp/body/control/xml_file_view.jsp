<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">
    <div class="page-header">
        <h1>Patient Data XML File Contents</h1>
    </div>

    <logic:present name="xmlFilename">
      <p>File Name: <b><bean:write name="xmlFilename"/></b></p>
    </logic:present>

    <logic:notPresent name="xmlContent">
      That file does not exist.
      <br /><br />
      The contents of uploaded files are only held for 20 days, so it may already have been deleted.
    </logic:notPresent>

    <bean:define id="xmlContent" name="xmlContent" type="java.lang.String" />

    <logic:present name="xmlContent">
      <p>----START XML FILE----</p>

      <p><%= StringEscapeUtils.escapeHtml(xmlContent).replace("\n", "<br />")%></p>

        <p>-----END XML FILE-----</p>
    </logic:present>
</div>

