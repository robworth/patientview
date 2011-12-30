package com.solidstategroup.radar.web.panels.tables;

import com.solidstategroup.radar.model.Modality;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.DialysisDataProvider;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class DialysisTablePanel extends Panel {

    public DialysisTablePanel(String id) {
        super(id);

        // Dialysis
        add(new DataView<Treatment>("dialysis", new DialysisDataProvider()) {
            @Override
            protected void populateItem(Item<Treatment> item) {
                item.add(new Label("modality.type"));
                item.add(DateLabel.forDatePattern("dateStarted", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateStopped", RadarApplication.DATE_PATTERN));
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
            }
        });

        // Edit dialysis container
        MarkupContainer editDialysisContainer = new WebMarkupContainer("editDialysisContainer");
        editDialysisContainer.setOutputMarkupPlaceholderTag(true);
        editDialysisContainer.setVisible(false);
        add(editDialysisContainer);

        DialysisForm editDialysisForm =
                new DialysisForm("editDialysisForm", new CompoundPropertyModel<Treatment>(new Treatment()));
        editDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        editDialysisForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Todo: Implement
            }
        });
        editDialysisContainer.add(editDialysisForm);

        // Add dialysis form
        DialysisForm addDialysisForm =
                new DialysisForm("addDialysisForm", new CompoundPropertyModel<Treatment>(new Treatment()));
        addDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        add(addDialysisForm);
    }

    private final class DialysisForm extends Form<Treatment> {
        private DialysisForm(String id, IModel<Treatment> treatmentIModel) {
            super(id, treatmentIModel);
            add(new DropDownChoice<Modality>("modality"));
            add(DateTextField.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
            add(DateTextField.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
        }
    }

}
