package org.patientview.radar.web.components;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;

public class RadarRequiredDropdownChoice extends DropDownChoice {
    public RadarRequiredDropdownChoice(String id, List choices, IChoiceRenderer iChoiceRenderer, WebMarkupContainer
            parent, List<Component> componentsToUpdateList) {
        super(id, choices, iChoiceRenderer);
        init(parent, componentsToUpdateList);
    }

    public RadarRequiredDropdownChoice(String id, IModel iModel, List choices, IChoiceRenderer iChoiceRenderer,
                                       Form form, List<Component> componentsToUpdateList) {
        super(id, iModel, choices, iChoiceRenderer);
        init(form, componentsToUpdateList);
    }

    public RadarRequiredDropdownChoice(String id, IModel iModel, List choices, Form form,
                                       List<Component> componentsToUpdateList) {
        super(id, iModel, choices);
        init(form, componentsToUpdateList);
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
