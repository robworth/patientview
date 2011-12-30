package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.DemographicsPanel;
import com.solidstategroup.radar.web.panels.DiagnosisPanel;
import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.HospitalisationPanel;
import com.solidstategroup.radar.web.panels.PathologyPanel;
import com.solidstategroup.radar.web.panels.RelapsePanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

public class PatientPage extends BasePage {


    public enum CurrentTab {
        // Todo: Use this for storing current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private DemographicsPanel demographicsPanel;
    private DiagnosisPanel diagnosisPanel;
    private FirstVisitPanel firstVisitPanel;
    private FollowUpPanel followUpPanel;
    private PathologyPanel pathologyPanel;
    private RelapsePanel relapsePanel;
    private HospitalisationPanel hospitalisationPanel;

    private CurrentTab currentTab = CurrentTab.DEMOGRAPHICS;

    public PatientPage() {

        // Construct panels for each of the tabs
        demographicsPanel = new DemographicsPanel("demographicsPanel");
        diagnosisPanel = new DiagnosisPanel("diagnosisPanel");
        firstVisitPanel = new FirstVisitPanel("firstVisitPanel");
        followUpPanel = new FollowUpPanel("followUpPanel");
        pathologyPanel = new PathologyPanel("pathologyPanel");
        relapsePanel = new RelapsePanel("relapsePanel");
        hospitalisationPanel = new HospitalisationPanel("hospitalisationPanel");

        // Add them all to the page
        add(demographicsPanel, diagnosisPanel, firstVisitPanel, followUpPanel, pathologyPanel, relapsePanel,
                hospitalisationPanel);

        // Add the links to switch tab
        add(new TabAjaxLink("demographicsLink", CurrentTab.DEMOGRAPHICS));
        add(new TabAjaxLink("diagnosisLink", CurrentTab.DIAGNOSIS));
        add(new TabAjaxLink("firstVisitLink", CurrentTab.FIRST_VISIT));
        add(new TabAjaxLink("followUpLink", CurrentTab.FOLLOW_UP));
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
            target.add(demographicsPanel, diagnosisPanel, firstVisitPanel, followUpPanel, pathologyPanel, relapsePanel,
                    hospitalisationPanel);
        }
    }
}
