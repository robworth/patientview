package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.web.panels.DemographicsPanel;
import com.solidstategroup.radar.web.panels.DiagnosisPanel;
import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.HospitalisationPanel;
import com.solidstategroup.radar.web.panels.PathologyPanel;
import com.solidstategroup.radar.web.panels.RelapsePanel;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class PatientPage extends BasePage {

    public enum CurrentTab {
        // Used for storing the current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private DemographicsPanel demographicsPanel;
    private DiagnosisPanel diagnosisPanel;
    private FirstVisitPanel firstVisitPanel;
    private FollowUpPanel followUpPanel;
    private PathologyPanel pathologyPanel;
    private RelapsePanel relapsePanel;
    private HospitalisationPanel hospitalisationPanel;

    private MarkupContainer linksContainer;

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

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);

        // Add the links to switch tab
        linksContainer.add(new TabAjaxLink("demographicsLink", CurrentTab.DEMOGRAPHICS));
        linksContainer.add(new TabAjaxLink("diagnosisLink", CurrentTab.DIAGNOSIS));
        linksContainer.add(new TabAjaxLink("firstVisitLink", CurrentTab.FIRST_VISIT));
        linksContainer.add(new TabAjaxLink("followUpLink", CurrentTab.FOLLOW_UP));
        linksContainer.add(new TabAjaxLink("pathologyLink", CurrentTab.PATHOLOGY));
        linksContainer.add(new TabAjaxLink("relapseLink", CurrentTab.RELAPSE));
        linksContainer.add(new TabAjaxLink("hospitalisationLink", CurrentTab.HOSPITALISATION));
        add(linksContainer);
    }

    public CurrentTab getCurrentTab() {
        return currentTab;
    }

    private class TabAjaxLink extends AjaxLink {
        private CurrentTab tab;

        public TabAjaxLink(String id, CurrentTab tab) {
            super(id);
            this.tab = tab;

            // Decorate span with class="hovered" if we're active tab
            MarkupContainer span = new WebMarkupContainer("span");
            span.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return currentTab.equals(TabAjaxLink.this.tab) ? "hovered" : "";
                }
            }));
            add(span);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            currentTab = tab;
            // Add the links container to update hover class
            target.add(linksContainer);
            target.add(demographicsPanel, diagnosisPanel, firstVisitPanel, followUpPanel, pathologyPanel, relapsePanel,
                    hospitalisationPanel);
        }
    }
}
