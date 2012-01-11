package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.web.pages.PatientPage;
import com.solidstategroup.radar.web.panels.firstvisit.ClinicalPicturePanel;
import com.solidstategroup.radar.web.panels.firstvisit.FirstVisitLaboratoryResultsPanel;
import com.solidstategroup.radar.web.panels.firstvisit.FirstVisitTreatmentPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FirstVisitPanel extends Panel {

    private ClinicalPicturePanel clinicalPicturePanel;
    private FirstVisitLaboratoryResultsPanel laboratoryResults;
    private FirstVisitTreatmentPanel treatmentPanel;

    public enum CurrentTab {
        CLINICAL_PICTURE, LABORATORY_RESULTS, TREATMENT
    }

    private CurrentTab currentTab = CurrentTab.CLINICAL_PICTURE;

    public FirstVisitPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TabAjaxLink("clinicalPictureLink", CurrentTab.CLINICAL_PICTURE));
        add(new TabAjaxLink("laboratoryResultsLink", CurrentTab.LABORATORY_RESULTS));
        add(new TabAjaxLink("treatmentLink", CurrentTab.TREATMENT));

        clinicalPicturePanel = new ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel);
        laboratoryResults = new FirstVisitLaboratoryResultsPanel("laboratoryResultsPanel", radarNumberModel);
        treatmentPanel = new FirstVisitTreatmentPanel("treatmentPanel", radarNumberModel);
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
