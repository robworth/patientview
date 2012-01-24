package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.HospitalisationDao;
import com.solidstategroup.radar.model.Hospitalisation;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HospitalisationPanel extends Panel {

    @SpringBean
    private HospitalisationDao hospitalisationDao;
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;


    public HospitalisationPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Add container for the form, it is not shown on first visit
        final MarkupContainer hospitalisationContainer = new WebMarkupContainer("hospitalisationContainer");
        hospitalisationContainer.setVisible(false);
        hospitalisationContainer.setOutputMarkupId(true);
        hospitalisationContainer.setOutputMarkupPlaceholderTag(true);
        add(hospitalisationContainer);

        // Set up models for the previous results switcher
        final IModel<Hospitalisation> hospitalisationModel = new Model<Hospitalisation>();

        IModel<List<Hospitalisation>> hospitalisations = new AbstractReadOnlyModel<List<Hospitalisation>>() {
            @Override
            public List<Hospitalisation> getObject() {
                if (radarNumberModel.getObject() == null) {
                    return Collections.emptyList();
                } else {
                    return hospitalisationDao.getHospitalisationsByRadarNumber(radarNumberModel.getObject());
                }
            }
        };

        // Previous results switcher
        DropDownChoice<Hospitalisation> switcher =
                new DropDownChoice<Hospitalisation>("switcher", hospitalisationModel, hospitalisations,
                        new IChoiceRenderer<Hospitalisation>() {
                            public Object getDisplayValue(Hospitalisation object) {
                                // Todo: Figure out what this should show
                                return object.getId();
                            }

                            public String getIdValue(Hospitalisation object, int index) {
                                return String.valueOf(object.getId());
                            }
                        });
        add(switcher);

        // Add ajax behaviour to update form
        switcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                hospitalisationContainer.setVisible(true);
                target.add(hospitalisationContainer);
            }
        });

        // Add ajax add new
        add(new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                hospitalisationContainer.setVisible(true);
                target.add(hospitalisationContainer);
            }
        });

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        // Set up model
        CompoundPropertyModel<Hospitalisation> model;
        model = new CompoundPropertyModel<Hospitalisation>(new LoadableDetachableModel<Hospitalisation>() {
            @Override
            protected Hospitalisation load() {
                // If we've got a selected record use that model
                if (hospitalisationModel.getObject() != null) {
                    return hospitalisationDao.getHospitalisation(hospitalisationModel.getObject().getId());
                }
                // Otherwise just return a new object
                return new Hospitalisation();
            }
        });

        // Set up the form
        Form<Hospitalisation> form = new Form<Hospitalisation>("form", model) {
            @Override
            protected void onValidateModelObjects() {
                super.onValidateModelObjects();
                Hospitalisation hospitalisation = getModelObject();
                Date dateofAdmission = hospitalisation.getDateOfAdmission();
                Date dateofDischarge = hospitalisation.getDateOfDischarge();
                if (dateofAdmission != null && dateofDischarge != null
                        && dateofAdmission.compareTo(dateofDischarge) != -1) {
                    get("dateOfDischarge").error("Date has to be after admission date");
                }
            }
        };

               // General details
        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        form.add(radarNumber);

        form.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                demographicsDao)));

        form.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisDao), "abbreviation")));

        form.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel, demographicsDao)));
        form.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsDao)));
        form.add(new TextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsDao)));

        form.add(new RadarRequiredDateTextField("dateOfAdmission", form, componentsToUpdate));
        form.add(new RadarDateTextField("dateOfDischarge", form, componentsToUpdate));
        form.add(new TextArea("reason"));
        form.add(new TextArea("comments"));
        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });
        hospitalisationContainer.add(form);
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.HOSPITALISATION);
    }
}
