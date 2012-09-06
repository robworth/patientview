<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Nutrition</h1>
        </div>

        <html:form action="/nutrition-update" styleClass="form-horizontal">
            <fieldset>
            <html:errors/>
            <div class="control-group">
                <label class="control-label">When this food was eaten</label>
                <div class="controls">
                    <div class="input-append date datePicker"
                         data-date="<bean:write name="nutritionForm" property="nutritionDate"/>">
                        <input name="nutritionDate" class="span2" size="16" type="text"
                               value="<bean:write name="nutritionForm" property="nutritionDate"/>" readonly>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </div>                    
            </div>

            <div class="control-group">
                <label class="control-label">
                    Foods that disagree with me
                </label>
                <div class="controls">
                    <html:textarea property="foodsThatDisagree" rows="5" styleClass="span6"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    Enter a comment on my results
                </label>
                <div class="controls">
                    <html:textarea property="comment" rows="5" styleClass="span6"/>
                </div>
            </div>
            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
                <html:link action="/nutrition" styleClass="btn">Cancel</html:link>
            </div>
            </fieldset>
        </html:form>
    </div>
</div>
