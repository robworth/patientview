<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="default.layout" flush="true" >

    <tiles:put name="body" value="/body/messaging/message_detail_body.jsp?actionPrefix=patient" />

</tiles:insert>