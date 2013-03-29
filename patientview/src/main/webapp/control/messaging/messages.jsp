<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="control.layout" flush="true" >

    <tiles:put name="body" value="/body/messaging/messages_body.jsp?actionPrefix=control" />

</tiles:insert>