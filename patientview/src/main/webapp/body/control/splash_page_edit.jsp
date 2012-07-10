<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Splash Page Editing</p>

<html:errors />

<p><b>Note:</b> It is the <b>name</b> field that lets a user know that they should see the page again. Changing the name lets users that have seen a previous splash page seen the new one. So if you are just tweaking a splash page, don't change the name. If it is a new one, change the name so everyone sees it.</p>

<p><b>HTML:</b> You can use simple html markup in the pages like links, bold but don't be too fancy or risk breaking the page.</p>
<html:form action="/control/splashPageUpdate">
   <html:hidden property="id" name="splashPage" />
<table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Name</b></td>
      <td><html:text name="splashPage" property="name" /></td>
    </tr>
    <tr>
      <td><b>Headline</b></td>
      <td><html:text name="splashPage" property="headline" /></td>
    </tr>
    <tr>
      <td><b>Body Text</b></td>
      <td><html:textarea cols="40" rows="10" name="splashPage" property="bodytext" /></td>
    </tr>
    <tr>
      <td><b>Is Live</b></td>
      <td><html:select name="splashPage" property="live" >
            <html:option value="true">live</html:option>
            <html:option value="false">inactive</html:option>
          </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>

    <tr>
        <td>&nbsp;</td>
      <td align="right"><html:submit value="Update" styleClass="formButton"/></td>
    </tr>
</html:form>

</table>
      <br/>
<hr/>

<p class="header"><bean:write name="splashPage" property="headline" ignore="true"/></p>

<p style="white-space: pre-wrap;"><bean:write filter="false" name="splashPage" property="bodytext"  ignore="true"/></p>

