package org.patientview.radar.web.panels;

import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.models.PageNumberModel;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.patientview.radar.web.panels.firstvisit.ClinicalPicturePanel;
import org.patientview.radar.web.panels.firstvisit.FirstVisitLaboratoryResultsPanel;
import org.patientview.radar.web.panels.firstvisit.FirstVisitTreatmentPanel;
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

public class FirstVisitPanel extends Panel {

    private ClinicalPicturePanel clinicalPicturePanel;
    private FirstVisitLaboratoryResultsPanel laboratoryResults;
    private FirstVisitTreatmentPanel treatmentPanel;
    private WebMarkupContainer linksContainer;

    public enum CurrentTab {
        CLINICAL_PICTURE(RadarApplication.CLINICAL_FIRST_VISIT_PAGE_NO),
        LABORATORY_RESULTS(RadarApplication.LABORATORY_FIRST_VISIT_PAGE_NO),
        TREATMENT(RadarApplication.TREATMENT_FIRST_VISIT_PAGE_NO),
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

    public FirstVisitPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);
        linksContainer.setOutputMarkupPlaceholderTag(true);

        linksContainer.add(new TabAjaxLink("clinicalPictureLink", CurrentTab.CLINICAL_PICTURE));
        linksContainer.add(new TabAjaxLink("laboratoryResultsLink", CurrentTab.LABORATORY_RESULTS));
        linksContainer.add(new TabAjaxLink("treatmentLink", CurrentTab.TREATMENT));

        clinicalPicturePanel = new ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel, true);
        laboratoryResults = new FirstVisitLaboratoryResultsPanel("laboratoryResultsPanel", radarNumberModel);
        treatmentPanel = new FirstVisitTreatmentPanel("treatmentPanel", radarNumberModel);

        add(linksContainer, clinicalPicturePanel, laboratoryResults, treatmentPanel);
    }

    public CurrentTab getCurrentTab() {
        return currentTab;
    }


    private class TabAjaxLink extends AjaxLink {
        private CurrentTab tab;

        public TabAjaxLink(String id, CurrentTab tab) {
            super(id);
            this.tab = tab;
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
            target.add(clinicalPicturePanel, laboratoryResults, treatmentPanel);
            target.add(linksContainer);

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
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.FIRST_VISIT);
    }


}
