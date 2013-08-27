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



    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl(baseUrl);

    }

    protected void prepareAndBeginAt(String url) {
        beginAt(url);    // start

        WebClient webClient = ((HtmlUnitTestingEngineImpl)getTestingEngine()).getWebClient();
//        webClient.setJavaScriptEnabled(true);
//        webClient.setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//        webClient.setTimeout(35000);
//        webClient.setThrowExceptionOnScriptError(false);
//        webClient.waitForBackgroundJavaScript(35000);
    }


}
