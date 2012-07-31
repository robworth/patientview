<%@ page import="com.worthsoln.utils.LegacySpringUtils" %><%
    String taglineImage, logoImage;

    if (LegacySpringUtils.getSecurityUserManager().isLoggedInToTenancy()) {
        taglineImage = "images/tagline.gif";
        logoImage = "images/logo.gif";
    } else {
        taglineImage = "images/tagline_generic.gif";
        logoImage = "images/logo_generic.gif";
    }
%>
    <tr bgcolor="#EDF6FB">
        <td colspan="2" nowrap="nowrap">
          <table>
            <tr>
              <td><img src="<%=taglineImage%>" width="258" height="31" border="0" alt="" hspace="20" /></td>
              <td><img src="<%=logoImage%>" width="366" height="55" border="0" alt="" /></td>
            </tr>
          </table>
        </td>
      </tr>
