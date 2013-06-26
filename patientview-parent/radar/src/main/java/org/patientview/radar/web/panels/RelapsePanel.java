package org.patientview.radar.web.panels;

import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.enums.RemissionAchieved;
import org.patientview.radar.model.sequenced.Relapse;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.RelapseManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelapsePanel extends Panel {

    @SpringBean
    private RelapseManager relapseManager;
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    public RelapsePanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);


        final IModel<Boolean> relapseListVisibilityModel = new Model<Boolean>(false);
        if (radarNumberModel.getObject() != null) {
            relapseListVisibilityModel.setObject(!relapseManager.getRelapsesByRadarNumber(
                    radarNumberModel.getObject()).isEmpty());
        }

        final WebMarkupContainer relapseListViewContainer = new WebMarkupContainer("relapseListViewContainer") {
            @Override
            public boolean isVisible() {
                return relapseListVisibilityModel.getObject();
            }
        };

        relapseListViewContainer.setOutputMarkupId(true);
        relapseListViewContainer.setOutputMarkupPlaceholderTag(true);
        add(relapseListViewContainer);

        final List<Component> addRelapseComponentsToUpdate = new ArrayList<Component>();
        final List<Component> editRelapseComponentsToUpdate = new ArrayList<Component>();

        final IModel relapseListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    return relapseManager.getRelapsesByRadarNumber(radarNumberModel.getObject());
                }
                return Collections.emptyList();
            }
        };

        //
        final IModel editRelapseModel = new Model<Relapse>();
        final MarkupContainer editRelapseContainer = new WebMarkupContainer("editRelapseContainer") {
            @Override
            public boolean isVisible() {
                return editRelapseModel.getObject() != null;
            }
        };
        editRelapseContainer.setOutputMarkupPlaceholderTag(true);
        editRelapseContainer.setOutputMarkupId(true);

        ListView<Relapse> relapseListView = new ListView<Relapse>("relapseListView",
                relapseListModel) {
            @Override
            protected void populateItem(final ListItem<Relapse> item) {
                item.setModel(new CompoundPropertyModel<Relapse>(item.getModelObject()));
                item.add(DateLabel.forDatePattern("dateOfRelapse", RadarApplication.DATE_PATTERN));
                item.add(new Label("transplantedNative.label"));
                item.add(new Label("viralTrigger"));
                item.add(new Label("immunisationTrigger"));
                item.add(new Label("otherTrigger"));
                item.add(new Label("drug1"));
                item.add(new Label("drug2"));
                item.add(new Label("drug3"));
                item.add(new Label("remissionAchieved.label"));
                item.add(DateLabel.forDatePattern("dateOfRemission", RadarApplication.DATE_PATTERN));

                AjaxLink ajaxDeleteLink = new AjaxLink("delete") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Relapse relapse = item.getModelObject();
                        relapseManager.deleteRelapse(relapse);
                        target.add(addRelapseComponentsToUpdate.toArray(new Component[
                                addRelapseComponentsToUpdate.size()]));
                        target.add(relapseListViewContainer);
                        relapseListVisibilityModel.setObject(!relapseManager.getRelapsesByRadarNumber(
                                radarNumberModel.getObject()).isEmpty());
                    }
                };
                item.add(ajaxDeleteLink);
                ajaxDeleteLink.add(RadarBehaviourFactory.getDeleteConfirmationBehaviour());
                AjaxLink ajaxEditLink = new AjaxLink("edit") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        editRelapseModel.setObject(item.getModelObject());
                        target.add(editRelapseContainer);
                    }
                };
                item.add(ajaxEditLink);
                AuthenticatedWebSession session = RadarSecuredSession.get();
                if (session.isSignedIn()) {
                    if (session.getRoles().hasRole(User.ROLE_PATIENT)) {
                        ajaxDeleteLink.setVisible(false);
                        ajaxEditLink.setVisible(false);
                    }
                }
            }
        };
        relapseListViewContainer.add(relapseListView);

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

        RelapseForm editRelapseForm = new RelapseForm("editRelapseForm",
                new CompoundPropertyModel<Relapse>(editRelapseModel), editRelapseComponentsToUpdate);

        editRelapseForm.add(new AjaxSubmitLink("saveTop") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                relapseManager.saveRelapse((Relapse) form.getModelObject());
                form.getModel().setObject(null);
                target.add(editRelapseContainer);
                target.add(relapseListViewContainer);
                target.add(form);
                target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editRelapseComponentsToUpdate.toArray(new Component[
                        editRelapseComponentsToUpdate.size()]));
            }
        });

        editRelapseForm.add(new AjaxLink("cancelTop") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editRelapseModel.setObject(null);
                target.add(editRelapseContainer);
            }
        });

        editRelapseForm.add(new AjaxSubmitLink("saveBottom") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                relapseManager.saveRelapse((Relapse) form.getModelObject());
                form.getModel().setObject(null);
                target.add(editRelapseContainer);
                target.add(relapseListViewContainer);
                target.add(form);
                target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editRelapseComponentsToUpdate.toArray(new Component[
                        editRelapseComponentsToUpdate.size()]));
            }
        });

        editRelapseForm.add(new AjaxLink("cancelBottom") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editRelapseModel.setObject(null);
                target.add(editRelapseContainer);
            }
        });

        editRelapseContainer.add(editRelapseForm);
        add(editRelapseContainer);

        WebMarkupContainer addRelapseFormContainer = new WebMarkupContainer("addRelapseFormContainer");

        Form<Relapse> addRelapseform = new RelapseForm("addRelapseForm",
                new CompoundPropertyModel<Relapse>(new Relapse()),
                addRelapseComponentsToUpdate);

        addRelapseform.setOutputMarkupId(true);
        addRelapseform.setOutputMarkupPlaceholderTag(true);

        addRelapseform.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Relapse relapse = (Relapse) form.getModelObject();
                relapse.setRadarNumber(radarNumberModel.getObject());
                relapseManager.saveRelapse(relapse);
                target.add(addRelapseComponentsToUpdate.toArray(new Component[
                        addRelapseComponentsToUpdate.size()]));
                relapseListVisibilityModel.setObject(true);
                target.add(relapseListViewContainer);
                form.getModel().setObject(new Relapse());
                target.add(form);
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(addRelapseComponentsToUpdate.toArray(new Component[
                        addRelapseComponentsToUpdate.size()]));

            }
        });

        addRelapseFormContainer.add(addRelapseform);
        add(addRelapseFormContainer);

        PlasmaPheresisPanel plasmaPheresisPanel = new PlasmaPheresisPanel("plasmapheresisPanel", radarNumberModel);
        add(plasmaPheresisPanel);
    }

    @Override
    public boolean isVisible() {
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.RELAPSE);
    }

    private static class RelapseForm extends Form<Relapse> {
        private RelapseForm(String id, IModel<Relapse> model, List<Component> componentsToUpdate) {
            super(id, model);
            add(new RadarRequiredDateTextField("dateOfRelapse", this, componentsToUpdate));

            // Transplanted / native radio options
            RadioGroup<KidneyTransplantedNative> transplantedNative =
                    new RadioGroup<KidneyTransplantedNative>("transplantedNative");
            transplantedNative.add(new Radio<KidneyTransplantedNative>("tx",
                    new Model<KidneyTransplantedNative>(KidneyTransplantedNative.TRANSPLANTED)));
            transplantedNative.add(new Radio<KidneyTransplantedNative>("native",
                    new Model<KidneyTransplantedNative>(KidneyTransplantedNative.NATIVE)));
            add(transplantedNative);

            // Triggers
            add(new TextField("viralTrigger"));
            add(new TextField("immunisationTrigger"));
            add(new TextField("otherTrigger"));

            // Drugs
            add(new TextField("drug1"));
            add(new TextField("drug2"));
            add(new TextField("drug3"));

            // Remission radio group
            RadioGroup<RemissionAchieved> remissionAchieved = new RadioGroup<RemissionAchieved>("remissionAchieved");
            remissionAchieved.add(new Radio<RemissionAchieved>("complete",
                    new Model<RemissionAchieved>(RemissionAchieved.COMPLETE)));
            remissionAchieved
                    .add(new Radio<RemissionAchieved>("partial", new Model<RemissionAchieved>(
                            RemissionAchieved.PARTIAL)));
            remissionAchieved
                    .add(new Radio<RemissionAchieved>("none", new Model<RemissionAchieved>(RemissionAchieved.NONE)));
            add(remissionAchieved);

            add(new RadarDateTextField("dateOfRemission", this, componentsToUpdate));

            add(RadarComponentFactory.getSuccessMessageLabel("successMessage", this, componentsToUpdate));
            add(RadarComponentFactory.getErrorMessageLabel("errorMessage", this, componentsToUpdate));

        }
    }
}
