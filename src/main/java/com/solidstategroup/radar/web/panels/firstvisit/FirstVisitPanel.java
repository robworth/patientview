package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

public class FirstVisitPanel extends Panel {

    private ClinicalPicturePanel clinicalPicturePanel;
    private LaboratoryResultsPanel laboratoryResults;
    private TreatmentPanel treatmentPanel;

    public enum CurrentTab {
        CLINICAL_PICTURE, LABORATORY_RESULTS, TREATMENT
    }

    private CurrentTab currentTab = CurrentTab.CLINICAL_PICTURE;

    public FirstVisitPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TabAjaxLink("clinicalPictureLink", CurrentTab.CLINICAL_PICTURE));
        add(new TabAjaxLink("laboratoryResultsLink", CurrentTab.LABORATORY_RESULTS));
        add(new TabAjaxLink("treatmentLink", CurrentTab.TREATMENT));

        clinicalPicturePanel = new ClinicalPicturePanel("clinicalPicturePanel");
        laboratoryResults = new LaboratoryResultsPanel("laboratoryResultsPanel");
        treatmentPanel = new TreatmentPanel("treatmentPanel");
        add(clinicalPicturePanel, laboratoryResults, treatmentPanel);
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
        public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            currentTab = tab;
            ajaxRequestTarget.add(clinicalPicturePanel, laboratoryResults, treatmentPanel);
        }
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.FIRST_VISIT);
    }
}
