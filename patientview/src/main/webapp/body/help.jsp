<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:xhtml/>


<div class="page-header">
    <h1>Renal PatientView Help page</h1>
</div>
 <dl class="flush-dl">
          <dt>Questions about your own information<dt>
            <dd>If you are a patient, and suspect that wrong information is being shown, you need to contact your local Renal PatientView administrator.  <html:link action="/patient/contact">Click here to send a message.</html:link>  </dd>
        <dt>Questions about the system </dt>
        <dd>First see our Frequently Asked Questions, listed below.  </dd>
        <dt>Other questions</dt>
        <dd>The first place to contact is usually your local Renal PatientView administrator. <html:link action="/patient/contact">Click here to send a message.</html:link></dd>
        <dt>Comments about the system generally</dt>
        <dd>You can send a message to the system designers.  <u>Click here to send a message</u><dd>
        <dt>FAQs</dt>
        <dd>Non-patients reading these Qs should not that these Qs and As are mostly phrased for patients - where necessary, information for others is clearly separated.
            <ul>
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
            </ul>
        </dd>
        <dt><a name="Anchor-How-49575"></a>How does it work?</dt>
        <dd>Renal PatientView takes information from your renal unit's computer system and links it to useful explanations about your kidney condition and its treatment.  It shows blood test results, clinic letters (if they are on your unit's renal computer system), and much more. It is updated at least once a day so that it should be up to date.  Because it shows information from your local unit's computer system, you should contact your local unit if you see (or suspect) errors.
            Technical information:  Data is automatically sent to the PatientView server at least daily via a gateway PC. Downloads for a patient are activated by a flag in the local information system.  This is triggered by a local administrator after receiving the patient's signed agreement. The PatientView server accumulates data prospectively.  Old results etc. are not transferred.  </dd>
        <dt><a name="Anchor-Who-47857"></a>Who can read it?</dt>
        <dd>Patients can, a patient's GP can, and a limited number of staff from the renal unit can.  Also, patients may share their password with anyone they want.  So it can be used to show information to relatives, friends, and to healthcare staff in other locations where renal notes are not available.  </dd>
        <dt><a name="Anchor-Where-11481"></a>Where has this come from?</dt>
        <dd>Renal PatientView has been produced by <a href="http://www.renal.org/rixg">RIXG</a>, the Renal Information EXchange Group.  This is a group representing patients and renal teams which was formed in 2003 to make advances in IT work to help renal patients.  The PatientView pilot project began in 2004, funded by the Department of Health.</dd>
        <dt><a name="Anchor-How-35882"></a>How do I join?</dt>
        <dd>Renal PatientView is only available at participating renal units, but we hope that the number will grow quickly.  <a href="http://www.renal.org/rixg/units.html">Check on this list</a> if you don't know if yours has it, or is due to get it.
            To join, you sign the enrolment form after seeing examples or a demo.  A member of staff signs the form too.  It is then given to your local Renal PatientView administrator.  They will send starter information, and your username and password, to you and to your GP (your GP has a different login).</dd>
        <dt><a name="Anchor-Unit-236258"></a>My unit isn't using PatientView. How can I get it to join?</dt>
        <dd>We are progressively making Renal PatientView available to more units, and we hope that eventually almost all units that want to will be able to join. How easy it is to join depends on the information system used by your unit, and on the availability of staff to enrol and maintain users.
            At first the system was set up to link to a computer system called Proton, which is the most widely used renal information system in the UK. Linking each new type of non-Proton system means some extra work, but we hope that this will be possible soon too.</dd>
        <dt><a name="Anchor-What-14210"></a>What if I don't use the Internet?</dt>
            <dd>Maybe you have a friend or family member that does?  Some units may have set up a way of using the system when you visit.</dd>
        <dt><a name="Anchor-What-23240"></a>What does it cost?</dt>
            <dd>It is free to patients.  There is a cost (usually small) to each unit to join the system.</dd>
        <dt><a name="Anchor-What-3800"></a>What if I move?</dt>
            <dd>If a patient moves to a different unit, either permanently or temporarily (e.g. for transplantation), they can sign up in the new centre too, and their records will be combined.  This should be very useful to the staff in both centres, as at present, there is no way of combining electronic records.</dd>
        <dt><a name="Anchor-Who-35326"></a>Who can see what?</dt>
            <dd>The information presented is the same for all users.  So staff can read patient information, and patients can read medical information.
                <ul>
                    <li>Patients see only their own results and other information
                    <li>GPs have a unique login for each of their PatientView-registered patients at present. So if they have multiple patients, they must login again to see another.
                    <li>Unit staff who have PatientView logins may see the records of any PatientView patient from their unit.
                </ul>
                <p>Patients may share their logins with relatives, staff in clinics that they are referred to, staff at other surgeries or foreign hospitals - or anyone else they choose.  They can change their password at any time to restrict access again.  </p>
            </dd>                                                                                                                                                                                                                                                             
        <dt><a name="Anchor-Is-44867"></a>Is it safe?  Security and confidentiality</dt>
            <dd>
                <p>We believe the system should be very safe.  It uses security systems like the ones used for Internet shopping. </p>
                <p>All information is encrypted during transmission.  The web server is very secure.  No information can be written back into records kept in units.  External review of the system's security is undertaken. A detailed log of who has accessed what information will be kept.</p>
                <blockquote>
                    <p><strong>Patients</strong>:  The major risk is of someone else finding out or guessing your username/password.  If you think someone may have discovered your password, you can change it at any time. Your information only goes onto Renal PatientView at your request, and you can have it all removed from the system if you want.    </p>
                </blockquote>
                <blockquote>
                    <p><strong>Staff</strong>:  The major risk is of a username/password combination being guessed or discovered. You will be aware how sensitive some information could be, and it is essential that you keep your login details secret and safe.  Staff logins must not be shared.  The correct way to permit an external person to see a PatientView record is for the patient to share their username and password.</p>
                </blockquote>
            </dd>

        <dt><a name="Anchor-How-33869"></a>How can I change what is shown?</dt>
        <dd>
            <p>
                The web server only shows information that is recorded in the unit's local computer system.  To change it, changes need to be made in the unit's renal database system as usual.  When a change is made, it may not be updated on the Renal PatientView server for up to 24h. </p>
                <p>Where results or letters are updated after being initially created, the system automatically allows an overwrite by new information for up to 10 days.  Later than that, manual intervention by a local administrator will be required.</p>
        </dd>
        <dt><a name="Anchor-What-6296"></a>What if I want to remove my information from the system?</dt>
        <dd>That is fine.  However as it may be impossible to recreate the valuable record that Renal PatientView builds up over time, we ask for formal signed consent before withdrawing patients from the system.  If you are registered in more than one unit, you will need to withdraw from each unit.  </dd>
     </dl>