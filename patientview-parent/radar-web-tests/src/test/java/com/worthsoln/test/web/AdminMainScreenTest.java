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
public class AdminMainScreenTest extends BaseTest{

    @Test
    public void testAdminMainScreen() {
        prepareAndBeginAt("/admins");

        assertTextPresent("Log In");
        assertTitleEquals("RaDaR - National Renal Rare Disease Registry");

        assertFormElementPresent("email");
        assertFormElementPresent("password");
        setTextField("email", superadmin_username);
        setTextField("password", superadmin_password);
        submit();

        assertLinkPresentWithText("| Log-out");     // we should now be logged in
        assertTextPresent("Logged In");
        assertLinkPresentWithText("Users");
        assertLinkPresentWithText("Consultants");
        assertLinkPresentWithText("Patients (All)");
        assertLinkPresentWithText("Patients (User)");
        assertLinkPresentWithText("Issues");

    }

    @Ignore
    public void testUsersTab() {
        testAdminMainScreen();
        clickLinkWithText("Users");
        assertTextPresent("Admin - Users");
        assertButtonPresentWithText("Add a new User");
    }

    @Test
    public void testConsultantsTab() {
        testAdminMainScreen();
        clickLinkWithText("Consultants");
        assertTextPresent("Admin - Consultants");
        assertButtonPresentWithText("Add a new Consultant");
    }

    @Ignore
    public void testPatientsAllTab() {
        testAdminMainScreen();
        clickLinkWithText("Patients (All)");
        assertTextPresent("Admin - Patients (All)");
        assertButtonPresentWithText("Export to pdf");
    }

    @Test
    public void testPatientsUserTab() {
        testAdminMainScreen();
        clickLinkWithText("Patients (User)");
        assertTextPresent("Admin - Patients (Users)");
        assertButtonPresentWithText("Export to pdf");
    }

    @Test
    public void testIssuesTab() {
        testAdminMainScreen();
        clickLinkWithText("Issues");
        assertTextPresent("Admin - Issues");
        assertButtonPresentWithText("Add a new Issue");
    }
}
