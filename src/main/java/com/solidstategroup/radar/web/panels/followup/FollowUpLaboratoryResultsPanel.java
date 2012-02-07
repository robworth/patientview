package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.LabDataDao;
import com.solidstategroup.radar.model.sequenced.LabData;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.subtabs.LaboratoryResultsPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
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
import java.util.List;

public class FollowUpLaboratoryResultsPanel extends Panel {
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;
    @SpringBean
    private LabDataDao labDataDao;

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

        labResultsContainer.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                demographicsDao)));

        labResultsContainer.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisDao), "abbreviation")));

        labResultsContainer.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel, demographicsDao)));
        labResultsContainer.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsDao)));
        labResultsContainer.add(new TextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsDao)));

        final IModel<LabData> followUpModel = new Model<LabData>(new LabData());

        IModel<List> labResultsListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = labDataDao.getLabDataByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice<LabData> labResultsSwitcher = new DropDownChoice("labResultsSwitcher", followUpModel,
                labResultsListModel, new ChoiceRenderer("date", "id"));

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
