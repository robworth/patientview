package com.solidstategroup.radar.web.panels.tables;

import com.solidstategroup.radar.dao.TreatmentDao;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DialysisTablePanel extends Panel {
    @SpringBean
    private TreatmentDao treatmentDao;

    public DialysisTablePanel(String id, final IModel<Long> radarNumberModel) {
        super(id);

        final IModel dialysisListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {
                if (radarNumberModel.getObject() != null) {
                    return treatmentDao.getTreatmentsByRadarNumber(radarNumberModel.getObject());
                }
                return Collections.emptyList();
            }
        };

        final WebMarkupContainer dialysisContainer = new WebMarkupContainer("dialysisContainer");
        add(dialysisContainer);

        final List<Component> addDialysisFormComponentsToUpdate = new ArrayList<Component>();
        final List<Component> editDialysisFormComponentsToUpdate = new ArrayList<Component>();

        final IModel editDialysisModel = new Model<Treatment>();

        // Edit dialysis container
        final MarkupContainer editDialysisContainer = new WebMarkupContainer("editDialysisContainer") {
            @Override
            public boolean isVisible() {
                return editDialysisModel.getObject() != null;
            }
        };
        editDialysisContainer.setOutputMarkupPlaceholderTag(true);
        editDialysisContainer.setOutputMarkupPlaceholderTag(true);
        add(editDialysisContainer);

        // Dialysis
        ListView<Treatment> dialysisListView = new ListView<Treatment>("dialysis", dialysisListModel) {
            @Override
            protected void populateItem(final ListItem<Treatment> item) {
                item.setModel(new CompoundPropertyModel<Treatment>(item.getModelObject()));
                item.add(new Label("treatmentModality.description"));
                item.add(DateLabel.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
                item.add(new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Treatment treatment = item.getModelObject();
                        treatmentDao.deleteTreatment(treatment);
                        target.add(addDialysisFormComponentsToUpdate.toArray(new Component[
                                addDialysisFormComponentsToUpdate.size()]));
                        target.add(dialysisContainer);

                    }
                });
                item.add(new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        editDialysisModel.setObject(item.getModelObject());
                        target.add(editDialysisContainer);
                    }
                });
            }
        };
        dialysisContainer.setOutputMarkupId(true);
        dialysisContainer.setOutputMarkupPlaceholderTag(true);
        dialysisContainer.add(dialysisListView);

        DialysisForm editDialysisForm =
                new DialysisForm("editDialysisForm", new CompoundPropertyModel<Treatment>(editDialysisModel),
                        editDialysisFormComponentsToUpdate);
        editDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                treatmentDao.saveTreatment((Treatment) form.getModelObject());
                form.getModel().setObject(null);
                target.add(editDialysisContainer);
                target.add(dialysisContainer);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editDialysisFormComponentsToUpdate.toArray(new Component[
                        editDialysisFormComponentsToUpdate.size()]));
            }
        });
        editDialysisForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                editDialysisModel.setObject(null);
                target.add(editDialysisContainer);
            }
        });
        editDialysisContainer.add(editDialysisForm);


        // Add dialysis form
        DialysisForm addDialysisForm =
                new DialysisForm("addDialysisForm", new CompoundPropertyModel<Treatment>(new Treatment()),
                        addDialysisFormComponentsToUpdate);
        addDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form form) {
                Treatment treatment = (Treatment) form.getModelObject();
                treatment.setRadarNumber(radarNumberModel.getObject());
                treatmentDao.saveTreatment(treatment);
                target.add(addDialysisFormComponentsToUpdate.toArray(new Component[
                        addDialysisFormComponentsToUpdate.size()]));
                form.getModel().setObject(new Treatment());
                dialysisContainer.setVisible(true);
                target.add(dialysisContainer);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addDialysisFormComponentsToUpdate
                        .toArray(new Component[addDialysisFormComponentsToUpdate.size()]));
            }
        });
        add(addDialysisForm);
    }

    private final class DialysisForm extends Form<Treatment> {
        private RadarDateTextField endDate;
        @SpringBean
        private TreatmentDao treatmentDao;

        private DialysisForm(String id, IModel<Treatment> treatmentIModel, List<Component> componentsToUpdate) {
            super(id, treatmentIModel);

            RadarRequiredDropdownChoice treatmentModality = new RadarRequiredDropdownChoice("treatmentModality", treatmentDao.getTreatmentModalities(),
                    new ChoiceRenderer("description", "id"), this,
                    componentsToUpdate);
            add(treatmentModality);

            RadarRequiredDateTextField startDate = new RadarRequiredDateTextField("startDate", this, componentsToUpdate);
            add(startDate);
            endDate = new RadarDateTextField("endDate", this, componentsToUpdate);
            add(endDate);

            treatmentModality.setOutputMarkupId(true);
            treatmentModality.setOutputMarkupPlaceholderTag(true);

            startDate.setOutputMarkupPlaceholderTag(true);
            startDate.setOutputMarkupPlaceholderTag(true);

            endDate.setOutputMarkupPlaceholderTag(true);
            endDate.setOutputMarkupPlaceholderTag(true);

            componentsToUpdate.add(treatmentModality);
            componentsToUpdate.add(startDate);
            componentsToUpdate.add(endDate);
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            Treatment treatment = getModelObject();
            Date start = treatment.getStartDate();
            Date end = treatment.getEndDate();
            if (start != null && end != null && start.compareTo(end) != -1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

}
