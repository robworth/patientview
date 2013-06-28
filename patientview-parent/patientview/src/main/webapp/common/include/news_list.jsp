<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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

<div class="span4 seperatingBorders pull-right">
        <h3 class="mediumBlueTitle titleSeperator">News</h3>

        <logic:present name="newses">
            <logic:empty name="newses">
                <p>
                    <i>There are currently no news items.</i>
                </p>
            </logic:empty>
            <logic:notEmpty name="newses">
                <ul class="linkList">
                    <logic:iterate id="news" name="newses">
                        <li>
                            <html:link action="/newsView" paramId="id" paramName="news" paramProperty="id" styleClass="readMoreParagraph">
                                &raquo; <bean:write name="news" property="headline" />
                            </html:link>
                        </li>
                    </logic:iterate>
                </ul>
            </logic:notEmpty>

        </logic:present>
    </div>
