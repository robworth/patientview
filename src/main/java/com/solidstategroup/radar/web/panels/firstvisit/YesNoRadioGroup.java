package com.solidstategroup.radar.web.panels.firstvisit;

import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.Model;

public class YesNoRadioGroup extends Panel {

    public YesNoRadioGroup(String id) {
        super(id);

        RadioGroup<Boolean> radioGroup = new RadioGroup<Boolean>("group", new ComponentPropertyModel<Boolean>(id));
        add(radioGroup);
        
        // Yes
        Radio<Boolean> yes = new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE));
        radioGroup.add(yes, new FormComponentLabel("yesLabel", yes));

        // No
        Radio<Boolean> no = new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE));
        radioGroup.add(no, new FormComponentLabel("noLabel", no));
    }

}
