<%--
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

      <logic:present name="newses">
        <logic:notEmpty name="newses">

          <table cellpadding="3" width="90%">

            <tr>
              <td colspan="2"><hr class="thinblue"/></td>
            </tr>

          <logic:iterate id="news" name="newses" >
            <tr>
              <td><html:link action="/newsView" paramId="id" paramName="news" paramProperty="id"><b><bean:write name="news" property="headline" /></b></html:link></td>
            </tr>
            <tr>
              <td colspan="2"><hr class="thinblue" /></td>
            </tr>
          </logic:iterate>

          </table>

        </logic:notEmpty>

      </logic:present>
--%>
