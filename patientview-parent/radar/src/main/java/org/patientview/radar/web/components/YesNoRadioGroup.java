package org.patientview.radar.web.components;

import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.Model;

public class YesNoRadioGroup extends RadioGroup<Boolean> {

    public YesNoRadioGroup(String id) {
        this(id, false);
    }

    public YesNoRadioGroup(String id, boolean includeUnknownOption) {
        this(id, true, includeUnknownOption);
    }

    public YesNoRadioGroup(String id, boolean includeLabels, boolean includeUnknownOption) {
        super(id);

        // Option for yes and no
        Radio<Boolean> yes = new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE));
        Radio<Boolean> no = new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE));
        add(yes, no);



        // Only put in labels if we're told to
        if (includeLabels) {
            add(yes, new FormComponentLabel("yesLabel", yes));
            add(no, new FormComponentLabel("noLabel", no));
        }

        if (includeUnknownOption) {
            // Option for unknown (null)
            Radio<Boolean> unknown = new Radio<Boolean>("unknown", new Model<Boolean>(null));
            add(unknown, new FormComponentLabel("unknownLabel", unknown));
        }
    }
}
