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

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Splash Pages</h1>
</div>

<html:errors />


<table cellpadding="3" class="table table-bordered table-striped">


    <logic:notEmpty name="splashpages">

        <tr class="tableheader">
            <td class="tableheader">Name</td>
            <td class="tableheader">Headline</td>
            <td class="tableheader">Unit</td>
            <td class="tableheader">Live</td>
            <td></td>
        </tr>

        <logic:iterate id="splashpage" name="splashpages" >
            <tr>

                <td class="tablecell">
                    <html:link action="/control/splashPageEdit" paramId="id" paramName="splashpage" paramProperty="id">
                        <bean:write name="splashpage" property="name" />
                    </html:link>
                </td>

                <td class="tablecell"><bean:write name="splashpage" property="headline" /></td>

                <td class="tablecell"><bean:write name="splashpage" property="unitcode" /></td>

                <td class="tablecell" align="center">
                    <logic:equal    value="true" name="splashpage" property="live"><font color="green">&#10004;</font></logic:equal>
                    <logic:notEqual value="true" name="splashpage" property="live"><font color="red">&#10008;</font></logic:notEqual>
                </td>



                <td class="tablecell" align="center" valign="center">
                    <html:form action="/control/splashPageDelete">
                        <html:hidden name="splashpage" property="id" />
                        <html:submit value="Delete" styleClass="btn" />
                    </html:form>
                </td>


            </tr>
        </logic:iterate>

    </logic:notEmpty>
</table>


<html:form action="/control/splashPageAddInput">
    <html:submit value="Add New" styleClass="btn" />
</html:form>



</div>
</div>

