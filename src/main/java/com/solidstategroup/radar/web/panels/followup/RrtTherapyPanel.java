package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.TransplantDao;
import com.solidstategroup.radar.model.Transplant;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.tables.DialysisTablePanel;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RrtTherapyPanel extends Panel {
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;
    @SpringBean
    private TransplantDao transplantDao;

    public RrtTherapyPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // General details
        TextField radarNumber = new TextField("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        add(radarNumber);

        add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                demographicsDao)));

        add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisDao), "abbreviation")));

        add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel, demographicsDao)));
        add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsDao)));
        add(new TextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsDao)));

        // Reusable panel for the dialysis table
        add(new DialysisTablePanel("dialysisContainer", radarNumberModel));

        final IModel transplantListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    return transplantDao.getTransplantsByRadarNumber(radarNumberModel.getObject());
                }
                return Collections.emptyList();

            }
        };

        final IModel editTransplantModel = new Model<Transplant>();
        final List<Component> addTransplantFormComponentsToUpdate = new ArrayList<Component>();
        final List<Component> editTransplantFormComponentsToUpdate = new ArrayList<Component>();

        final WebMarkupContainer transplantsContainer = new WebMarkupContainer("transplantsContainer");
        add(transplantsContainer);
        transplantsContainer.setOutputMarkupPlaceholderTag(true);
        transplantsContainer.setOutputMarkupId(true);

        // Container for edit transplants
        final MarkupContainer editTransplantContainer = new WebMarkupContainer("editTransplantContainer") {
            @Override
            public boolean isVisible() {
                return editTransplantModel.getObject() != null;
            }
        };

        editTransplantContainer.setOutputMarkupPlaceholderTag(true);
        editTransplantContainer.setOutputMarkupPlaceholderTag(true);
        add(editTransplantContainer);

        // Transplants table
        transplantsContainer.add(new ListView<Transplant>("transplants", transplantListModel) {
            @Override
            protected void populateItem(final ListItem<Transplant> item) {
                item.setModel(new CompoundPropertyModel<Transplant>(item.getModelObject()));
                item.add(DateLabel.forDatePattern("date", RadarApplication.DATE_PATTERN));
                item.add(new Label("modality.description"));
                item.add(new Label("recurr"));
                item.add(DateLabel.forDatePattern("dateRecurr", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateFailure", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateRejected", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateBiopsy", RadarApplication.DATE_PATTERN));

                // Delete, edit and add reject buttons
                item.add(new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Transplant transplant = item.getModelObject();
                        transplantDao.deleteTransplant(transplant);
                        target.add(addTransplantFormComponentsToUpdate.toArray(new Component[
                                addTransplantFormComponentsToUpdate.size()]));
                        target.add(transplantsContainer);
                    }
                });
                item.add(new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        editTransplantModel.setObject(item.getModelObject());
                        target.add(editTransplantContainer);
                    }
                });
                item.add(new AjaxLink("addRejectLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                    }
                });
            }
        });

        // Container for reject transplants
        final MarkupContainer rejectDataContainer = new WebMarkupContainer("rejectDataContainer");
        rejectDataContainer.setVisible(false);
        rejectDataContainer.setOutputMarkupPlaceholderTag(true);
        add(rejectDataContainer);

        // Form for adding reject data - model probably needs changing
        Form<Transplant> rejectDataForm = new Form<Transplant>("form");
        rejectDataForm.add(new DateTimeField("dateRejected"));
        rejectDataForm.add(new DateTimeField("dateBiopsy"));
        rejectDataForm.add(new AjaxSubmitLink("add") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        rejectDataForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                rejectDataContainer.setVisible(false);
                target.add(rejectDataContainer);
            }
        });
        rejectDataForm.add(DateLabel.forDatePattern("transplantDate", new Model<Date>(), "dd/MM/yyyy"));
        rejectDataContainer.add(rejectDataForm);

        // Edit transplant form
        Form<Transplant> editTransplantForm =
                new TransplantForm("form", new CompoundPropertyModel<Transplant>(editTransplantModel), editTransplantFormComponentsToUpdate);
        editTransplantContainer.add(editTransplantForm);

        editTransplantForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                transplantDao.saveTransplant((Transplant) form.getModelObject());
                editTransplantModel.setObject(null);
                target.add(editTransplantContainer);
                target.add(transplantsContainer);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editTransplantFormComponentsToUpdate.toArray(
                        new Component[editTransplantFormComponentsToUpdate.size()]));
            }
        });
        editTransplantForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editTransplantModel.setObject(null);
                target.add(editTransplantContainer);
            }
        });


        // Add transplant form
        Form<Transplant> addTransplantForm =
                new TransplantForm("addTransplantForm", new CompoundPropertyModel<Transplant>(new Transplant()),
                        addTransplantFormComponentsToUpdate);
        addTransplantForm.add(new AjaxSubmitLink("add") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                target.add(form);
                Transplant transplant = (Transplant) form.getModelObject();
                transplant.setRadarNumber(radarNumberModel.getObject());
                transplantDao.saveTransplant(transplant);
                form.getModel().setObject(new Transplant());
                transplantsContainer.setVisible(true);
                target.add(transplantsContainer);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addTransplantFormComponentsToUpdate.toArray(new Component[addTransplantFormComponentsToUpdate.size()]));
            }
        });
        addTransplantForm.setOutputMarkupId(true);
        addTransplantForm.setOutputMarkupPlaceholderTag(true);
        add(addTransplantForm);
    }

    private final class TransplantForm extends Form<Transplant> {
        @SpringBean
        private TransplantDao transplantDao;

        private TransplantForm(String id, IModel<Transplant> transplantIModel, List<Component> componentsToUpdate) {
            super(id, transplantIModel);
            RadarRequiredDateTextField date = new RadarRequiredDateTextField("date", this, componentsToUpdate);
            add(date);
            RadarRequiredDropdownChoice modality = new RadarRequiredDropdownChoice("modality", transplantDao.getTransplantModalitites(),
                    new ChoiceRenderer("description", "id"), this, componentsToUpdate);
            add(modality);
            YesNoRadioGroup recurr = new YesNoRadioGroup("recurr");
            add(recurr);
            RadarDateTextField dateRecurr = new RadarDateTextField("dateRecurr", this, componentsToUpdate);
            add(dateRecurr);
            RadarDateTextField dateFailure = new RadarDateTextField("dateFailure", this, componentsToUpdate);
            add(dateFailure);
        }
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.RRT_THERAPY);
    }
}
