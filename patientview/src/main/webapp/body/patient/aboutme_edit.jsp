<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>About Me</h1>
        </div>



<html:form action="/patient/aboutmeUpdate">

    <logic:notEmpty name="aboutme">
        <html:hidden name="aboutme" property="id"/>
        <html:hidden name="aboutme" property="nhsno"/>
    </logic:notEmpty>

    <label>
        <b>Things people should know about me</b><br />(For example...)
    </label>

    <logic:empty name="aboutme">
        <html:textarea property="aboutme" rows="5" cols="40" styleClass="span6"/>
    </logic:empty>

    <logic:notEmpty name="aboutme">
        <html:textarea name="aboutme" property="aboutme" rows="5" cols="40"  styleClass="span6"/>
    </logic:notEmpty>

    <label>
    <b>Things I'd like to talk about</b><br />
                (For example... what should my blood pressure be, home dialysis, stopping dialysis, getting a transplant)</p>
    </label>

    <logic:empty name="aboutme">
        <html:textarea property="talkabout" rows="5" cols="80"  styleClass="span6"/>
    </logic:empty>

    <logic:notEmpty name="aboutme">
        <html:textarea name="aboutme" property="talkabout" rows="5" cols="40"  styleClass="span6"/>
    </logic:notEmpty>


    <div class="form-actions">
         <html:submit value="Update" styleClass="btn btn-primary"/>
    </div>

</html:form>

<%--
    <tr>
        <td >
            <p><b>Upload an Image</b><br />
                (jpeg, gif or tiff and no larger than 1Mb)</p>
        </td>
    </tr>

    <html:form action="/patient/aboutmeImageUpload" method="post" enctype="multipart/form-data">
    <tr>
        <html:hidden name="user" property="username"  />
        <td align="left">
             <html:file property="aboutmeImageFile"/>
</td>
        </tr>
        <tr>
        <td>
             <html:submit styleClass="formButton">Upload Image</html:submit>
        </td>
    </tr>
</html:form>
--%>
</div>
</div>


