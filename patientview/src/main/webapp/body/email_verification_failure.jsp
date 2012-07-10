<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<table width="690" height="1"><tr><td>&nbsp;</td></tr></table>

        <div align="center">
            <p><b>Email Verification Failed</b></p>
        </div>

          <p>The verification of your email failed. This is because:</p>
<ul>
    <li>More than two weeks have passed since the verification email was sent - request a new verification.</li>
    <li>The email address on the verification doesn't match the current email address on your profile - perhaps you changed your email address again?</li>
    <li>The verification code doesn't match any verification requests - make a new verification request.</li>
</ul>
