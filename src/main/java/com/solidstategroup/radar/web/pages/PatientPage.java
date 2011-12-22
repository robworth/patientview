package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.PatientDemographicsPanel;
import com.solidstategroup.radar.web.panels.PatientDiagnosisPanel;
import com.solidstategroup.radar.web.panels.PatientPathologyPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public class PatientPage extends BasePage {

    public enum CurrentTab {
        // Todo: Use this for storing current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private PatientDemographicsPanel patientDemographicsPanel;
    private PatientDiagnosisPanel patientDiagnosisPanel;
    private PatientPathologyPanel patientPathologyPanel;
    private CurrentTab currentTab = CurrentTab.DEMOGRAPHICS;

    public PatientPage() {

        // Construct panels for each of the tabs
        patientDemographicsPanel = new PatientDemographicsPanel("demographicsPanel");
        patientDiagnosisPanel = new PatientDiagnosisPanel("diagnosisPanel");
        patientPathologyPanel = new PatientPathologyPanel("pathologyPanel");

        // Add them all to the page
        add(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);

        // Add the links to switch tab
        add(new AjaxLink("demographicsLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                currentTab = CurrentTab.DEMOGRAPHICS;
                target.add(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);
            }
        });
        add(new AjaxLink("diagnosisLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                currentTab = CurrentTab.DIAGNOSIS;
                target.add(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);
            }
        });
        add(new AjaxLink("pathologyLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                currentTab = CurrentTab.PATHOLOGY;
                target.add(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);
            }
        });
    }

    public CurrentTab getCurrentTab() {
        return currentTab;
    }
}
