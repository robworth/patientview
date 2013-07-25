<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

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

<html:xhtml/>

<logic:notPresent name="emailSent">
<logic:notPresent name="commentSent">
<div class="page-header">
    <h1>Contact Details</h1>
</div>


<logic:present name="contacts">
<logic:notEmpty name="contacts">
<logic:iterate id="contact" name="contacts">
    <table class="table table-bordered table-striped">
        <tr>
            <th>Contact details for unit </th>
            <td><bean:write name="contact" property="unit.shortname"/></td>
        </tr>
        <tr>
            <th>NHS no</th>
            <td><bean:write name="contact" property="usermapping.nhsno"/></td>
        </tr>
    </table>
<logic:notEmpty name="contact" property="unit">



   <logic:present role="patient">
       <logic:present specialty="renal">
           <h2>Email your renal unit</h2>
       </logic:present>
       <logic:present specialty="ibd">
           <h2>Email your IBD unit</h2>
       </logic:present>

        <p>Any queries about results not appearing or being wrong, or about diagnosis or contact details.</p>
        <p>Note: Your name and NHS number will be sent with this message.</p>
        <p>Email is not regarded as a secure way to send sensitive data.</p>
    <logic:notEmpty name="contact" property="unit.renaladminemail">
        <html:form action="/patient/contactForm">
            <fieldset>
                <div class="control-group">
                    <label class="control-label">Please enter your message below</label>
                    <div class="controls"><html:textarea rows="6" cols="30" property="message"/></div>
                </div>
                <div class="control-group">
                    <label class="control-label">Email (this will help us contact you much faster to resolve your issue)</label>
                    <div class="controls"><html:text property="email" /></div>
                </div>
                <html:hidden property="unit.renaladminemail" name="contact" />
                <html:hidden property="usermapping.nhsno" name="contact" />
                <html:hidden property="type" value="unit" />
                <div class="form-actions">
                    <html:submit value="Send"  styleClass="btn" />
                </div>
            </fieldset>
        </html:form>
    </logic:notEmpty>
    <logic:empty name="contact" property="unit.renaladminemail">
        <div class="alert alert-error">It seems that your unit has not set up a contact email address, so you are not able to contact them from here. Let them know.</div>
    </logic:empty>
    </logic:present>


<logic:present role="patient">
<logic:equal value="RSC02" name="contact" property="unit.unitcode">
    <logic:present specialty="renal">
        <h2>Send feedback to your renal unit</h2>
    </logic:present>
    <logic:present specialty="ibd">
        <h2>Send feedback to your IBD unit</h2>
    </logic:present>

    <p>Any comments you wish to make about the unit.</p>
    <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

    <html:form action="/patient/feedbackForm">
        <fieldset>
        <div class="control-group">
            <label class="control-label">Please enter your message below:</label>
            <div class="controls"><html:textarea rows="6" cols="30" property="comment"/></div>
        </div>
        <div class="control-group">
            <label class="control-label">Anonymous</label>
            <label class="checkbox"><html:checkbox property="anonymous" value="on"/></label>
        </div>
        <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
        <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />
        <div class="form-actions">
        <html:submit value="Send"  styleClass="btn" />
        </div>
        </fieldset>
    </html:form>

<html:form action="/patient/feedbackViewForUnit">
    <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
    <html:submit value="View feedback for your unit" styleClass="btn" />
</html:form>
</logic:equal>



<logic:equal value="A" name="contact" property="unit.unitcode">



    <logic:present specialty="renal">
        <h2>Send feedback to your renal unit</h2>
    </logic:present>
    <logic:present specialty="ibd">
        <h2>Send feedback to your IBD unit</h2>
    </logic:present>
        <p>Any comments you wish to make about the unit.</p>
        <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

        <html:form action="/patient/feedbackForm" styleClass="form-horizontal">
            <fieldset>
            <div class="control-group">
                <label class="control-label">Please enter your message below</label>
                <div class="controls"><html:textarea rows="6" cols="30" property="comment" styleClass="input-xlarge"/></div>
            </div>

            <div class="control-group">
                <label class="control-label">Anonymous</label>
                <div class="controls">
                    <label class="checkbox"><html:checkbox property="anonymous" value="on"/></label>
                </div>
            </div>
            <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
            <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />
            <div class="form-actions">
                <html:submit value="Send"  styleClass="btn btn-primary" />
            </div>
            </fieldset>
        </html:form>
        <html:form action="/patient/feedbackViewForUnit">
            <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
            <html:submit value="View feedback for your unit" styleClass="btn" />
        </html:form>
