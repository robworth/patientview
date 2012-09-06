<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

        <div class="page-header">
            <h1>Nutrition <small>Discover up-to date information about food and IBD</small></h1>
        </div>

        <p>
            When you have IBD it is important to have a well-balanced, healthy diet. Although there is no research to
            date suggesting diet causes IBD, some people have found that certain foods can play a role in worsening
            their symptoms.     
        </p>

        <p>
            Below is a list of helpful resources about food and IBD, as well as a food diary. You can use this to keep
            a note of certain foods that you feel may be making your symptoms worse.     
        </p>

        <p>
            Please click on the information links to learn more:
        </p>

        <ul>
            <li><a target="_blank" href="">Food and IBD- Crohn's and Colitis, UK</a></li>
            <li><a target="_blank" href="">Diet and Nutrition- Crohn's and Colitis Foundation of America</a></li>
            <li><a target="_blank" href="">Healthy Eating</a></li>
            <li><a target="_blank" href="">Malnutrition</a></li>
            <li><a target="_blank" href="http://www.nhs.uk/Tools/Pages/Healthyweightcalculator.aspx">BMI Calculator</a></li>
        </ul>

        <h2>Food diary</h2>

        <p>
            This section is for you to keep a record of any foods that you think may upset your condition. Make a list
            of the foods that you have eaten and what symptoms it may have caused (e.g. abdominal pain or diarrhoea). 
        </p>
        <p>
            Stop eating this particular food for a few weeks and then try it again. 
        </p>

        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Date</th>
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

