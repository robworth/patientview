package org.patientview.radar.web.components;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackIndicator;

public class RadarFormComponentFeedbackIndicator extends FormComponentFeedbackIndicator{
    public RadarFormComponentFeedbackIndicator(String id, Component component) {
        super(id);
        setIndicatorFor(component);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }
}
