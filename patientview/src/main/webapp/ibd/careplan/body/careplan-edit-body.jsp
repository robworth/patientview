<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Care Plan</h1>
        </div>
    <div>
</div>
        <html:form action="/careplan-update" styleClass="row">
            <html:errors/>
            <fieldset class="span12">
                <div class="row">
                    <div class="span12">
                        <p>
                            These are some common areas that can have an impact on living with IBD. Which are the
                            most relevant to you? Please tick the boxes to highlight the areas you would like to
                            discuss at your next appointment. There are information links to learn more about
                            these areas in detail.
                        </p>
                    </div>
                </div>
                        <bean:define id="areaToDiscussIds" name="carePlanForm" property="areaToDiscussIds" type="java.lang.Long[]"/>
                        <%
                            int count = 0;
                        %>
                        <logic:iterate name="areaToDiscussList" id="areaToDiscuss" type="com.worthsoln.ibd.model.enums.AreaToDiscuss">
                            <%
                            boolean checked = false;
                            count ++;

                            if (count == 3) {
                                    count = 1;
                            }

                            for (Long l : areaToDiscussIds) {
                                if (l == areaToDiscuss.getId()) {
                                    checked = true;
                                    break;
                                }
                            }
                            %>
                            <%=count == 1 ? "<div class=\"row\">" :""%>
                            <label class="checkbox inline span6" for="areaToDiscuss<bean:write name="areaToDiscuss" property="id" />">
                            <input type="checkbox" id="areaToDiscuss<bean:write name="areaToDiscuss" property="id" />"
                                   name="areaToDiscussIds" value="<bean:write name="areaToDiscuss" property="id" />"
                                   <%=(checked ? "checked=\"checked\"":"")%> />
                            <bean:write name="areaToDiscuss" property="name"/></label>
                            <%=count == 2 ? "</div>": "" %>
                        </logic:iterate>
                    <div class="row paragraphSizeTopMargin">
                        <div class="span12">
                            <label>
                                Use the box below to add further topic areas that you would like to explore.
                            </label>
                            <html:textarea property="furtherTopics" rows="5" styleClass="span6"/>
                            <div class="page-header">
                                <h2>Set your goals to improve your health</h2>
                            </div>
                            <p>
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
                                <html:select property="goalScale">
                                    <html:options collection="scaleList" property="value" name="value"/>
                                </html:select>
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
                        <html:select property="confidenceScale">
                            <html:options collection="scaleList" property="value" name="value"/>
                        </html:select>
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
