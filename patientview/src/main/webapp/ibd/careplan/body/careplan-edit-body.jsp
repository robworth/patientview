<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row" id="editCarePlan">
    <div class="span12">
        <div class="page-header">
            <h1>Care Plan</h1>
        </div>

        <html:form action="/careplan-update" styleClass="row">
            <html:errors/>

            <fieldset class="span12">
                <div class="row">
                    <div class="span12">
                        <h2>Before your appointment</h2>
                        <ul>
                            <li>
                                Whether you are newly diagnosed or have been living with IBD for a number of years, the
                                challenges of managing a chronic condition can change quickly.
                            </li>
                            <li>
                                Below is a list of common areas, where people feel they may need more help or
                                information.
                            </li>
                            <li>
                                Please tick the boxes to highlight the areas you would like to discuss at your next
                                appointment.
                            </li>
                            <li>
                                There are information links to learn more about these areas in detail.
                            </li>
                        </ul>

                    </div>
                </div>

                <div class="form-horizontal">
                    <div class="sliderGroup">
                        <div class="sliderHeading">
                            <div class="bottomValue">Is not a problem </div>
                            <div class="topValue">A big problem </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Overall my condition</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="overallMyConditionScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="overallMyConditionScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Tiredness /Fatigue</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="tirednessFatigueScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="tirednessFatigueScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Managing Pain</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingPainScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingPainScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Stress and worry</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="stressAndWorryScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="stressAndWorryScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Support from family and friends</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="supportFromFamilyAndFriendsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="supportFromFamilyAndFriendsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Managing my social life / hobbies</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingMySocialLifeHobbiesScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingMySocialLifeHobbiesScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Managing work / studies</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingWorkStudiesScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingWorkStudiesScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Taking my medicines regularly</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="takingMyMedicinesRegularlyScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="takingMyMedicinesRegularlyScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                    </div>

                    <div class="sliderGroup">
                        <div class="sliderHeading">
                            <div class="bottomValue">Don't need help</div>
                            <div class="topValue">Need help</div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Managing flare ups</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingFlareUpsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingFlareUpsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Stopping smoking</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="stoppingSmokingScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="stoppingSmokingScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Sleeping</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="sleepingScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="sleepingScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Sexual relationships</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="sexualRelationshipsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="sexualRelationshipsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Fertility / Pregnancy</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="fertilityPregnancyScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="fertilityPregnancyScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Learning about my condition</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="learningAboutMyConditionScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="learningAboutMyConditionScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Eating a healthy diet</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="eatingAHealthyDietScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="eatingAHealthyDietScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Travelling</label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="travellingScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="travellingScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row paragraphSizeTopMargin">
                    <div class="span12">
                        <label>
                            Use the box below to add further topic areas that you would like to explore.
                        </label>
                        <html:textarea property="otherAreasToDiscuss" rows="5" styleClass="span6"/>
                        <hr/>
                            <h2>During and after your appointment</h2>

                        <h3>My goals to improve my health</h3>
                        <p class="paragraphSizeTopMargin">
                            What goals would you like to change or improve about your health in the next year?
                        </p>

                        <p>
                            My goals for changing/improving my IBD are:
                            <br/>
                            <html:textarea property="goals" rows="5" styleClass="span6"/>
                        </p>

                        <p>
                            Think about these goals, what one goal would you like to achieve?
                            <br/>
                            <html:textarea property="goalToAchieve" rows="5" styleClass="span6"/>
                        </p>

                        <p>
                            How important is achieving this goal to you?
                            <br/>
                            <logic:present name="importanceList" scope="session">
                                <html:select property="importanceId">
                                    <html:options collection="importanceList" property="id" labelProperty="name"/>
                                </html:select>
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
                        <html:textarea property="howToAchieveGoal" rows="5" styleClass="span6"/>
                    </p>

                    <p>
                        What are the barriers that could get in the way?
                        <br/>
                        <html:textarea property="barriers" rows="5" styleClass="span6"/>
                    </p>

                    <p>
                        What can I do about this?
                        <br/>
                        <html:textarea property="whatCanBeDone" rows="5" styleClass="span6"/>
                    </p>

                    <p>
                        How confident do I feel?
                        <br/>
                        <logic:present name="confidenceList" scope="session">
                            <html:select property="confidenceId">
                                <html:options collection="confidenceList" property="id" labelProperty="name"/>
                            </html:select>
                        </logic:present>
                    </p>

                    <p>
                        Date set to review my Care Plan
                        <br/>
                        <div class="input-append date datePicker" data-date="<bean:write name="carePlanForm" property="reviewDate"/>">
                            <input name="reviewDate" class="span2" size="16" type="text" value="<bean:write name="carePlanForm" property="reviewDate"/>" readonly>
                            <span class="add-on"><i class="icon-th"></i></span>
                        </div>
                    </p>
                </div>

                <div class="form-actions">
                    <html:submit value="Save" styleClass="btn btn-primary"/>
                </div>
            </fieldset>
        </html:form>

        <ul>
            <li>
                If you would like, you could create a personal care plan. To learn more about care plans and for further
                tips on getting more from your appointments click here.
                <a href="http://www.myibdportal.org/personal-care-plans">click here link</a>
            </li>
        </ul>
    </div>
</div>