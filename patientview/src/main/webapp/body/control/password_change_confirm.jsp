<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<logic:present name="verificationMailSent">

    <p class="header"><b>Verify your Email Address</b></p>

    <p>
        A verification email has been sent to your email address. You need to
        click the link in that email to verify your email address.The verification link will expire
        in two weeks.
    </p>
</logic:present>

<p class="header"><b>Password Changed</b></p>

<p>
    Your password has been changed. Please do not tell it to anyone and come
    back and change it if you think that anyone might have learned it.
</p>


