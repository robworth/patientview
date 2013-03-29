<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="default.layout" flush="true" >

    <tiles:put name="body" value="/body/messaging/messages_body.jsp?actionPrefix=patient" />

</tiles:insert>