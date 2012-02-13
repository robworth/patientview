<%
    String redirectURL = request.getContextPath() + "/error?type=404";
    response.sendRedirect(redirectURL);
%>