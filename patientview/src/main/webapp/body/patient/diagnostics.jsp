<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Diagnostics</h1>
            <p><bean:message key="diagnostics.intro" /></p>
        </div>

        <div class="page-header">
            <h2>Imaging</h2>
        </div>

        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Exam Date</th>
                <th>Exam</th>
            </tr>
            </thead>
            <tbody>
            <logic:iterate name="diagnosticsImaging" id="image" type="com.worthsoln.patientview.model.Diagnostic">
                <tr>
                    <td>
                        <bean:write name="image" property="formattedDatestamp"/>
                    </td>

                    <td>
                        <bean:write name="image" property="description"/>
                    </td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>

        <div class="page-header">
            <h2>Endoscopy</h2>
        </div>

        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Procedure Date</th>
                <th>Procedure</th>
            </tr>
            </thead>
            <tbody>
            <logic:iterate name="diagnosticsEndoscopy" id="endoscopy" type="com.worthsoln.patientview.model.Diagnostic">
                <tr>
                    <td>
                        <bean:write name="endoscopy" property="formattedDatestamp"/>
                    </td>

                    <td>
                        <bean:write name="endoscopy" property="description"/>
                    </td>
                </tr>
            </logic:iterate>
            </tbody>
        </table>
    </div>
</div>