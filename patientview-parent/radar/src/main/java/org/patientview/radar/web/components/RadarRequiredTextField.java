package org.patientview.radar.web.components;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;

import java.util.List;

public class RadarRequiredTextField extends TextField {
    public RadarRequiredTextField(String id, WebMarkupContainer parent, List<Component> componentsToUpdateList) {
        super(id);
        init(parent, componentsToUpdateList);
    }

    private void init(WebMarkupContainer parent, List<Component> componentsToUpdateList) {
        setRequired(true);
        RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                new RadarFormComponentFeedbackIndicator(getId() + "FeedbackIndicator", this);
        parent.add(radarFormComponentFeedbackIndicator);
        radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
        radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(radarFormComponentFeedbackIndicator);
    }
}
