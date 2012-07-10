<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<html:form action="/control/decrypt">

  <html:text property="src" />
  <html:text property="dest" />
  <html:text property="passphrase" />


</html:form>