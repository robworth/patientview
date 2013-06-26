package org.patientview.radar.web.panels;


import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.models.PageNumberModel;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.patientview.radar.web.panels.followup.ClinicalPicturePanel;
import org.patientview.radar.web.panels.followup.FollowUpLaboratoryResultsPanel;
import org.patientview.radar.web.panels.followup.FollowUpTreatmentPanel;
import org.patientview.radar.web.panels.followup.RrtTherapyPanel;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
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
        CLINICAL_PICTURE(RadarApplication.CLINICAL_FOLLOW_UP_PAGE_NO),
        LABORATORY_RESULTS(RadarApplication.LABORATORY_FOLLOW_UP_PAGE_NO),
        TREATMENT(RadarApplication.TREATMENT_FOLLOW_UP_PAGE_NO),
        RRT_THERAPY(RadarApplication.RRT_THERAPY_PAGE_NO);

        private int pageNumber;

        CurrentTab(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageNumber() {
            return pageNumber;
        }
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
        public void onClick(AjaxRequestTarget target) {
            currentTab = tab;
            target.add(linksContainer, clinicalPicturePanel, laboratoryResults, treatmentPanel,
                    rrtTherapyPanel);

            Component pageNumber = getPage().get("pageNumber");
            PageNumberModel pageNumberModel = (PageNumberModel) pageNumber.getDefaultModel();
            pageNumberModel.setPageNumber(currentTab.getPageNumber());
            target.add(pageNumber);
        }

        @Override
        protected IAjaxCallDecorator getAjaxCallDecorator() {
            return RadarBehaviourFactory.getWarningOnFormExitCallDecorator();
        }
    }

    @Override
    public boolean isVisible() {
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.FOLLOW_UP);
    }

}
