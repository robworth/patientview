<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

        <div class="page-header">
            <h1>Colitis</h1>
        </div>

<div class="row">
    <div class="span5">
        <html:form action="/colitis-update" styleClass="form-horizontal">
            <html:errors/>
            <div class="control-group">
                <label class="control-label">Date</label>
                <div class="controls">
                    <div class="input-append date datePicker"
                         data-date="<bean:write name="colitisForm" property="colitisDate"/>">
                        <input name="colitisDate" class="span2" size="16" type="text"
                               value="<bean:write name="colitisForm" property="colitisDate"/>" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </div>
            </div>
            <div class="control-group">
                 <label class="control-label">Number of Stools (Day)</label>
                <div class="controls">
                    <html:select property="stoolsDay">
                        <html:options collection="stoolsDayList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Number of Stools (Night)</label>
                <div class="controls">
                    <html:select property="stoolsNight">
                        <html:options collection="stoolsNightList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">When I go to the toilet?</label>
                <div class="controls">
                    <html:select property="toiletTiming">
                        <html:options collection="toiletTimingList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Is there blood present?</label>
                <div class="controls">
                    <html:select property="presentBlood">
                        <html:options collection="presentBloodList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">How do I feel?</label>
                <div class="controls">
                    <html:select property="feeling">
                        <html:options collection="feelingList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Do I have any further complications?</label>
                <div class="controls">
                    <html:select property="furtherComplications">
                        <html:options collection="furtherComplicationList" property="id" name="id" labelName="displayText"
                                      labelProperty="displayText"/>
                    </html:select>
                </div>
            </div>
            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
            </div>
        </html:form>
        </div>
    </div>
    <div class="span7">
        <%--GRAPH GOES HERE--%>
    </div>
</div>


