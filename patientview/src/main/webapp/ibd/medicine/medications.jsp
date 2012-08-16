<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="patient.layout" flush="true" >

    <tiles:put name="body" value="/ibd/medicine/body/medications-body.jsp" />

</tiles:insert>