<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<table width="100%" cellpadding="3">
  <tr>
    <td align="left"><html:link action="/control/newsList" styleClass="btn">&larr; back to News List</html:link></td>
  </tr>
</table>


<html:form action="/control/newsPreview">

  <table cellpadding="3" >
    <tr>
      <td><img src="images/space.gif" height="10" /> </td>
    </tr>
    <tr>
      <td><b>Headline</b></td>
    </tr>
    <tr>
      <td><html:text name="news" property="headline" /></td>
    </tr>
    <tr>
      <td><b>Text</b></td>
    </tr>
    <tr>
      <td colspan="2"><html:textarea name="news" property="body" cols="50" rows="12" /></td>
    </tr>
    <tr>
        <td><b>Renal Unit</b></td>
        <td><html:select name="news" property="unitcode">
               <logic:present role="superadmin">
                 <html:option value="all">-- All Units --</html:option>
               </logic:present>
               <html:options collection="units" property="unitcode" labelProperty="name"/>
            </html:select></td>
      </tr>
    <tr>
      <td><b>Viewable by Unit Admins</b></td>
      <td><html:checkbox name="news" property="admin" value="true" /></td>
    </tr>
    <tr>
      <td><b>Viewable by Patients</b></td>
      <td><html:checkbox name="news" property="patient" value="true" /></td>
    </tr>
    <logic:present role="superadmin">
      <tr>
        <td><b>Viewable by the General Public</b></td>
        <td><html:checkbox name="news" property="everyone" value="true" /></td>
      </tr>
    </logic:present>
    <tr align="right">
      <html:hidden name="news" property="id" />
      <td>&nbsp;</td>
      <td><html:submit value="Preview" styleClass="btn" property="submission"/>&nbsp;
      <html:submit value="Post" styleClass="btn" property="submission"/></td>
    </tr>
 </table>                  

</html:form>

<logic:present name="news">

  <p class="header"><bean:write name="news" property="headline" /></p>

  <p><bean:write name="news" property="bodyForHtml" filter="false" /></p>

</logic:present>
</div>
</div>