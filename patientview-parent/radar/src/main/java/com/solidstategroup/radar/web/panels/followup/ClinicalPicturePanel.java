package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.service.ClinicalDataManager;
import com.solidstategroup.radar.service.DemographicsManager;
import com.solidstategroup.radar.service.DiagnosisManager;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
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
        Panel clinicalPicturePanel = new com.solidstategroup.radar.web.panels.firstvisit.
                ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel, false);
        add(clinicalPicturePanel);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.CLINICAL_PICTURE);
    }

}
