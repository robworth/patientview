<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<html:xhtml/>

<logic:notPresent name="emailSent">
<logic:notPresent name="commentSent">
<p class="header">Contact Details</p>


<logic:present name="contacts">
<logic:notEmpty name="contacts">

<logic:iterate id="contact" name="contacts">
       <table width="440" border="0" cellspacing="1" cellpadding="3">
            <tr valign="top">
              <td class="tableheader" colspan="2">Contact details for unit: <bean:write name="contact" property="unit.shortname"/>&nbsp; and NHS no: <bean:write name="contact" property="usermapping.nhsno"/></td>
            </tr>
      </table>

<logic:notEmpty name="contact" property="unit">



   <logic:present role="patient">
        <p><b>Email your renal unit</b></p>
        <p>Any queries about results not appearing or being wrong, or about diagnosis or contact details.</p>
        <p>Note: Your name and NHS number will be sent with this message.</p>
        <p>Email is not regarded as a secure way to send sensitive data.</p>
    <logic:notEmpty name="contact" property="unit.rpvadminemail">
        <html:form action="/patient/contactForm">
            Please enter your message below: <br />
            <html:textarea rows="6" cols="30" property="message"/>
            <br /><br />Email (this will help us contact you much faster to resolve your issue): <br />
            <html:text property="email" />
            <html:hidden property="unit.rpvadminemail" name="contact" />
            <html:hidden property="type" value="unit" />
            <br /><br /><html:submit value="Send"  styleClass="formbutton" />
        </html:form>
    </logic:notEmpty>
    <logic:empty name="contact" property="unit.rpvadminemail">
        <p><font color="red">It seems that your renal unit has not set up a contact email address, so you are not able to contact them from here. Let them know.</font></p>
    </logic:empty>

    <br/>
    </logic:present>


<logic:present role="patient">
<logic:equal value="RSC02" name="contact" property="unit.unitcode">
    <p><b>Send feedback to your renal unit</b></p>
    <p>Any comments you wish to make about the unit.</p>
    <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

    <html:form action="/patient/feedbackForm">
        Please enter your message below: <br />
        <html:textarea rows="6" cols="30" property="comment"/>
        <br /> <br />
        Anonymous&nbsp;&nbsp;<html:checkbox property="anonymous" value="on"/>

        <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
        <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />

        <br /><br /><html:submit value="Send"  styleClass="formbutton" />
    </html:form>

<br/>
<html:form action="/patient/feedbackViewForUnit">
    <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
    <html:submit value="View feedback for your unit" styleClass="formbutton" />
</html:form>
<br /><br />
</logic:equal>



<logic:equal value="A" name="contact" property="unit.unitcode">



        <p><b>Send feedback to your renal unit</b></p>
        <p>Any comments you wish to make about the unit.</p>
        <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

        <html:form action="/patient/feedbackForm">
            Please enter your message below: <br />
            <html:textarea rows="6" cols="30" property="comment"/>
            <br /> <br />
            Anonymous&nbsp;&nbsp;<html:checkbox property="anonymous" value="on"/>

            <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
            <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />

            <br /><br /><html:submit value="Send"  styleClass="formbutton" />
        </html:form>

    <br/>
    <html:form action="/patient/feedbackViewForUnit">
        <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
        <html:submit value="View feedback for your unit" styleClass="formbutton" />
    </html:form>
    <br /><br />


</logic:equal>


<logic:equal value="SGC04" name="contact" property="unit.unitcode">
    <p><b>Send feedback to your renal unit</b></p>
    <p>Any comments you wish to make about the unit.</p>
    <p>Note: By default this message will be anonymous, but you may untick the box below if you wish your name to be attached to the comment.</p>

    <html:form action="/patient/feedbackForm">
        Please enter your message below: <br />
        <html:textarea rows="6" cols="30" property="comment"/>
        <br /> <br />
        Anonymous&nbsp;&nbsp;<html:checkbox property="anonymous" value="on"/>

        <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
        <input type="hidden" name="nhsno" value="<bean:write name="contact" property="usermapping.nhsno"/>" />

        <br /><br /><html:submit value="Send"  styleClass="formbutton" />
    </html:form>

