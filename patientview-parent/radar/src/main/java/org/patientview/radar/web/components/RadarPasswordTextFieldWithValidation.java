package org.patientview.radar.web.components;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.validation.IValidator;

import java.util.List;


// package private - use RadarComponentFactory to access
class RadarPasswordTextFieldWithValidation extends PasswordTextField {

    public RadarPasswordTextFieldWithValidation(String id, IValidator validator, boolean required,
                                                WebMarkupContainer container, List<Component> componentsToUpdate) {
        super(id);
        init(id, container, validator, required, componentsToUpdate);
    }

    private void init(String id, WebMarkupContainer form, IValidator validator, boolean required,
                      List<Component> componentsToUpdate) {
        if (validator != null) {
             add(validator);
        }
        final ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id + "Feedback", this) {
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

        if (required) {
            setRequired(true);
            RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                    new RadarFormComponentFeedbackIndicator(getId() + "FeedbackIndicator", this) {
                        @Override
                        public boolean isVisible() {
                            if (feedbackPanel.isVisible()) {
                                return false;
                            }
                            return super.isVisible();
                        }
                    };
            form.add(radarFormComponentFeedbackIndicator);
            radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
            radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
            componentsToUpdate.add(radarFormComponentFeedbackIndicator);
        }

    }
}
