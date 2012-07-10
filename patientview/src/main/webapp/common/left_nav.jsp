<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

    <td class="left">


            <table>
              <tr>
                <td colspan="2"><img src="images/space.gif" height="20" width="140" border="0" alt="" class="picture" /></td>
              </tr>
              <tr>
                <td align="center" colspan="2"><img src="images/boy_and_apple.jpg" width="134" height="184" border="0" alt="Happy User" /></td>
              </tr>
            </table>


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




        </td>
