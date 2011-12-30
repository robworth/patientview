package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Relative;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;

import java.util.Collections;

public class DiagnosisRelativePanel extends Panel {

    public DiagnosisRelativePanel(String id, int i) {
        super(id);
        add(new DropDownChoice<Relative>("relative", new ComponentPropertyModel("relativeWithDiseaseRadarNumber" + i),
                Collections.<Relative>emptyList()));
        add(new TextField("radarNumber", new ComponentPropertyModel("relativeWithDisease" + i)));
    }

}
