package com.solidstategroup.radar.web.components;


import com.solidstategroup.radar.web.behaviours.AttributePrepender;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.List;

public class RadarComponentFactory {

    public static Label getSuccessMessageLabel(String id, final Form form, final List<Component> componentsToUpdate) {
        return new Label(id, RadarModelFactory.getSuccessMessageModel(form)) {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdate.add(this);
            }

            @Override
            public boolean isVisible() {
                return form.isSubmitted() && !form.hasError();
            }
        };
    }

    public static Label getErrorMessageLabel(String id, final Form form, final List<Component> componentsToUpdate) {
        return new Label(id, "Please fix all errors") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdate.add(this);
            }

            @Override
            public boolean isVisible() {
                return form.isSubmitted() && form.hasError();
            }
        };
    }

}
