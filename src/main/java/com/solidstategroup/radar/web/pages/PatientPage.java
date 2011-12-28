package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.PatientDemographicsPanel;
import com.solidstategroup.radar.web.panels.PatientDiagnosisPanel;
import com.solidstategroup.radar.web.panels.PatientHospitalisationPanel;
import com.solidstategroup.radar.web.panels.PatientPathologyPanel;
import com.solidstategroup.radar.web.panels.PatientRelapsePanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public class PatientPage extends BasePage {


    public enum CurrentTab {
        // Todo: Use this for storing current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private PatientDemographicsPanel demographicsPanel;
    private PatientDiagnosisPanel diagnosisPanel;
    private PatientPathologyPanel pathologyPanel;
    private PatientRelapsePanel relapsePanel;
    private PatientHospitalisationPanel hospitalisationPanel;

    private CurrentTab currentTab = CurrentTab.DEMOGRAPHICS;

    public PatientPage() {

        // Construct panels for each of the tabs
        demographicsPanel = new PatientDemographicsPanel("demographicsPanel");
        diagnosisPanel = new PatientDiagnosisPanel("diagnosisPanel");
        pathologyPanel = new PatientPathologyPanel("pathologyPanel");
        relapsePanel = new PatientRelapsePanel("relapsePanel");
        hospitalisationPanel = new PatientHospitalisationPanel("hospitalisationPanel");

        // Add them all to the page
        add(demographicsPanel, diagnosisPanel, pathologyPanel, relapsePanel, hospitalisationPanel);

        // Add the links to switch tab
        add(new TabAjaxLink("demographicsLink", CurrentTab.DEMOGRAPHICS));
        add(new TabAjaxLink("diagnosisLink", CurrentTab.DIAGNOSIS));
        add(new TabAjaxLink("pathologyLink", CurrentTab.PATHOLOGY));
        add(new TabAjaxLink("relapseLink", CurrentTab.RELAPSE));
        add(new TabAjaxLink("hospitalisationLink", CurrentTab.HOSPITALISATION));
    }

    public CurrentTab getCurrentTab() {
        return currentTab;
    }

    private class TabAjaxLink extends AjaxLink {

        private CurrentTab tab;

        public TabAjaxLink(String id, CurrentTab tab) {
            super(id);
            this.tab = tab;
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            currentTab = tab;
            target.add(demographicsPanel, diagnosisPanel, pathologyPanel, relapsePanel, hospitalisationPanel);
        }
    }
}
