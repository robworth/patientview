<%@ page import="com.worthsoln.ibd.Ibd" %>
<%@ page import="com.worthsoln.ibd.model.enums.Severity" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span7">
        <div class="page-header">
            <h1>IBD Patient Settings</h1>
        </div>
        <p>
            <strong>Defaults:</strong>
            <br />
            Severe - <%=Severity.SEVERE.getDefaultLevel()%>, Moderate - <%=Severity.MODERATE.getDefaultLevel()%>, Mild - <%=Severity.MILD.getDefaultLevel()%>
        </p>
        <p>
            <em>Leave values blank to use defaults</em>
            <br />
        </p>
        <html:form action="/control/ibduser-edit">
            <html:errors/>

            <html:hidden property="submit" value="true" />

            <html:hidden property="nhsNo" />

            <div class="control-group">
                <label class="control-label">Severe level:</label>

                <div class="controls">
                    <html:text property="severeLevel"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Severe level treatment:</label>

                <div class="controls">
                    <html:textarea property="severeTreatment"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Moderate level:</label>

                <div class="controls">
                    <html:text property="moderateLevel"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Moderate level treatment:</label>

                <div class="controls">
                    <html:textarea property="moderateTreatment"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Mild level:</label>

                <div class="controls">
                    <html:text property="mildLevel"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">Mild level treatment:</label>

                <div class="controls">
                    <html:textarea property="mildTreatment"/>
                </div>
            </div>

            <div class="form-actions">
                <html:submit value="Save" styleClass="btn btn-primary"/>
            </div>
        </html:form>
    </div>
</div>
