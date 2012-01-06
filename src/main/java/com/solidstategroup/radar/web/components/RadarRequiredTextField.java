package com.solidstategroup.radar.web.components;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import java.util.List;

public class RadarRequiredTextField extends TextField {
    public RadarRequiredTextField(String id, Form form, List<Component> componentsToUpdateList) {
        super(id);
        init(form, componentsToUpdateList);
    }

    private void init(Form form, List<Component> componentsToUpdateList) {
        setRequired(true);
        RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                new RadarFormComponentFeedbackIndicator(getId() + "FeedbackIndicator", this);
        form.add(radarFormComponentFeedbackIndicator);
        radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
        radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(radarFormComponentFeedbackIndicator);
    }
}
