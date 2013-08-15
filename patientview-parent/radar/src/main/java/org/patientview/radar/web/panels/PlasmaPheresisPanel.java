package org.patientview.radar.web.panels;


import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.enums.RemissionAchieved;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.PlasmapheresisManager;
import org.patientview.radar.service.TreatmentManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.components.RadarRequiredDropdownChoice;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PlasmaPheresisPanel extends Panel {
    @SpringBean
    private PlasmapheresisManager plasmapheresisManager;

    public PlasmaPheresisPanel(String id, final IModel<Long> radarNumberModel) {

        super(id);
        final WebMarkupContainer plasmapheresisContainer = new WebMarkupContainer("plasmapheresisContainer");

        plasmapheresisContainer.setOutputMarkupId(true);
        plasmapheresisContainer.setOutputMarkupPlaceholderTag(true);
        add(plasmapheresisContainer);

        final List<Component> addPlasmapheresisComponentsToUpdate = new ArrayList<Component>();
        final List<Component> editPlasmapheresisComponentsToUpdate = new ArrayList<Component>();

        final IModel plasmapheresisListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    return plasmapheresisManager.getPlasmapheresisByRadarNumber(radarNumberModel.getObject());
                }
                return Collections.emptyList();
            }
        };

        final IModel editPlasmapheresisModel = new Model<Plasmapheresis>();
        final MarkupContainer editPlasmapheresisContainer = new WebMarkupContainer("editPlasmapheresisContainer") {
            @Override
            public boolean isVisible() {
                return editPlasmapheresisModel.getObject() != null;
            }
        };
        editPlasmapheresisContainer.setOutputMarkupPlaceholderTag(true);
        editPlasmapheresisContainer.setOutputMarkupId(true);

        ListView<Plasmapheresis> plasmapheresisListViewlistView = new ListView<Plasmapheresis>("plasmapheresis",
                plasmapheresisListModel) {
            @Override
            protected void populateItem(final ListItem<Plasmapheresis> item) {
                item.setModel(new CompoundPropertyModel<Plasmapheresis>(item.getModelObject()));
                item.add(DateLabel.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
                item.add(new Label("plasmapheresisExchanges.name"));
                item.add(new Label("response.label"));
                AjaxLink ajaxDeleteLink = new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Plasmapheresis plasmapheresis = item.getModelObject();
                        plasmapheresisManager.deletePlasmaPheresis(plasmapheresis);
                        target.add(addPlasmapheresisComponentsToUpdate.toArray(new Component[
                                addPlasmapheresisComponentsToUpdate.size()]));
                        target.add(plasmapheresisContainer);
                    }
                };
                item.add(ajaxDeleteLink);
                ajaxDeleteLink.add(RadarBehaviourFactory.getDeleteConfirmationBehaviour());
                AjaxLink ajaxEditLink = new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        editPlasmapheresisModel.setObject(item.getModelObject());
                        target.add(editPlasmapheresisContainer);
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
        plasmapheresisContainer.add(plasmapheresisListViewlistView);

        // Add the form
        PlasmapheresisForm editPlasmapheresisForm = new PlasmapheresisForm("editPlasmapheresisForm",
                new CompoundPropertyModel<Plasmapheresis>(editPlasmapheresisModel),
                editPlasmapheresisComponentsToUpdate);

        editPlasmapheresisForm.add(new AjaxSubmitLink("saveTop") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(editPlasmapheresisContainer);
                target.add(plasmapheresisContainer);
                try {
                    plasmapheresisManager.savePlasmapheresis((Plasmapheresis) form.getModelObject());
                } catch (InvalidModelException e) {
                    for (String error : e.getErrors()) {
                        error(error);
                    }
                    return;
                }
                form.getModel().setObject(null);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editPlasmapheresisComponentsToUpdate.toArray(new Component[
                        editPlasmapheresisComponentsToUpdate.size()]));
            }
        });

        editPlasmapheresisForm.add(new AjaxLink("cancelTop") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editPlasmapheresisModel.setObject(null);
                target.add(editPlasmapheresisContainer);
            }
        });

        editPlasmapheresisForm.add(new AjaxSubmitLink("saveBottom") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(editPlasmapheresisContainer);
                target.add(plasmapheresisContainer);
                try {
                    plasmapheresisManager.savePlasmapheresis((Plasmapheresis) form.getModelObject());
                } catch (InvalidModelException e) {
                    for (String error : e.getErrors()) {
                        error(error);
                    }
                    return;
                }
                form.getModel().setObject(null);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editPlasmapheresisComponentsToUpdate.toArray(new Component[
                        editPlasmapheresisComponentsToUpdate.size()]));
            }
        });
        editPlasmapheresisForm.add(new AjaxLink("cancelBottom") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editPlasmapheresisModel.setObject(null);
                target.add(editPlasmapheresisContainer);
            }
        });

        editPlasmapheresisContainer.add(editPlasmapheresisForm);
        add(editPlasmapheresisContainer);

        // Add the add plasmapheresis form
        PlasmapheresisForm addPlasmapheresisForm = new PlasmapheresisForm("addPlasmapheresisForm",
                new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis()), addPlasmapheresisComponentsToUpdate);

        addPlasmapheresisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                target.add(addPlasmapheresisComponentsToUpdate.toArray(new Component[
                        addPlasmapheresisComponentsToUpdate.size()]));
                target.add(plasmapheresisContainer);

                Plasmapheresis plasmapheresis = (Plasmapheresis) form.getModelObject();
                plasmapheresis.setRadarNumber(radarNumberModel.getObject());
                try {
                    plasmapheresisManager.savePlasmapheresis(plasmapheresis);
                } catch (InvalidModelException e) {
                    for (String error : e.getErrors()) {
                        error(error);
                    }
                    return;
                }

                form.getModel().setObject(new Plasmapheresis());
                plasmapheresisContainer.setVisible(true);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addPlasmapheresisComponentsToUpdate.toArray(new Component[
                        addPlasmapheresisComponentsToUpdate.size()]));
            }
        });
        add(addPlasmapheresisForm);
    }

    private final class PlasmapheresisForm extends Form<Plasmapheresis> {
        private RadarDateTextField endDate;
        @SpringBean
        private PlasmapheresisManager plasmapheresisManager;

        private PlasmapheresisForm(String id, IModel<Plasmapheresis> model, List<Component> componentsToUpdate) {
            super(id, model);
            RadarRequiredDateTextField startDate = new RadarRequiredDateTextField("startDate", this,
                    componentsToUpdate);
            add(startDate);
            endDate = new RadarDateTextField("endDate", this, componentsToUpdate);
            add(endDate);


            RadarRequiredDropdownChoice plasmapheresisExchanges = new RadarRequiredDropdownChoice(
                    "plasmapheresisExchanges", plasmapheresisManager.getPlasmapheresisExchangeUnits(),
                    new ChoiceRenderer("name", "id"), this, componentsToUpdate);
            add(plasmapheresisExchanges);

            RadarRequiredDropdownChoice response = new RadarRequiredDropdownChoice("response",
                    Arrays.asList(RemissionAchieved.COMPLETE,
                            RemissionAchieved.PARTIAL, RemissionAchieved.NONE), new ChoiceRenderer("label", "id"),
                    this, componentsToUpdate);
            add(response);

            componentsToUpdate.add(startDate);
            componentsToUpdate.add(endDate);
            componentsToUpdate.add(plasmapheresisExchanges);
            componentsToUpdate.add(response);

            endDate.setOutputMarkupId(true);
            endDate.setOutputMarkupPlaceholderTag(true);

            startDate.setOutputMarkupId(true);
            startDate.setOutputMarkupPlaceholderTag(true);

            plasmapheresisExchanges.setOutputMarkupId(true);
            plasmapheresisExchanges.setOutputMarkupPlaceholderTag(true);

            response.setOutputMarkupId(true);
            response.setOutputMarkupPlaceholderTag(true);

            FeedbackPanel plasmapheresisFeedback = new FeedbackPanel("plasmapheresisFeedback",
                    new IFeedbackMessageFilter() {
                        public boolean accept(FeedbackMessage feedbackMessage) {
                            return TreatmentManager.ERROR_MESSAGES.contains(feedbackMessage.getMessage());
                        }
                    });

            add(plasmapheresisFeedback);
            plasmapheresisFeedback.setOutputMarkupPlaceholderTag(true);
            componentsToUpdate.add(plasmapheresisFeedback);
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            Plasmapheresis plasmapheresis = getModelObject();
            Date start = plasmapheresis.getStartDate();
            Date end = plasmapheresis.getEndDate();
            if (start != null && end != null && start.compareTo(end) != -1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }
}
