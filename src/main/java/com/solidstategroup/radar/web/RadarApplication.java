package com.solidstategroup.radar.web;

import com.solidstategroup.radar.web.pages.HomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class RadarApplication extends WebApplication {

    public static final String DATE_PATTERN = "dd-MMM-yyyy";

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
    }
}
