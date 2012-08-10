<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Nutrition</h1>
        </div>

        <html:form action="/nutrition" styleClass="form-horizontal">
            <html:errors/>

            <logic:iterate name="nutritionList" id="nutrition" type="com.worthsoln.ibd.model.Nutrition">
                <tr>
                    <td>
                        <bean:write name="nutrition" property="formattedNutritionDate"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:write name="nutrition" property="weight"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:write name="nutrition" property="foodsThatDisagree"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <bean:write name="nutrition" property="comment"/>
                    </td>
                </tr>
            </logic:iterate>

            <div class="row">
                <div class="span12">
                    <html:link action="/nutrition-edit">Add</html:link>
                </div>
            </div>

        </html:form>
    </div>
</div>


