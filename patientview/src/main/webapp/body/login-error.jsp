<p class="header">Login Failed</p>

<%
  if (request.getAttribute("lockedOut") != null && (Boolean) request.getAttribute("lockedOut")) {
%>
<p>You have tried using an incorrect password too many times. Your password has been locked.</p>

<p>Please contact an administrator in your unit to have it unlocked.</p>
<%
} else {
%>
<p>Your login attempt failed, please try again.</p>
<%
  }
%>