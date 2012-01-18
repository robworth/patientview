package com.solidstategroup.radar.web.components;


import com.solidstategroup.radar.web.models.RadarModelFactory;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

import java.util.List;

public class RadarComponentFactory {

    public static Label getSuccessMessageLabel(String id, final Form form, final List<Component> componentsToUpdate) {
        return new Label(id, RadarModelFactory.getSuccessMessageModel(form)) {
            {
                setVisible(false);
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdate.add(this);
            }
        };
    }
}