</logic:equal>


<logic:equal value="SGC04" name="contact" property="unit.unitcode">
    <logic:present specialty="renal">
        <h2>Send feedback to your renal unit</h2>
    </logic:present>
    <logic:present specialty="ibd">
        <h2>Send feedback to your IBD unit</h2>
    </logic:present>
    <p>Any comments you wish to make about the unit.</p>
    <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

    <html:form action="/patient/feedbackForm" styleClass="form-horizontal">
        <fieldset>
            <div class="control-group">
                <label class="control-label">Please enter your message below: </label>
                <div class="controls"><html:textarea rows="6" cols="30" property="comment"/></div>
            </div>
        <div class="control-group">
            <label class="control-label">Anonymous</label>
            <div class="controls">
                <label class="checkbox"><html:checkbox property="anonymous" value="on"/></label>
            </div>
        </div>
        <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
        <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />
        <div class="form-actions">
            <html:submit value="Send"  styleClass="formbutton" />
        </div>
        </fieldset>
    </html:form>

<html:form action="/patient/feedbackViewForUnit">
    <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
    <html:submit value="View feedback for your unit" styleClass="formbutton" />
</html:form>
</logic:equal>
</logic:present> <%--close "patient" present --%>

</logic:notEmpty>

<logic:notPresent name="contact" property="patient">
<div class="alert alert-error">Patient details not uploaded</div>
</logic:notPresent>


<logic:present name="contact" property="patient">
    <h2>GP details for <bean:write name="contact" property="patient.forename"/> <bean:write name="contact" property="patient.surname"/></h2>
    <table width="440" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">

        <tr valign="top">
          <td class="tablecellbold">GP Name</td>

          <td class="tablecell"><bean:write name="contact" property="patient.gpname"/></td>
        </tr>

        <tr valign="top">
          <td class="tablecellbold">Address</td>

          <td class="tablecell"><bean:write name="contact" property="patient.gpaddress1"/><br />
          <bean:write name="contact" property="patient.gpaddress2"/><br />
          <bean:write name="contact" property="patient.gpaddress3"/></td>
        </tr>

        <tr valign="top">
          <td class="tablecellbold">Postcode</td>

          <td class="tablecell"><bean:write name="contact" property="patient.gppostcode"/></td>
        </tr>

        <tr valign="top">
          <td class="tablecellbold">Telephone</td>

          <td class="tablecell"><bean:write name="contact" property="patient.gptelephone"/></td>
        </tr>
     </table>
</logic:present>


