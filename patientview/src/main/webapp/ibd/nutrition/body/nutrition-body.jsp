<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Nutrition</h1>
        </div>
        <p>
            This section is for you to keep a record of any foods that you think may upset your condition. Make a list of the foods that you have eaten and what symptoms it may have caused (e.g. abdominal pain or diarrhoea).
        </p>
        <p>
            Stop eating this particular food for a few weeks and then try it again.
        </p>

        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Weight</th>
                    <th>Foods that disagree with me</th>
                    <th>Enter a comment on my results</th>
                </tr>
            </thead>
            <tbody>
            <logic:iterate name="nutritionList" id="nutrition" type="com.worthsoln.ibd.model.Nutrition">
                <tr>
                    <td>
                        <bean:write name="nutrition" property="formattedNutritionDate"/>
                    </td>

                    <td>
                        <bean:write name="nutrition" property="weight"/>
                    </td>

                    <td>
                        <bean:write name="nutrition" property="foodsThatDisagree"/>
                    </td>

                    <td>
                        <bean:write name="nutrition" property="comment"/>
                    </td>
                </tr>
            </logic:iterate>
            </tbody>
       </table>
        <html:form action="/nutrition" styleClass="form-horizontal">
            <html:errors/>
            <div class="row">
                <div class="span12">
                    <html:link action="/nutrition-edit" styleClass="btn">Add</html:link>
                </div>
            </div>

        </html:form>
        <p>
            If you wish to calculate your BMI please visit this <a href="http://www.nhs.uk/Tools/Pages/Healthyweightcalculator.aspx">link</a> fot the NHS BMI calculator.
        </p>
    </div>
</div>


