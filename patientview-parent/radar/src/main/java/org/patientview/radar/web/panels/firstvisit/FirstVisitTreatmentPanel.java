package org.patientview.radar.web.panels.firstvisit;

import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.FirstVisitPanel;
import org.patientview.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FirstVisitTreatmentPanel extends Panel {
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    public FirstVisitTreatmentPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
        // General details
        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        add(radarNumber);

        add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                demographicsManager)));

        add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisManager), "abbreviation")));

        add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel, demographicsManager)));
        add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsManager)));
        add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsManager),
                RadarApplication.DATE_PATTERN));

        add(new TreatmentPanel("treatmentPanel", radarNumberModel, true, null, null));
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.TREATMENT);
    }
}
