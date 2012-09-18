<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="span9">
    <div class="page-header">
        <h1>Links Editing</h1>
    </div>

    <html:errors/>

    <html:form action="/control/careplan-links-add-save">
        <html:hidden property="linkType" name="codeType" />
        <table cellpadding="3" >
            <tr>
                <td><img src="images/space.gif" height="10" /> </td>
            </tr>
            <tr>
                <td><b>Code</b></td>
                <td><html:text property="edtaCode" /></td>
            </tr>
            <tr>
                <td><b>Description</b></td>
                <td><html:text property="description" /></td>
            </tr>
            <tr>
                <td><img src="images/space.gif" height="10" /> </td>
            </tr>
            <tr>
                <td></td>
                <td align="center"><b>Link (e.g. http:// etc.)</b></td>
                <td align="center"><b>Text Description</b></td>
            </tr>
            <tr>
                <td><b>Help Link</b></td>
                <td><html:text property="medicalLink01" /></td>
                <td><html:text property="medicalLinkText01" /></td>
            </tr>
            <tr>
                <td><html:submit value="Add" styleClass="formbutton" /></td>
            </tr>
        </table>

    </html:form>
</div>
