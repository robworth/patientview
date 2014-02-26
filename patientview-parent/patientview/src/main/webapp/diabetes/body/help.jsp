<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

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
    <h1>Diabetes Help Page</h1>
</div>

<p>
    Diabetes PatientView is an interactive website to help support people with diabetes.
</p>

<p>
    The website provides secure access to your blood tests, clinic letters and your diabetes. You'll find tailored information links, from accurate and reliable sources. We hope it will help you manage your condition more effectively.
</p>

<dl class="flush-dl">
    <dt>Questions about your health</dt>
    <dd>
        If you have any questions about your condition, you can contact us:  <html:link action="/patient/contact">Click here to send a message.</html:link> If you are unable
        to contact us and you feel you need urgent help, please contact your GP or attend your local Accident & Emergency
        department.
    </dd>

    <dt>Questions about your own information<dt>
    <dd>
        If you are a patient, and suspect that wrong information is being shown, you need to contact us here.
        <html:link action="/patient/contact">Click here to send a message.</html:link>
    </dd>

    <dt>Questions about the system </dt>
    <dd>
        First see our Frequently Asked Questions, listed below.
    </dd>

    <dt>Other questions</dt>
    <dd>
        For any queries about login problems, incorrect details on the site, content issues, results not appearing
        or being wrong, or any concerns or feedback, please contact us here.
        <html:link action="/patient/ibd-contact">Click here to send a message.</html:link>
    </dd>

    <dt>FAQs</dt>
    <dd>
        Non-patients reading these Qs should note that these Qs and As are mostly phrased for patients - where
        necessary, information for others is clearly separated.
        <ul>
            <li><a href="#Anchor-How-49575">How does it work?</a></li>
            <li><a href="#Anchor-Who-47857">Who can read it?</a></li>
            <li><a href="#Anchor-Where-11481">Where has this come from?</a></li>
            <li><a href="#Anchor-How-35882">How do I join?</a></li>
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
    <dd>
        <p>
            PatientView takes information from your hospital's and GP’s computer system and links it to useful explanations about
            your condition and its treatment. It shows blood test results, clinic letters (if they are on your hospital's computer
            system), and much more. It is updated at least once a day so that it should be up to date. Because it shows information
            from your local hospital's computer system, you should contact your diabetic clinic or GP if you see (or suspect) errors.
        </p>
        <p>
            Technical information: Data is automatically sent to the PatientView server, at least daily via a gateway PC. Downloads
            for a patient are activated by a flag in the local hospital information system. This is triggered by a local administrator
            after receiving the patient's signed agreement. The PatientView server accumulates data prospectively. Old results etc.
            are not transferred.
        </p>
    </dd>

    <dt><a name="Anchor-Who-47857"></a>Who can read it?</dt>
    <dd>
        Patients can, a patient's GP can, and a limited number of staff from the diabetes team can. Also, patients may share their
        password with anyone they want. So it can be used to show information to relatives, friends, and to healthcare staff in
        other locations where your notes are not available.
    </dd>

    <dt><a name="Anchor-Where-11481"></a>Where has this come from?</dt>
    <dd>
        Please remember that the information on this site is general advice - if you are having any specific problems please
        contact your GP or another healthcare professional to get help.
        We believe that the information provided from external websites contain useful and unbiased information. However, we
        cannot be held responsible for the accuracy of their content or the views of the authors.
    </dd>

    <dt><a name="Anchor-How-35882"></a>How do I join?</dt>
    <dd>
        Only some centres can offer this service at present. We will link a list from the front page.
    </dd>

    <dt><a name="Anchor-What-14210"></a>What if I don't use the Internet?</dt>
    <dd>
        Maybe you have a friend or family member that does? There is often internet access available in public libraries. Please
        ensure when using a public computer that you always safely logout and close the web browser.
    </dd>

    <dt><a name="Anchor-What-23240"></a>What does it cost?</dt>
    <dd>
        There is no cost it is free for patients.
    </dd>

    <dt><a name="Anchor-What-3800"></a>What if I move?</dt>
    <dd>
        We hope that more centres will join so that you can stay connected.
    </dd>

    <dt><a name="Anchor-Who-35326"></a>Who can see what?</dt>
    <dd>
        The information presented is the same for all users. So staff can read patient information, and patients can read
        medical information.
        <ul>
            <li>
                Patients see only their own results and other information.
            </li>
            <li>
                GPs have a unique login for each of their registered patients at present. So if they have multiple patients, they
                must login again to see another.
            </li>
            <li>
                Unit staff who have logins may see the records of any Diabetes PatientView patient from their unit.
            </li>
        </ul>
        <p>
            Patients may share their logins with relatives, staff in clinics that they are referred to, staff at other surgeries or foreign
            hospitals - or anyone else they choose. They can change their password at any time to restrict access again.
        </p>
    </dd>

    <dt><a name="Anchor-Is-44867"></a>Is it safe?  Security and confidentiality</dt>
        <dd>
            <p>
                We believe the system should be very safe. It uses security systems like the ones used for Internet shopping.
            </p>
            <p>
                All information is encrypted during transmission. The web server is very secure. No information can be written back
                into records kept in units. External review of the system's security is undertaken. A detailed log of who has accessed
                what information is kept.
            </p>
            <p>
                To maximise your security online, you should always click the 'logout' button in the top right of
                the screen. Once you have done this, close your web browser by clicking the 'x' in the top right
                corner.
            </p>
            <p>
                <strong>Patients</strong>:  The major risk is of someone else finding out or guessing your username/password. If you think someone
                may have discovered your password, you can change it at any time. Your information only goes onto PatientView at
                your request, and you can have it all removed from the system if you want.
            </p>
            <p>
                <strong>Staff</strong>:  The major risk is of a username/password combination being guessed or discovered. You will be aware how
                sensitive some information could be, and it is essential that you keep your login details secret and safe. Staff logins
                must not be shared. The correct way to permit an external person to see a PatientView record is for the patient to share
                their username and password.
            </p>
        </dd>

    <dt><a name="Anchor-How-33869"></a>How can I change what is shown?</dt>
    <dd>
        <p>
            The web server only shows information that is recorded in local computer systems. To change it, changes need to be
            made in the local diabetes records. When a change is made, it may not be updated on the PatientView for up to 48h.
        </p>
        <p>
            Where results or letters are updated after being initially created, the system automatically allows an overwrite by new
            information for up to 10 days. Later than that, manual intervention by a local administrator will be required.
        </p>
    </dd>

    <dt><a name="Anchor-What-6296"></a>What if I want to remove my information from the system?</dt>
    <dd>
        That is fine. However as it may be impossible to recreate the valuable record that PatientView builds up over time, we
        may ask for formal signed consent before withdrawing patients from the system.
    </dd>
 </dl>
