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
                        <ul class="padded-list">
                            <li>
                                Whether you are newly diagnosed or have been living with IBD for a number of years, the
                                challenges of managing a chronic condition can change quickly.
                            </li>
                            <li>
                                Use this section to help think about how well you are currently managing your condition
                                and to highlight areas where you need more support.
                            </li>
                            <li>
                                My IBD Tracker&copy; shows the common topics that can cause difficulties in living with
                                IBD. Move the tracker to show how much each area is a concern and where you may need
                                more help at your next appointment. For further resources click on the <i class="icon-info-sign"></i> links.
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
                                Overall my condition

                                <logic:present name="overallMyConditionLink">
                                    <a href="<bean:write name="overallMyConditionLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>

                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="overallMyConditionScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="overallMyConditionScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Tiredness /Fatigue

                                <logic:present name="tirednessFatigueLink">
                                    <a href="<bean:write name="tirednessFatigueLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="tirednessFatigueScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="tirednessFatigueScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Managing Pain

                                <logic:present name="managingPainLink">
                                    <a href="<bean:write name="managingPainLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingPainScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingPainScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Stress and worry

                                <logic:present name="stressAndWorryLink">
                                    <a href="<bean:write name="stressAndWorryLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="stressAndWorryScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="stressAndWorryScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Support from family and friends

                                <logic:present name="supportFromFamilyAndFriendsLink">
                                    <a href="<bean:write name="supportFromFamilyAndFriendsLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="supportFromFamilyAndFriendsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="supportFromFamilyAndFriendsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Managing my social life / hobbies

                                <logic:present name="managingMySocialLifeHobbiesLink">
                                    <a href="<bean:write name="managingMySocialLifeHobbiesLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingMySocialLifeHobbiesScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingMySocialLifeHobbiesScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Managing work / studies

                                <logic:present name="managingWorkStudiesLink">
                                    <a href="<bean:write name="managingWorkStudiesLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingWorkStudiesScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingWorkStudiesScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Taking my medicines regularly

                                <logic:present name="takingMyMedicinesRegularlyLink">
                                    <a href="<bean:write name="takingMyMedicinesRegularlyLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="takingMyMedicinesRegularlyScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="takingMyMedicinesRegularlyScore" />"
                                       step="1"/>
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
                                Managing flare ups

                                <logic:present name="managingFlareUpsLink">
                                    <a href="<bean:write name="managingFlareUpsLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="managingFlareUpsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="managingFlareUpsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Stopping smoking

                                <logic:present name="stoppingSmokingLink">
                                    <a href="<bean:write name="stoppingSmokingLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="stoppingSmokingScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="stoppingSmokingScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Sleeping

                                <logic:present name="sleepingLink">
                                    <a href="<bean:write name="sleepingLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="sleepingScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="sleepingScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Sexual relationships

                                <logic:present name="sexualRelationshipsLink">
                                    <a href="<bean:write name="sexualRelationshipsLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="sexualRelationshipsScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="sexualRelationshipsScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Fertility / Pregnancy

                                <logic:present name="fertilityPregnancyLink">
                                    <a href="<bean:write name="fertilityPregnancyLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="fertilityPregnancyScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="fertilityPregnancyScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Learning about my condition

                                <logic:present name="learningAboutMyConditionLink">
                                    <a href="<bean:write name="learningAboutMyConditionLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="learningAboutMyConditionScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="learningAboutMyConditionScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Eating a healthy diet

                                <logic:present name="eatingAHealthyDietLink">
                                    <a href="<bean:write name="eatingAHealthyDietLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
                            <div class="controls sliderContainer">
                                <i class="smile happy">&#9786;</i>
                                <input name="eatingAHealthyDietScore" type="range" min="0" max="10"
                                       value="<bean:write name="carePlanForm" property="eatingAHealthyDietScore" />"
                                       step="1"/>
                                <i class="smile sad">&#9785;</i>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">
                                Travelling

                                <logic:present name="travellingLink">
                                    <a href="<bean:write name="travellingLink"/>" target="_blank"><i class="icon-info-sign"></i></a>
                                </logic:present>
                            </label>
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
                </div>
                <div class="row paragraphSizeTopMargin">
                    <div class="span12">
                        <label>
                            Use the box below to add further topic areas that you would like to explore.
                        </label>
                        <html:textarea property="otherAreasToDiscuss" rows="5" styleClass="span6"/>
                        <ul>
                            <li>
                                If you would like, you could create a personal care plan. To learn more about care plans and for further
                                tips on getting more from your appointments
                                <a target="_blank" href="http://www.myibdportal.org/welcome-to-my-ibd-portal/personal-care-plans">click here</a>.
                            </li>
                        </ul>
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
    </div>
</div>
