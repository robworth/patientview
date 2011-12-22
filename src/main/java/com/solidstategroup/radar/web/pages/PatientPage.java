package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.PatientDemographicsPanel;
import com.solidstategroup.radar.web.panels.PatientDiagnosisPanel;
import com.solidstategroup.radar.web.panels.PatientPathologyPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Arrays;
import java.util.List;

public class PatientPage extends BasePage {

    private enum CurrentTab {
        // Todo: Use this for storing current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private PatientDemographicsPanel patientDemographicsPanel;
    private PatientDiagnosisPanel patientDiagnosisPanel;
    private PatientPathologyPanel patientPathologyPanel;

    public PatientPage() {

        // Construct panels for each of the tabs
        patientDemographicsPanel = new PatientDemographicsPanel("demographicsPanel");
        patientDiagnosisPanel = new PatientDiagnosisPanel("diagnosisPanel");
        patientPathologyPanel = new PatientPathologyPanel("pathologyPanel");

        // Hide them all but demographics
        hideAllPanels();
        patientDemographicsPanel.setVisible(true);

        // Add them all to the page
        add(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);

        // Add the links to switch tab
        add(new AjaxLink("demographicsLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hideAllPanels();
                patientDemographicsPanel.setVisible(true);
                for (Panel panel : getPanels()) {
                    target.add(panel);
                }
            }
        });
        add(new AjaxLink("diagnosisLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hideAllPanels();
                patientDiagnosisPanel.setVisible(true);
                for (Panel panel : getPanels()) {
                    target.add(panel);
                }
            }
        });
        add(new AjaxLink("pathologyLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hideAllPanels();
                patientPathologyPanel.setVisible(true);
                for (Panel panel : getPanels()) {
                    target.add(panel);
                }
            }
        });
    }

    private void hideAllPanels() {
        for (Panel panel : getPanels()) {
            panel.setVisible(false);
        }
    }

    private List<Panel> getPanels() {
        return Arrays.asList(patientDemographicsPanel, patientDiagnosisPanel, patientPathologyPanel);
    }
}
