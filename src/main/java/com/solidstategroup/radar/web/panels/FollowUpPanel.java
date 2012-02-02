package com.solidstategroup.radar.web.panels;


import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.panels.followup.ClinicalPicturePanel;
import com.solidstategroup.radar.web.panels.followup.FollowUpLaboratoryResultsPanel;
import com.solidstategroup.radar.web.panels.followup.FollowUpTreatmentPanel;
import com.solidstategroup.radar.web.panels.followup.RrtTherapyPanel;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class FollowUpPanel extends Panel {

    private ClinicalPicturePanel clinicalPicturePanel;
    private FollowUpLaboratoryResultsPanel laboratoryResults;
    private FollowUpTreatmentPanel treatmentPanel;
    private RrtTherapyPanel rrtTherapyPanel;

    private MarkupContainer linksContainer;

    public enum CurrentTab {
        CLINICAL_PICTURE, LABORATORY_RESULTS, TREATMENT, RRT_THERAPY
    }

    private CurrentTab currentTab = CurrentTab.CLINICAL_PICTURE;

    public FollowUpPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);

        linksContainer.add(new TabAjaxLink("clinicalPictureLink", CurrentTab.CLINICAL_PICTURE));
        linksContainer.add(new TabAjaxLink("laboratoryResultsLink", CurrentTab.LABORATORY_RESULTS));
        linksContainer.add(new TabAjaxLink("treatmentLink", CurrentTab.TREATMENT));
        linksContainer.add(new TabAjaxLink("rrtTherapyLink", CurrentTab.RRT_THERAPY));

        clinicalPicturePanel = new ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel);
        laboratoryResults = new FollowUpLaboratoryResultsPanel("laboratoryResultsPanel", radarNumberModel);
        treatmentPanel = new FollowUpTreatmentPanel("treatmentPanel", radarNumberModel);
        rrtTherapyPanel = new RrtTherapyPanel("rrtTherapyPanel", radarNumberModel);
        add(linksContainer, clinicalPicturePanel, laboratoryResults, treatmentPanel, rrtTherapyPanel);
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
        public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            currentTab = tab;
            ajaxRequestTarget.add(linksContainer, clinicalPicturePanel, laboratoryResults, treatmentPanel,
                    rrtTherapyPanel);
        }
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.FOLLOW_UP);
    }
}
