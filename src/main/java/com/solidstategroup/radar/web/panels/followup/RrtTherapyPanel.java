package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.model.Transplant;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.dataproviders.TransplantDataProvider;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.tables.DialysisTablePanel;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Date;

public class RrtTherapyPanel extends Panel {

    public RrtTherapyPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Reusable panel for the dialysis table
        add(new DialysisTablePanel("dialysisContainer"));

        // Transplants table
        add(new DataView<Transplant>("transplants", new TransplantDataProvider()) {
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
                new TransplantForm("form", new CompoundPropertyModel<Transplant>(new Transplant()));
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

        // Add transplant form
        Form<Transplant> addTransplantForm =
                new TransplantForm("addTransplantForm", new CompoundPropertyModel<Transplant>(new Transplant()));
        addTransplantForm.add(new AjaxSubmitLink("add") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        add(addTransplantForm);
    }

    private class TransplantForm extends Form<Transplant> {
        private TransplantForm(String id, IModel<Transplant> transplantIModel) {
            super(id, transplantIModel);
            add(new DateTextField("date"));
            add(new DropDownChoice("type"));
            add(new YesNoRadioGroup("recurr"));
            add(new DateTextField("dateRecurr"));
            add(new DateTextField("dateFailure"));
        }
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.RRT_THERAPY);
    }
}
