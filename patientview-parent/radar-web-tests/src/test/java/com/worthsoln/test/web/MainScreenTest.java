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

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MainScreenTest extends BaseTest{

    @Test
    public void testProfessionalsMainScreen() {
        prepareAndBeginAt("/");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");
        clickLinkWithText("Professionals");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", professional_username);
        setTextField("password", professional_password);
        submit();

        assertLinkPresentWithText("| Log-out");     // we should now be logged in
        assertLinkPresentWithText("| Enter New Patient");
        assertLinkNotPresentWithText("Modify Patients");
    }

    @Test
    public void testProfessionalsAddPatient() {
        testProfessionalsMainScreen();
        clickLinkWithText("| Enter New Patient");
        assertTextPresent("New Patient");

        assertFormElementPresent("patientId");
        setTextField("patientId", "6810341560");

        assertFormElementPresent("nhsNumberType");
        selectOption("nhsNumberType", "NHS");

        assertFormElementPresent("diseaseGroup");
        selectOptionByValue("diseaseGroup", "ARPKD");

        clickButtonWithText("Add Patient");
        assertTextPresent("Demographics");
        assertTextPresent("Disease Group: ARPKD - autosomal recessive polycystic kidney disease");
    }

    @Test
    public void testProfessionalsViewPatient() {
        testProfessionalsMainScreen();

        clickLinkWithText("Existing Patients");
        getTestingEngine().clickLink("patientsListingPageLink");
        assertTextPresent("View Patient Details");

    }
}
