<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>


<p class="header"><bean:write name="splashPage" property="headline"/></p>

<p style="white-space: pre-wrap;"><bean:write filter="false" name="splashPage" property="bodytext"/></p>      