<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ page import="org.patientview.patientview.model.User" %>
<%@ page import="org.patientview.patientview.model.SpecialtyUserRole" %>
<%@ page import="java.util.List" %>
<%@ page import="org.patientview.patientview.model.Specialty" %>
<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

<!-- the specialty switcher -->
<%
    if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty()) {

        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        List<SpecialtyUserRole> specialtyUserRoles = LegacySpringUtils.getUserManager().getSpecialtyUserRoles(user);

        if (specialtyUserRoles.size() > 1) {

%>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Change specialty<b class="caret"></b></a>
    <ul class="dropdown-menu">
         <%
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            for (SpecialtyUserRole specialtyUserRole : specialtyUserRoles) {
                if (!specialtyUserRole.getSpecialty().equals(specialty)) {
        %>
            <li><a href="/launchpad-select.do?specialtyId=<%=specialtyUserRole.getSpecialty().getId()%>"><%=specialtyUserRole.getSpecialty().getName()%></a></li>
        <%
                }
            }
        %>
    </ul>
</li>
<%
        }
    }
%>
