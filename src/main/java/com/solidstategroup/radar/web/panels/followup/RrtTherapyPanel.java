package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Transplant;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.dataproviders.TransplantDataProvider;
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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RrtTherapyPanel extends Panel {
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;

    public RrtTherapyPanel(String id, IModel<Long> radarNumberModel) {
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
        add(new DialysisTablePanel("dialysisContainer"));

        // Transplants table
        add(new DataView<Transplant>("transplants", new TransplantDataProvider(radarNumberModel)) {
            @Override
            protected void populateItem(Item<Transplant> item) {
                item.add(DateLabel.forDatePattern("date", RadarApplication.DATE_PATTERN));
                item.add(new Label("type"));
                item.add(new Label("recurr"));
                item.add(DateLabel.forDatePattern("dateRecurr", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateFailure", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateRejected", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateBiopsy", RadarApplication.DATE_PATTERN));

                // Delete, edit and add reject buttons
                item.add(new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        // Todo: Implement
                    }
                });
                item.add(new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        // Todo: Implement
                    }
                });
                item.add(new AjaxLink("addRejectLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        // Todo: Implement
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

        // Container for edit transplants
        final MarkupContainer editTransplantContainer = new WebMarkupContainer("editTransplantContainer");
        editTransplantContainer.setVisible(false);
        editTransplantContainer.setOutputMarkupPlaceholderTag(true);
        add(editTransplantContainer);

        // Edit transplant form
        Form<Transplant> editTransplantForm =
                new TransplantForm("form", new CompoundPropertyModel<Transplant>(new Transplant()),
                        new ArrayList<Component>());
        editTransplantContainer.add(editTransplantForm);

        editTransplantForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        editTransplantForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editTransplantContainer.setVisible(false);
                target.add(editTransplantContainer);
            }
        });

        final List<Component> addFormComponentsToUpdate = new ArrayList<Component>();
        // Add transplant form
        Form<Transplant> addTransplantForm =
                new TransplantForm("addTransplantForm", new CompoundPropertyModel<Transplant>(new Transplant()),
                        addFormComponentsToUpdate);
        addTransplantForm.add(new AjaxSubmitLink("add") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(addFormComponentsToUpdate.toArray(new Component[addFormComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addFormComponentsToUpdate.toArray(new Component[addFormComponentsToUpdate.size()]));
            }
        });
        add(addTransplantForm);
    }

    private final class TransplantForm extends Form<Transplant> {
        private TransplantForm(String id, IModel<Transplant> transplantIModel, List<Component> componentsToUpdate) {
            super(id, transplantIModel);
            add(new RadarRequiredDateTextField("date", this, componentsToUpdate));
            add(new RadarRequiredDropdownChoice("type", Arrays.asList("temp"), new ChoiceRenderer(), this,
                    componentsToUpdate));
            add(new YesNoRadioGroup("recurr"));
            add(new RadarDateTextField("dateRecurr", this, componentsToUpdate));
            add(new RadarDateTextField("dateFailure", this, componentsToUpdate));
        }
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.RRT_THERAPY);
    }
}