<logic:present name="contact" property="unit">

          <h2>Unit Details for <bean:write name="contact" property="unit.name"/></h2>


          <table width="440" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">


            <tr valign="top">
              <td class="tablecellbold">Name</td>
              <td class="tablecell"><bean:write name="contact" property="unit.name"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Address</td>
              <td class="tablecell"><bean:write name="contact" property="unit.address1"/><br />
                                    <bean:write name="contact" property="unit.address2"/><br />
                                    <bean:write name="contact" property="unit.address3"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Postcode</td>
              <td class="tablecell"><bean:write name="contact" property="unit.postcode"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Unit Web Address</td>
              <td class="tablecell"><a href="<bean:write name="contact" property="unit.uniturl"/>" target="_blank"><bean:write name="contact" property="unit.uniturl"/></a></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Trust Web Address</td>
              <td class="tablecell"><a href="<bean:write name="contact" property="unit.trusturl"/>" target="_blank"><bean:write name="contact" property="unit.trusturl"/></a></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Patient View Admin Name</td>
              <td class="tablecell"><bean:write name="contact" property="unit.renaladminname"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Patient View Admin Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.renaladminphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Unit Enquiries Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.unitenquiriesphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Unit Enquiries Email</td>
              <td class="tablecell">
                <logic:notEmpty name="contact" property="unit.unitenquiriesemail">
                  <a href="mailto:<bean:write name="contact" property="unit.unitenquiriesemail"/>?subject=[Patient View Enquiry]"><bean:write name="contact" property="unit.unitenquiriesemail"/></a>
                </logic:notEmpty>
              </td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Appointments Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.appointmentphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Appointments Email</td>
              <td class="tablecell"><bean:write name="contact" property="unit.appointmentemail"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Out of Hours Info</td>
              <td class="tablecell"><bean:write name="contact" property="unit.outofhours"/></td>
            </tr>

            <logic:present specialty="renal">
                <tr valign="top">
                  <td class="tablecellbold">Peritoneal Dialysis Phone</td>
                  <td class="tablecell"><bean:write name="contact" property="unit.peritonealdialysisphone"/></td>
                </tr>
                <tr valign="top">
                  <td class="tablecellbold">Peritoneal Dialysis Email</td>
                  <td class="tablecell"><bean:write name="contact" property="unit.peritonealdialysisemail"/></td>
                </tr>
            </logic:present>


            <logic:notEmpty name="contact" property="unit.haemodialysisunitname1">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname1"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone1"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation1"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl1"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname2">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname2"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone2"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation2"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl2"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname3">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname3"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone3"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation3"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl3"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname4">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname4"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone4"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation4"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl4"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname5">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname5"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone5"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation5"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl5"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname6">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname6"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone6"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation6"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl6"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname7">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname7"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone7"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation7"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl7"/></td>             </tr>
            </logic:notEmpty>

            <logic:notEmpty name="contact" property="unit.haemodialysisunitname8">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname8"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone8"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation8"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl8"/></td>             </tr>
            </logic:notEmpty>

             <logic:notEmpty name="contact" property="unit.haemodialysisunitname9">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname9"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">NPhone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone9"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation9"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl9"/></td>             </tr>
            </logic:notEmpty>

             <logic:notEmpty name="contact" property="unit.haemodialysisunitname10">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname10"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone10"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation10"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl10"/></td>             </tr>
            </logic:notEmpty>

             <logic:notEmpty name="contact" property="unit.haemodialysisunitname11">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname11"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone11"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation11"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl11"/></td>             </tr>
            </logic:notEmpty>

             <logic:notEmpty name="contact" property="unit.haemodialysisunitname12">
            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>
            <tr valign="top">
              <td class="tableheader" colspan="2">Haemodialysis Unit - <bean:write name="contact" property="unit.haemodialysisunitname12"/></td>
            </tr>
            <tr valign="top">               <td class="tablecellbold">Phone</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitphone12"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Location</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisunitlocation12"/></td>             </tr>
            <tr valign="top">               <td class="tablecellbold">Web Address</td>               <td class="tablecell"><bean:write name="contact" property="unit.haemodialysisuniturl12"/></td>             </tr>
            </logic:notEmpty>
    </table>
</logic:present>

        <logic:present name="contact" property="patient">
          <p>
            <logic:notEmpty name="contact" property="unit.unitenquiriesemail">
              <a class="btn" href="mailto:<bean:write name="contact" property="unit.unitenquiriesemail"/>?subject=[Patient View Enquiry]">Email Unit</a>
            </logic:notEmpty>
          </p>
        </logic:present>
</logic:iterate>  <%--close contacts iterate--%>
</logic:notEmpty> <%--close contacts notEmpty--%>
</logic:present> <%--clost contacts present--%>

<logic:present specialty="renal">
    <h2>Email the RPV system administrator</h2>
</logic:present>
<logic:present specialty="ibd">
    <h2>Email the IBD system administrator</h2>
</logic:present>

<p>Any comments about the system as a whole, or the information links suggested.</p>
<html:form action="/patient/contactForm" styleClass="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label">Please enter your message below</label>
            <div class="controls"><html:textarea rows="6" cols="30" property="message"/></div>
        </div>
        <div class="control-group">
            <label class="control-label">Email (this will help us contact you much faster to resolve your issue)</label>
            <div class="controls"><html:text property="email" /></div>
        </div>
        <div class="form-actions">
            <html:hidden property="type" value="admin" />
            <input type="submit" value="Send" class="btn btn-primary"/>
        </div>
    </fieldset>
</html:form>
<p>
<logic:present specialty="renal">
    <a class="btn" href="mailto:admin@renalpatientview.org?subject=[Renal Patient View Enquiry]">Email Renal PatientView Administrator</a>
</logic:present>
<logic:present specialty="ibd">
    <a class="btn" href="mailto:admin-ibd@renalpatientview.org?subject=[IBD Patient View Enquiry]">Email IBD PatientView Administrator</a>
</logic:present>

</p>

</logic:notPresent>   <%--close of emailSent present--%>
</logic:notPresent>    <%--close of commentSent present--%>

<logic:present name="emailSent">
    <div class="page-header">
        <h1>Contact</h1>
    </div>
    <div class="alert alert-success">Your contact form was successfully submitted.</div>
</logic:present>

<logic:present name="commentSent">
    <div class="page-header">
        <h1>Comment</h1>
    </div>
    <div class="alert alert-success">Your comment form was successfully submitted.</div>
</logic:present>




