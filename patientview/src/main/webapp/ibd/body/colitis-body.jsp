<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Colitis</h1>
        </div>
        <div>
        </div>
        <html:form action="/colitis-update" styleClass="form-horizontal row">
            <html:errors/>

            <div class="input-append date datePicker"
                 data-date="<bean:write name="colitisForm" property="colitisDate"/>">
                <input name="colitisDate" class="span2" size="16" type="text"
                       value="<bean:write name="colitisForm" property="colitisDate"/>" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            Number of Stools (Day)
            <html:select property="stoolsDay">
                <html:options collection="stoolsDayList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            Number of Stools (Night)
            <html:select property="stoolsNight">
                <html:options collection="stoolsNightList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            When I go to the toilet?
            <html:select property="toiletTiming">
                <html:options collection="toiletTimingList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            Is there blood present?
            <html:select property="presentBlood">
                <html:options collection="presentBloodList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            How do I feel?
            <html:select property="feeling">
                <html:options collection="feelingList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            Do I have any further complications?
            <html:select property="furtherComplications">
                <html:options collection="furtherComplicationList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            <html:submit value="Save" styleClass="btn btn-primary"/>

        </html:form>
    </div>
</div>
