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
@ContextConfiguration(locations = "classpath:spring-context.xml")
public abstract class BaseTest {

    @Value("${base.url}")
    protected String baseUrl;

    @Value("${patient.username}")
    protected String patient_username;

    @Value("${patient.password}")
    protected String patient_password;

    @Value("${patient.dateOfBirth}")
    protected String dateOfBirth;

    @Value("${professional.username}")
    protected String professional_username;

    @Value("${professional.password}")
    protected String professional_password;

    @Value("${superadmin.username}")
    protected String superadmin_username;

    @Value("${superadmin.password}")
    protected String superadmin_password;

    @Value("${error.message}")
    protected String error_message;



    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);

    }

    protected void prepareAndBeginAt(String url) {
        beginAt(url);    // start

        WebClient webClient = ((HtmlUnitTestingEngineImpl)getTestingEngine()).getWebClient();
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }


}
