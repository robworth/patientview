package com.solidstategroup.radar.web.panels.firstvisit;

import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.Model;

public class YesNoRadioGroupPanel extends Panel {

    public YesNoRadioGroupPanel(String id, boolean addLabels) {
        super(id);

        RadioGroup<Boolean> radioGroup = new RadioGroup<Boolean>("group", new Model<Boolean>());
                //new ComponentPropertyModel<Boolean>(id));
        add(radioGroup);

        // Yes, No
        Radio<Boolean> yes = new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE));
        Radio<Boolean> no = new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE));
        radioGroup.add(yes, no);

        // If we're using labels add them too
        if (addLabels) {
            radioGroup.add(new FormComponentLabel("yesLabel", yes));
            radioGroup.add(new FormComponentLabel("noLabel", no));
        }
    }

}
