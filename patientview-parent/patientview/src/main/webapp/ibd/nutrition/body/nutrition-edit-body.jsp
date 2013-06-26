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

<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Nutrition</h1>
        </div>

        <html:form action="/nutrition-update" styleClass="form-horizontal">
            <fieldset>
            <html:errors/>
            <div class="control-group">
                <label class="control-label">When this food was eaten</label>
                <div class="controls">
                    <div class="input-append date datePicker"
                         data-date="<bean:write name="nutritionForm" property="nutritionDate"/>">
                        <input name="nutritionDate" class="span2" size="16" type="text"
                               value="<bean:write name="nutritionForm" property="nutritionDate"/>" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    Foods that disagree with me
                </label>
                <div class="controls">
                    <html:textarea property="foodsThatDisagree" rows="5" styleClass="span6"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    Enter a comment on my results
                </label>
                <div class="controls">
                    <html:textarea property="comment" rows="5" styleClass="span6"/>
                </div>
            </div>
            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
                <html:link action="/nutrition" styleClass="btn">Cancel</html:link>
            </div>
            </fieldset>
        </html:form>
    </div>
</div>
