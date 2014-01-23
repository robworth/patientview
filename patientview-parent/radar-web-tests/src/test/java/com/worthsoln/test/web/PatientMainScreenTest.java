/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */
package com.worthsoln.test.web;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class PatientMainScreenTest extends BaseTest{


    public void testPatientMainScreen(String username, String password, String dob) {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("| Patients");

        assertFormElementPresent("username");
        assertFormElementPresent("password");
        assertFormElementPresent("dateOfBirth");
        setTextField("username", username);
        setTextField("password", password);
        setTextField("dateOfBirth", dob);
        submit();

        assertLinkPresentWithText("Demographics");     // we should now be logged in
        assertLinkPresentWithText("Diagnosis");
        assertLinkPresentWithText("First Visit");
        assertLinkPresentWithText("Follow Up");
        assertLinkPresentWithText("Pathology");
        assertLinkPresentWithText("Relapse");
        assertLinkPresentWithText("Hospitalisation");
    }

    @Ignore
    public void testSRNSPatientScreen() {
        testPatientMainScreen("patient3","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient3");
        assertSelectedOptionsEqual("renalUnit", new String[]{"SRNS"});

        testTabs();
    }

    @Ignore
    public void testALPORTPatientScreen() {
        testPatientMainScreen("patient4","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient4");
        assertSelectedOptionsEqual("renalUnit", new String[]{"Alport"});

        testTabs();
    }

    @Ignore
    public void testHNF1BPatientScreen() {
        testPatientMainScreen("patient5","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient5");
        assertSelectedOptionsEqual("renalUnit", new String[]{"HNF1B"});

        testTabs();
    }
    @Ignore
    public void testOthersPatientScreen() {
        testPatientMainScreen("patient6","pppppp","01-08-1985");

        assertTextPresent("Demographics");
        assertFormElementPresent("forenameForHeader");
        assertTextFieldEquals("forenameForHeader", "patient6");
        assertSelectedOptionsEqual("renalUnit", new String[]{"STECHUS"});

        testTabs();
    }

    public void testTabs() {

        clickLinkWithText("Diagnosis");
        assertTextNotPresent(error_message);
        clickLinkWithText("First Visit");
        assertTextNotPresent(error_message);
        clickLinkWithText("Follow Up");
        assertTextNotPresent(error_message);
        clickLinkWithText("Pathology");
        assertTextNotPresent(error_message);
        clickLinkWithText("Relapse");
        assertTextNotPresent(error_message);
        clickLinkWithText("Hospitalisation");
        assertTextNotPresent(error_message);
    }


}
