package com.solidstategroup.radar.web.components;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.validation.IValidator;

import java.util.List;


public class RadarTextFieldWithValidation extends TextField {
    public RadarTextFieldWithValidation(String id, IValidator validator, WebMarkupContainer container, List<Component> componentsToUpdate) {
        super(id);
        init(id, container, validator, componentsToUpdate);

    }

    private void init(String id, WebMarkupContainer form, IValidator validator, List<Component> componentsToUpdate) {
        add(validator);
        final ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id + "Feedback", this){
                        @Override
            public boolean isVisible() {
                List<FeedbackMessage> feedbackMessages = getCurrentMessages();
                for(FeedbackMessage feedbackMessage : feedbackMessages) {
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
