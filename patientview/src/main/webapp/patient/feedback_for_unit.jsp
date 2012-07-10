<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="patient.layout" flush="true">

  <tiles:put name="body" value="/body/patient/feedback_for_unit.jsp"/>

</tiles:insert>