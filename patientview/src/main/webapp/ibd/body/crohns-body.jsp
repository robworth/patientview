<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Crohns</h1>
        </div>
        <div>
        </div>
        <html:form action="/crohns-update" styleClass="form-horizontal row">
            <html:errors/>

            <div class="input-append date datePicker"
                 data-date="<bean:write name="crohnsForm" property="chornsDate"/>">
                <input name="chornsDate" class="span2" size="16" type="text"
                       value="<bean:write name="crohnsForm" property="chornsDate"/>" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            I have currently got
            <html:select property="abdominalPain">
                <html:options collection="abdominalPainList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select> abdominal pain at present

            My bowels are open
            <html:textarea property="openBowels"/>
            a day

            Currently I am feeling
            <html:select property="feeling">
                <html:options collection="feelingList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>

            I have
            <html:select property="complications">
                <html:options collection="crohnsComplicationList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>
            complications from my IBD

            The Doctor has said I have
            <html:select property="massInTummy">
                <html:options collection="massInTummyList" property="id" name="id" labelName="displayText"
                              labelProperty="displayText"/>
            </html:select>
            mass in my tummy

            <html:submit value="Save" styleClass="btn btn-primary"/>

        </html:form>
    </div>
</div>
