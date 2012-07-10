<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<html:form action="/control/decrypt">

  <html:text property="src" /><br />
  <html:text property="dest" /><br />
  <html:text property="passphrase" /><br />
  <html:text property="gpgHome" /><br />
  <html:text property="gpgRuntime" /><br />
  <html:submit/><br />

</html:form>