<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

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


<div class="page-header">
    <h1>Renal PatientView Help page</h1>
</div>
 <dl class="flush-dl">
          <dt>Questions about your own information<dt>
            <dd>If you are a patient, and suspect that wrong information is being shown, you need to contact your local PatientView administrator. <logic:present role="patient">If you are logged in you can <html:link action="/patient/contact">click here to send a message.</html:link></logic:present></dd>

        <dt>Other questions</dt>
        <dd>The first place to contact is usually your local PatientView administrator. <logic:present role="patient">If you are logged in you can <html:link action="/patient/contact">click here to send a message.</html:link></logic:present></dd>

        <dt>Comments about the system generally</dt>
        <dd>You can send a message to the system designers.  <logic:present role="patient">If you are logged in you can <html:link action="/patient/contact">click here to send a message.</html:link></logic:present><dd>

        <dt>FAQs</dt>
        <dd>
            <ul>
                <li><a href="#Anchor-How-lockedout">I've forgotten my login/ I'm locked out</a></li>
                <li><a href="#Anchor-How-49575">How does it work?</a></li>
                <li><a href="#Anchor-Who-47857">Who can read it?</a></li>
                <li><a href="#Anchor-Where-11481">Where has this come from?</a></li>
                <li><a href="#Anchor-How-35882">How do I join?</a></li>
                <li><a href="#Anchor-Unit-236258">My unit isn't using PatientView. How can I get it to join?</a></li>
                <li><a href="#Anchor-What-14210">What if I don't use the Internet?</a></li>
                <li><a href="#Anchor-What-23240">What does it cost?</a></li>
                <li><a href="#Anchor-What-3800">What if I move?</a></li>
                <li><a href="#Anchor-Who-35326">Who can see what?</a></li>
                <li><a href="#Anchor-Is-44867">Is it safe?  Security and confidentiality</a></li>
                <li><a href="#Anchor-How-33869">How can I change what is shown?</a></li>
                <li><a href="#Anchor-What-6296">What if I want to remove my information from the system?</a></li>
                <li><a href="#Anchor-What-further-info">Further information</a></li>
            </ul>
        </dd>

        <dt><a name="Anchor-How-lockedout"></a>I've forgotten my login/ I'm locked out</dt>
        <dd>If you have forgotten your password, you can reset it yourself if
            <ul>
                <li>You know your username, AND</li>
                <li>Your current email address is registered in the system</li>
            </ul>
            Click on Login, then on 'Forgotten password?'
            If you can't do that, you need to contact your local unit's RPV Admin.  There were details in your original Welcome to RPV letter<!--, or you can send a message by ... (tbc)-->.  In these circumstances your new login will usually have to be posted to your registered address.
        </dd>

        <dt><a name="Anchor-How-49575"></a>How does it work?</dt>
        <dd>PatientView takes information from your renal unit's computer system and links it to useful explanations about your kidney condition
            and its treatment. It shows blood test results, clinic letters (if they are on your unit's renal computer system), and much more.
            It is updated at least once a day so that it should be up to date. Because it shows information from your local unit's computer
            system, you should contact your local unit if you see (or suspect) errors. Technical information: Downloads for a patient are
            activated by a flag in the local information system. This is triggered by a local administrator after receiving your signed agreement.
        </dd>

        <dt><a name="Anchor-Who-47857"></a>Who can read it?</dt>
        <dd>Patients can, a patient's GP can, and a limited number of staff from the renal unit can.  Also, patients may share their password with anyone they want.  So it can be used to show information to relatives, friends, and to healthcare staff in other locations where renal notes are not available.  </dd>

        <dt><a name="Anchor-Where-11481"></a>Where has this come from?</dt>
        <dd>PatientView was developed by <a href="http://www.renal.org/rixg" target="_blank">RIXG</a>, the Renal Information EXchange Group.  This is a
            group representing patients and renal teams which was formed in 2003 to make advances in IT work to help renal patients. The
            PatientView pilot project began in 2004, funded by the Department of Health, and the full project was officially launched in 2005.
            It is officially hosted now by the UK Renal Association (www.renal.org)</dd>

        <dt><a name="Anchor-How-35882"></a>How do I join?</dt>
        <dd>Renal PatientView is only available at participating renal units, but that is now most units in the UK.  <a href="http://www.renal.org/rixg/units.html" target="_blank">Check on this list</a> if you don't know if yours has it, or is due to get it.
            To join, you sign the enrolment form after seeing examples or a demo.  A member of staff signs the form too.  It is then given to your local Renal PatientView administrator.  They will send starter information, and your username and password, to you and to your GP (your GP has a different login). You can also send your request to join by clicking on the 'I want to join' button at <a href="/">www.renalpatientview.org</a>. </dd>

        <dt><a name="Anchor-Unit-236258"></a>My unit isn't using PatientView. How can I get it to join?</dt>
        <dd>We hope that soon almost all units will be participating. In some units, changes to their local computer systems have delayed joining.  A few units, especially at children's hospitals, don't have computer-held records at all, and this has delayed them.</dd>

        <dt><a name="Anchor-What-14210"></a>What if I don't use the Internet?</dt>
            <dd>Maybe you have a friend or family member that does, and they could help you? Public libraries often have computers you could use.  We are also working to make RPV available through phones and possibly via digital TVs.  </dd>

        <dt><a name="Anchor-What-23240"></a>What does it cost?</dt>
            <dd>It is free to patients. There is a small annual cost to each unit to join the system.</dd>

        <dt><a name="Anchor-What-3800"></a>What if I move?</dt>
            <dd>If you move to a different unit, either permanently or temporarily (e.g. for a transplant), you can sign up in the new centre too, and your records will be combined. This can be very useful to staff in both centres, as well as to patients.</dd>

        <dt><a name="Anchor-Who-35326"></a>Who can see what?</dt>
            <dd>The information presented is the same for all users.  So staff can read patient information, and patients can read medical information.
                <ul>
                    <li>Patients see only their own results and other information
                    <li>GPs have a unique login for each of their PatientView-registered patients at present. So if they have multiple patients, they must login again to see another.
                    <li>Unit staff who have PatientView logins may see the records of any PatientView patient from their unit.
                </ul>
                <p>Patients may share their logins with relatives, staff in clinics that they are referred to, staff at other surgeries or foreign hospitals - or anyone else they choose.  They can change their password at any time to restrict access again.  </p>
                <p>Staff must not share their logins. </p>
            </dd>

        <dt><a name="Anchor-Is-44867"></a>Is it safe?  Security and confidentiality</dt>
            <dd>
                <p>We believe the system is very safe. It uses security systems like the ones used for online credit card payments. </p>
                <p>All information is encrypted during transmission. The web server is very secure. External review of the system's security is undertaken. A log of who has accessed your information is kept, and you can ask to see it.</p>
                <ul>
                    <li><strong>Patients</strong>:  The major risk is of someone else finding out or guessing your username/password.  If you think someone may have discovered your password, you can change it at any time. Your information only goes onto Renal PatientView at your request, and you can have it all removed from the system if you want.
                    <li><strong>Staff</strong>:  The major risk is of a username/password combination being guessed or discovered. You will be aware how sensitive some information could be, and it is essential that you keep your login details secret and safe.  Staff logins must not be shared.  The correct way to permit an external person to see a PatientView record is for the patient to share their username and password.
                </ul>
            </dd>

        <dt><a name="Anchor-How-33869"></a>How can I change what is shown?</dt>
        <dd>
            <p>
                PatientView shows mostly information that is recorded in other computer systems. To change it, changes need to be made at the place it is coming from. When a change is made, it may not be updated on the Renal PatientView server for up to 24h.  Changes to any of the last 10 items under each heading should be corrected this way.  Older information will require manual intervention by a local adminstrator.
                If you yourself have entered information that is incorrect, you should contact your local RPV Admin.
            </p>
        </dd>

        <dt><a name="Anchor-What-6296"></a>What if I want to remove my information from the system?</dt>
        <dd>That is fine. However as it may be impossible to recreate the valuable record that Renal PatientView builds up over time, we ask for a formal signed request before withdrawing patients from the system. If you are registered in more than one unit, you will need to withdraw from each unit. We are reviewing how we do this at present (2013), and may give more options soon.  </dd>

        <dt><a name="Anchor-What-further-info"></a>Further information</dt>
        <dd>
            <ul>
                <li><a href="http://www.renal.org/rpv" target="_blank">www.renal.org/rpv</a> has info about the development of RPV, and technical links</li>
                <li><a href="http://renalpatientview.blogspot.co.uk" target="_blank">renalpatientview.blogspot.co.uk</a> has News</li>
                <li><a href="http://www.rixg.org/rpv" target="_blank">www.rixg.org/rpv</a> has info about how to use RPV, including videos and a Recruitment Toolkit</li>
            </ul>
        </dd>
     </dl>
