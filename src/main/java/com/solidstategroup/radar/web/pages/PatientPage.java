package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.PatientDemographicsPanel;

public class PatientPage extends BasePage {

    public PatientPage() {
        add(new PatientDemographicsPanel("content"));
    }
}
