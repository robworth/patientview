<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="row" id="editCarePlan">
<div class="span12">
<div class="page-header">
    <h1>Care Plan</h1>
</div>

<form:form action="/diabetes/web/careplan-update" class="row" modelAttribute="carePlan">
<form:hidden path="id"/>
<form:hidden path="nhsno"/>
<c:if test="${messages!=null && messages}">
    <div class="alert alert-error">
        <p>To continue you must meet the following criteria</p>
        <ul>
            <form:errors path="whatCanBeDone" element="li"/>
            <form:errors path="goals" element="li"/>
            <form:errors path="goalToAchieve" element="li"/>
            <form:errors path="importanceId" element="li"/>
            <form:errors path="howToAchieveGoal" element="li"/>
            <form:errors path="barriers" element="li"/>
            <form:errors path="confidenceId" element="li"/>
            <form:errors path="reviewDate" element="li"/>
            <form:errors path="nhsno" element="li"/>
        </ul>
    </div>
</c:if>

<fieldset class="span12">
<div class="row">
    <div class="span12">
        <h2>Before your appointment</h2>
        <ul class="padded-list">
            <li>
                Whether you are newly diagnosed or have been living with diabetes for a number of years, the
                challenges of managing a chronic condition can change quickly.
            </li>
            <li>
                Use this section to help think about how well you are currently managing your condition
                and to highlight areas where you need more support.
            </li>
            <li>
                This page shows the common topics that can cause difficulties in living with
                diabetes. Move the tracker to show how much each area is a concern and where you may need
                more help at your next appointment. For further resources click on the <i class="icon-info-sign"></i>
                links.
            </li>
        </ul>

    </div>
</div>
<div class="row">
<div class="form-horizontal">
<div class="sliderGroup span6">
    <div class="sliderHeading">
        <div class="bottomValue">Is not a concern</div>
        <div class="topValue">A big concern</div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Diabetes overall

            <logic:present name="diabetesOverallLink">
                <a href="${diabetesOverallLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="diabetesOverallScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Mood

            <logic:present name="moodLink">
                <a href="${moodLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="moodScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Diabetes Control

            <logic:present name="diabetesControlLink">
                <a href="${diabetesControlLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="diabetesControlScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Hypoglycaemia

            <logic:present name="hypoglycaemiaLink">
                <a href="${hypoglycaemiaLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="hypoglycaemiaScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Weight

            <logic:present name="weightLink">
                <a href="${weightLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="weightScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Blood Pressure

            <logic:present name="bloodPressureLink">
                <a href="${bloodPressureLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="bloodPressureScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Cholesterol

            <logic:present name="cholesterolLink">
                <a href="${cholesterolLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="cholesterolScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Smoking / Alcohol

            <logic:present name="smokingAlcoholLink">
                <a href="${smokingAlcoholLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="smokingAlcoholScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
</div>

<div class="sliderGroup span6">
    <div class="sliderHeading">
        <div class="bottomValue">Don't need help</div>
        <div class="topValue">Need help</div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Eating / Exercise / Sport

            <logic:present name="eatingExerciseSportLink">
                <a href="${eatingExerciseSportLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="eatingExerciseSportScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Driving / Work / Study

            <logic:present name="drivingWorkStudyLink">
                <a href="${drivingWorkStudyLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="drivingWorkStudyScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Pregnancy / Sex / Relationships

            <logic:present name="pregnancySexRelationshipsLink">
                <a href="${pregnancySexRelationshipsLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="pregnancySexRelationshipsScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Eyes

            <logic:present name="eyesLink">
                <a href="${eyesLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="eyesScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Kidneys

            <logic:present name="kidneysLink">
                <a href="${kidneysLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="kidneysScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Feet

            <logic:present name="feetLink">
                <a href="${feetLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="feetScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Heart Attacks / Strokes

            <logic:present name="heartAttacksStrokesLink">
                <a href="${heartAttacksStrokesLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="heartAttacksStrokesScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">
            Taking medication regularly

            <logic:present name="takingMedicationRegularlyLink">
                <a href="${takingMedicationRegularlyLink}" target="_blank"><i class="icon-info-sign"></i></a>
            </logic:present>
        </label>

        <div class="controls sliderContainer">
            <i class="smile happy">&#9786;</i>
            <form:input path="takingMedicationRegularlyScore" type="range" min="0" max="10" step="1"/>
            <i class="smile sad">&#9785;</i>
        </div>
    </div>
</div>
</div>
</div>
<div class="row paragraphSizeTopMargin">
    <div class="span12">
        <label>
            Use the box below to add further topic areas that you would like to explore.
        </label>
        <form:textarea path="otherAreasToDiscuss" rows="5" class="span6"/>
        <ul>
            <li>
                If you would like, you could create a personal care plan. To learn more about care plans and for further
                tips on getting more from your appointments
                <a target="_blank" href="http://www.myibdportal.org/welcome-to-my-ibd-portal/personal-care-plans">click
                    here</a>.
            </li>
        </ul>
        <hr/>
        <h2>During and after your appointment</h2>

        <h3>My goals to improve my health</h3>

        <p class="paragraphSizeTopMargin">
            What goals would you like to change or improve about your health in the next year?
        </p>

        <p>
            My goals for changing/improving my diabetes are:
            <br/>
            <form:textarea path="goals" rows="5" class="span6"/>
        </p>

        <p>
            Think about these goals, what one goal would you like to achieve?
            <br/>
            <form:textarea path="goalToAchieve" rows="5" class="span6"/>
        </p>

        <p>
            How important is achieving this goal to you?
            <br/>
            <logic:present name="importanceList" scope="session">
                <form:select path="importanceId" itemValue="${importanceId}">
                    <form:options items="${importanceList}" itemLabel="name" itemValue="id"/>
                </form:select>
            </logic:present>
        </p>
    </div>
</div>

<div class="control-group">
    <div class="page-header">
        <h2>Action plan</h2>
    </div>

    <p>
        In this section a specific plan on how to achieve the goal will be made.
    </p>

    <p>
        What am I going to do to achieve this goal?
        <br/>
        (How, what, when, where, how often)
        <br/>
        <form:textarea path="howToAchieveGoal" rows="5" class="span6"/>
    </p>

    <p>
        What are the barriers that could get in the way?
        <br/>
        <form:textarea path="barriers" rows="5" class="span6"/>
    </p>

    <p>
        What can I do about this?
        <br/>
        <form:textarea path="whatCanBeDone" rows="5" class="span6"/>
    </p>

    <p>
        How confident do I feel?
        <br/>
        <logic:present name="confidenceList" scope="session">
            <form:select path="confidenceId">
                <form:options items="${confidenceList}" itemLabel="name" itemValue="id"/>
            </form:select>
        </logic:present>
    </p>

    <p>
        Date set to review my Care Plan
        <br/>

    <div class="input-append date datePicker" data-date="<fmt:formatDate value='${carePlan.reviewDate}' pattern='dd-MM-yyyy'/>">
        <input id="reviewDate" name="reviewDate" class="span2" size="16" type="text" readonly="true" value="<fmt:formatDate value='${carePlan.reviewDate}' pattern='dd-MM-yyyy'/>"/>
        <span class="add-on"><i class="icon-th"></i></span>
    </div>
    </p>
</div>

<div class="form-actions">
    <html:submit value="Save" styleClass="btn btn-primary"/>

</div>
</fieldset>
</form:form>
</div>
</div>