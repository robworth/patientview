<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>Diagnostics</h1>
        </div>

        <ul class="padded-list">
            <li>
                The Diagnostics section lists your previous imaging and endoscopy tests direct from the hospital record.
            </li>
            <li>
                Please note only the date and type of test is recorded. The full report is not available.
            </li>
            <li>
                If you have any further questions about your tests please contact us by
                <html:link action="/patient/ibd-contact">clicking here.</html:link>
            </li>
            <li>
                To learn more about the investigations used commonly in IBD click on the following links:
            </li>
        </ul>



        <h3>Links about Diagnostics</h3>
        <ul>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#endoscopy">Endoscopy (Colonosocopy / Sigmoidoscopy/ Gastroscopy)</a></li>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#plain-x-rays">X-Rays- Plain and Barium tests</a></li>
            <li><a target="_blank" href="http://www.myibdportal.org/investigations-for-ibd#other-x-ray-tests-and-scans">CT scans and MRI scans</a></li>
        </ul>

        <%--<p><bean:message key="diagnostics.intro" /></p>--%>
        <hr/>

            <h2>Imaging</h2>

        <table class="table table-bordered table-striped paragraphSizeTopMargin">
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
            <h2>Endoscopy</h2>
        <table class="table table-bordered table-striped paragraphSizeTopMargin">
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