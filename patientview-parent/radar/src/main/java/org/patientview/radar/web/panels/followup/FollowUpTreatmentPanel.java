package org.patientview.radar.web.panels.followup;

import org.patientview.radar.model.sequenced.Therapy;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.TherapyManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.FollowUpPanel;
import org.patientview.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FollowUpTreatmentPanel extends Panel {
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private TherapyManager therapyManager;

    public FollowUpTreatmentPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // General details
        final WebMarkupContainer treatmentContainer = new WebMarkupContainer("treatmentContainer");
        treatmentContainer.setVisible(false);
        treatmentContainer.setOutputMarkupPlaceholderTag(true);
        treatmentContainer.setOutputMarkupId(true);
        add(treatmentContainer);

        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        treatmentContainer.add(radarNumber);

        treatmentContainer.add(new TextField("hospitalNumber", RadarModelFactory.
                getHospitalNumberModel(radarNumberModel, demographicsManager)));

        treatmentContainer.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.
                getDiagnosisCodeModel(radarNumberModel, diagnosisManager), "abbreviation")));

        treatmentContainer.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                demographicsManager)));
        treatmentContainer.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel,
                demographicsManager)));
        treatmentContainer.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel,
                demographicsManager), RadarApplication.DATE_PATTERN));


        final IModel<Therapy> followUpModel = new Model<Therapy>(new Therapy());

        IModel<List> therapiesListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = therapyManager.getTherapyByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice<Therapy> treatmentSwitcher = new DropDownChoice("treatmentSwitcher", followUpModel,
                therapiesListModel, new DateChoiceRenderer("treatmentRecordDate", "id") {

            @Override
            protected Date getDate(Object object) {
                return ((Therapy) object).getTreatmentRecordDate();
            }
        });
        add(treatmentSwitcher);

        final TreatmentPanel treatmentPanel = new TreatmentPanel("treatmentPanel", radarNumberModel, false,
                followUpModel,
                Arrays.<Component>asList(treatmentSwitcher));
        treatmentPanel.setVisible(false);
        add(treatmentPanel);
        treatmentPanel.setOutputMarkupId(true);
        treatmentPanel.setOutputMarkupPlaceholderTag(true);

        treatmentSwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                treatmentContainer.setVisible(true);
                treatmentPanel.setVisible(true);
                target.add(treatmentContainer);
                target.add(treatmentPanel);
            }
        });

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                followUpModel.setObject(new Therapy());
                treatmentContainer.setVisible(true);
                treatmentPanel.setVisible(true);
                treatmentSwitcher.clearInput();
                target.add(treatmentContainer);
                target.add(treatmentPanel);
                target.add(treatmentSwitcher);
            }
        };

        add(addNew);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.TREATMENT);
    }
}
