<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert definition="default.layout" flush="true" >

    <tiles:put name="body" value="/body/info_links.jsp" />
    <tiles:put name="left_nav" value="/common/left_nav_empty.jsp" />

</tiles:insert>