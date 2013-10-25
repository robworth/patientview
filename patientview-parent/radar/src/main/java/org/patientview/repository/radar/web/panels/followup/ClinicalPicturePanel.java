package org.patientview.repository.radar.web.panels.followup;

import org.patientview.repository.radar.service.ClinicalDataManager;
import org.patientview.repository.radar.service.DemographicsManager;
import org.patientview.repository.radar.service.DiagnosisManager;
import org.patientview.repository.radar.web.panels.FollowUpPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ClinicalPicturePanel extends Panel {

    @SpringBean
    private ClinicalDataManager clinicalDataManager;
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    public ClinicalPicturePanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
        Panel clinicalPicturePanel = new org.patientview.repository.radar.web.panels.firstvisit.
                ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel, false);
        add(clinicalPicturePanel);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.CLINICAL_PICTURE);
    }

}
