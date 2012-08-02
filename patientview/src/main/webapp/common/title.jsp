<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="navbar">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="#">Site Title</a>
        <div class="nav-collapse">
            <ul class="nav pull-right">
                <logic:present role="patient,demo,superadmin,unitadmin,unitstaff">
                <li class="pull-right "><div class="navText">logged in as: <b><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername()%></b></div></li>
                <li><html:link action="logout">Logout</html:link></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Change specialty<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">IBD portal</a></li>
                        <li><a href="#">Renal patient portal</a></li>
                    </ul>
                </li>
                </logic:present>

                <li><html:link action="/help" styleClass="<%= ("help".equals(request.getAttribute("currentNav"))) ? "navlinkon" : "navlink" %>">Need help <i class="icon-question-sign icon-white"></i></html:link></li>

            </ul>

            <logic:notPresent role="patient,demo,superadmin,unitadmin,unitstaff">
                <form class="navbar-form pull-right" action="j_security_check" method="POST">
                    <input type="text" class="span2" name="j_username" placeholder="Username">
                    <input type="password" class="span2" name="j_password" placeholder="Password">
                    <input class="btn" type="submit" value="Login">
                </form>
            </logic:notPresent>

        </div><!-- /.nav-collapse -->
      </div>
    </div><!-- /navbar-inner -->
</div>

<div class="container">