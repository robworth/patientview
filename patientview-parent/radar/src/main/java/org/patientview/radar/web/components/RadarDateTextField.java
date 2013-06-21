package org.patientview.radar.web.components;

import org.patientview.radar.web.RadarApplication;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;

import java.util.Date;
import java.util.List;

public class RadarDateTextField extends DateTextField {
    public RadarDateTextField(String id, Form form, List<Component> componentsToUpdate) {
        super(id, RadarApplication.DATE_PATTERN2);
        init(form, componentsToUpdate);
    }

    public RadarDateTextField(String id, IModel<Date> model, String datePattern, Form form,
                              List<Component> componentsToUpdate) {
        super(id, model, datePattern);
        init(form, componentsToUpdate);
    }

    private void init(Form form, List<Component> componentsToUpdate) {
        add(new RadarDatePicker());
        ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(getId() + "Feedback", this) {

            @Override
            public boolean isVisible() {
                List<FeedbackMessage> feedbackMessages = getCurrentMessages();
                for (FeedbackMessage feedbackMessage : feedbackMessages) {
                    if (feedbackMessage.getMessage().toString().contains("required")) {
                        return false;
                    }
                }
                return super.isVisible();
            }
        };
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);
        componentsToUpdate.add(feedbackPanel);
    }

}
