package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Diagnosis;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public class PatientDiagnosisPanel extends Panel {

    public PatientDiagnosisPanel(String id) {
        super(id);

        Form<Diagnosis> form = new Form<Diagnosis>("form");
        add(form);
    }
}
