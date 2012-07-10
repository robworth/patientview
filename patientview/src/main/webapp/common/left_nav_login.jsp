<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<td class="left">
    <table>
        <tr>
            <td colspan="2" align="center" height="30"><font class="subheader">PLEASE LOG IN BELOW</font></td>
        </tr>
        <form action="j_security_check" method="POST">
            <tr>
                <td class="smallform">user name</td>
                <td><input type="text" name="j_username" class="loginform" tabindex="1" /></td>
            </tr>

            <tr>
                <td class="smallform">password</td>
                <td><input type="password" name="j_password" class="loginform" tabindex="2" /></td>
            </tr>

            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="GO &#187;" class="formbutton" style="border-style: outset;" tabindex="3" />
                </td>
            </tr>
        </form>

        <tr>
            <td colspan="2"><a href="forgotten-password.jsp">Forgotten password?</a></td>
        </tr>

        <tr>
            <td colspan="2"><img src="images/space.gif" height="20" border="0" alt="" class="picture" /></td>
        </tr>
        <tr>
            <td align="center" colspan="2"><img src="images/nurse.jpg" width="134" height="141" border="0" alt="" />
            </td>
        </tr>
    </table>
</td>