<br/>
<html:form action="/patient/feedbackViewForUnit">
    <input type="hidden" name="unitcode" value="<bean:write name="contact" property="unit.unitcode"/>" />
    <html:submit value="View feedback for your unit" styleClass="formbutton" />
</html:form>
<br /><br />
</logic:equal>
</logic:present> <%--close "patient" present --%>

</logic:notEmpty>





        <table width="440" border="0" cellspacing="1" cellpadding="3">


          <logic:notPresent name="contact" property="patient">
            <tr valign="top">
              <td class="tableheader" colspan="2">Patient details not uploaded</td>
            </tr>
          </logic:notPresent>


          <logic:present name="contact" property="patient">
            <tr valign="top">
              <td class="tableheader" colspan="2">GP details for <bean:write name="contact" property="patient.forename"/> <bean:write name="contact" property="patient.surname"/></td>
            </tr>

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
          </logic:present>


            <tr valign="top">
              <td colspan="2" class="tablecell">&nbsp;</td>
            </tr>




          <logic:present name="contact" property="unit">
            <tr valign="top">
              <td class="tableheader" colspan="2">Unit Details for <bean:write name="contact" property="unit.name"/></td>
            </tr>

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
              <td class="tablecell"><bean:write name="contact" property="unit.rpvadminname"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Patient View Admin Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.rpvadminphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Unit Enquiries Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.unitenquiriesphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Unit Enquiries Email</td>
              <td class="tablecell">
                <logic:notEmpty name="contact" property="unit.unitenquiriesemail">
                  <a href="mailto:<bean:write name="contact" property="unit.unitenquiriesemail"/>?subject=[Renal Patient View Enquiry]"><bean:write name="contact" property="unit.unitenquiriesemail"/></a>
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
            <tr valign="top">
              <td class="tablecellbold">Peritoneal Dialysis Phone</td>
              <td class="tablecell"><bean:write name="contact" property="unit.peritonealdialysisphone"/></td>
            </tr>
            <tr valign="top">
              <td class="tablecellbold">Peritoneal Dialysis Email</td>
              <td class="tablecell"><bean:write name="contact" property="unit.peritonealdialysisemail"/></td>
            </tr>


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

          </logic:present>

        </table>
        <logic:present name="contact" property="patient">
          <p>
            <logic:notEmpty name="contact" property="unit.unitenquiriesemail">
              <a href="mailto:<bean:write name="contact" property="unit.unitenquiriesemail"/>?subject=[Renal Patient View Enquiry]">Email Renal Unit</a>
            </logic:notEmpty>
          </p>
        </logic:present>
</logic:iterate>  <%--close contacts iterate--%>
</logic:notEmpty> <%--close contacts notEmpty--%>
</logic:present> <%--clost contacts present--%>

<br /><br />
<p><b>Email the RPV system administrator</b></p>
<p>Any comments about the system as a whole, or the information links suggested.</p>
<html:form action="/patient/contactForm">
    Please enter your message below: <br />
    <html:textarea rows="6" cols="30" property="message"/>
    <br /><br />Email (this will help us contact you much faster to resolve your issue): <br />
    <html:text property="email" />
    <html:hidden property="type" value="admin" />
    <br />
    <input type="submit" value="Send" />
</html:form>



           <p>
              <a href="mailto:admin@renalpatientview.org?subject=[Renal Patient View Enquiry]">Email Renal PatientView Administrator</a>
          </p>



  </logic:notPresent>   <%--close of emailSent present--%>
</logic:notPresent>    <%--close of commentSent present--%>

<logic:present name="emailSent">
    <p class="header">Contact</p>

    <p>Your contact form was successfully submitted.</p>
</logic:present>

<logic:present name="commentSent">
    <p class="header">Comment</p>

    <p>Your comment was successfully submitted.</p>
</logic:present>




