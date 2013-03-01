<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

        <div class="page-header">
            <h1>Nutrition <small>Discover up-to date information about food and IBD</small></h1>
        </div>
        <ul class="padded-list">
            <li>
                When you have IBD it is important to have a well-balanced, healthy diet. Although there is no research to
                date suggesting diet causes IBD, some people have found that certain foods can play a role in worsening
                their symptoms.
            </li>

            <li>
                Below is a list of helpful resources about food and IBD, as well as a food diary. You can use this to keep
                a note of certain foods that you feel may be making your symptoms worse.
            </li>

            <li>
                Please click on the information links to learn more:
                <ul>
                    <li><a target="_blank" href="http://www.myibdportal.org/food-and-ibd">Food and IBD- Crohn's and Colitis, UK</a></li>
                    <li><a target="_blank" href="http://www.ccfa.org/resources/diet-and-nutrition.html">Diet and Nutrition- Crohn's and Colitis Foundation of America</a></li>
                    <li><a target="_blank" href="http://www.nhs.uk/livewell/healthy-eating/Pages/Healthyeating.aspx">Healthy Eating</a></li>
                    <li><a target="_blank" href="http://www.nhs.uk/conditions/malnutrition/pages/introduction.aspx">Malnutrition</a></li>
                    <li><a target="_blank" href="http://www.nhs.uk/Tools/Pages/Healthyweightcalculator.aspx">BMI Calculator</a></li>
                </ul>
            </li>
        </ul>


        <h2>Food diary</h2>
        <ul>
            <li>
                This section is for you to keep a record of any foods that you think may upset your condition.
            </li>
            <li>
                Make a list of the foods that you have eaten and what symptoms it may have caused (e.g. abdominal pain
                or diarrhoea). 
            </li>
            <li>
                Stop eating this particular food for a few weeks and then try it again.
            </li>
        </ul>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Foods that disagree with me</th>
                    <th>Enter a comment on the food</th>
                </tr>
            </thead>
            <tbody>
            <logic:iterate name="nutritionList" id="nutrition" type="com.worthsoln.ibd.model.Nutrition">
                <tr>
                    <td>
                        <bean:write name="nutrition" property="formattedNutritionDate"/>
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

