<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<logic:notPresent name="specialtyUserRoles">
    <div class="alert alert-error">
        Sorry you do not have the correct permissions to login
    </div>
</logic:notPresent>

<logic:present name="specialtyUserRoles">

    <div class="page-header">
        <h1>Launch Pad <small>Select which speciality you want to view and manage</small></h1>
    </div>

    <ul class="thumbnails">
    <logic:iterate id="specialtyUserRole" name="specialtyUserRoles">
        <li class="span3">
            <div class="thumbnail">
                <a href="launchpad-select.do?specialtyId=<bean:write name="specialtyUserRole" property="specialty.id"/>" class="thumbnail"><div class="launchPadTextAlternative"><bean:write name="specialtyUserRole" property="specialty.name"/></div></a>
                <div class="caption">
                  <h5><bean:write name="specialtyUserRole" property="specialty.name"/> (<bean:write name="specialtyUserRole" property="role"/>)</h5>
                  <p><a href="launchpad-select.do?specialtyId=<bean:write name="specialtyUserRole" property="specialty.id"/>" class="btn">Enter</a></p>
                </div>
            </div>
        </li>            
    </logic:iterate>

</logic:present>



