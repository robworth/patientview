<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
    <div class="page-header">
        <h1>Links Editing</h1>
    </div>

    <html:errors/>

    <html:form action="/control/careplan-links-update" styleClass="form-horizontal">
        <html:hidden property="linkType" name="edtaCode"/>
        <fieldset>
            <div class="control-group">
                <label class="control-label">Code</label>

                <div class="controls"><bean:write name="edtaCode" property="edtaCode"/></div>
                <html:hidden name="edtaCode" property="edtaCode"/>
            </div>
            <div class="control-group">
                <label class="control-label">Description</label>

                <div class="controls"><html:text name="edtaCode" property="description"/></div>
            </div>
        </fieldset>

        <table cellpadding="3" class="table table-bordered table-striped">
            <tr>
                <td></td>
                <td align="center"><b>Link (e.g. http:// etc.)</b></td>
                <td align="center"><b>Text Description</b></td>
            </tr>
            <tr>
                <td><b>Help Link</b></td>
                <td><html:text name="edtaCode" property="medicalLink01"/></td>
                <td><html:text name="edtaCode" property="medicalLinkText01"/></td>
            </tr>
        </table>

        <html:submit value="Update" styleClass="btn"/>

    </html:form>
</div>
