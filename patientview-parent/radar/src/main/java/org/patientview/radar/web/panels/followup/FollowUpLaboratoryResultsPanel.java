package org.patientview.radar.web.panels.followup;

import org.patientview.radar.model.sequenced.LabData;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.LabDataManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.FollowUpPanel;
import org.patientview.radar.web.panels.subtabs.LaboratoryResultsPanel;
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

public class FollowUpLaboratoryResultsPanel extends Panel {
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private LabDataManager labDataManager;

    public FollowUpLaboratoryResultsPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final WebMarkupContainer labResultsContainer = new WebMarkupContainer("labResultsContainer");
        labResultsContainer.setVisible(false);
        labResultsContainer.setOutputMarkupPlaceholderTag(true);
        labResultsContainer.setOutputMarkupId(true);

        // General details
        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        labResultsContainer.add(radarNumber);

        labResultsContainer.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(
                radarNumberModel, demographicsManager)));

        labResultsContainer.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.
                getDiagnosisCodeModel(radarNumberModel, diagnosisManager), "abbreviation")));

        labResultsContainer.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                demographicsManager)));
        labResultsContainer.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel,
                demographicsManager)));
        labResultsContainer.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel,
                demographicsManager), RadarApplication.DATE_PATTERN));

        final IModel<LabData> followUpModel = new Model<LabData>(new LabData());

        IModel<List> labResultsListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = labDataManager.getLabDataByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice<LabData> labResultsSwitcher = new DropDownChoice("labResultsSwitcher", followUpModel,
                labResultsListModel, new DateChoiceRenderer("date", "id"){
            @Override
            protected Date getDate(Object object) {
                return ((LabData) object).getDate();
            }
        });

        final LaboratoryResultsPanel formContainer = new LaboratoryResultsPanel("formContainer", radarNumberModel,
                false, followUpModel, Arrays.<Component>asList(labResultsSwitcher));

        labResultsContainer.add(formContainer);
        add(labResultsContainer);

        labResultsSwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                labResultsContainer.setVisible(true);
                target.add(labResultsContainer);
            }
        });

        add(labResultsSwitcher);

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                followUpModel.setObject(new LabData());
                labResultsContainer.setVisible(true);
                labResultsSwitcher.clearInput();
                target.add(labResultsContainer, labResultsSwitcher);
            }
        };

        add(addNew);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.LABORATORY_RESULTS);

    }
}
