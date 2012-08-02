<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<logic:notPresent name="tenancyUserRoles">
    <div class="alert alert-error">
        Sorry you do not have the correct permissions to login
    </div>
</logic:notPresent>

<logic:present name="tenancyUserRoles">

    <div class="page-header">
        <h1>Launch Pad <small>Select the speciality you wish to view in more detail</small></h1>
    </div>

    <ul class="thumbnails">
    <logic:iterate id="tenancyUserRole" name="tenancyUserRoles">
        <li class="span3">
            <div class="thumbnail">
                <a href="launchpad-select.do?tenancyId=<bean:write name="tenancyUserRole" property="tenancy.id"/>" class="thimbnail"><img src="http://placehold.it/260x180" alt=""></a>
                <div class="caption">
                  <h5><bean:write name="tenancyUserRole" property="tenancy.name"/> (<bean:write name="tenancyUserRole" property="role"/>)</h5>
                  <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                  <p><a href="launchpad-select.do?tenancyId=<bean:write name="tenancyUserRole" property="tenancy.id"/>" class="btn">Enter</a></p>
                </div>
            </div>
        </li>            
    </logic:iterate>
    </ul>

</logic:present>



