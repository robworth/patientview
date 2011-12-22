package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public class PatientPathologyPanel extends Panel {

    public PatientPathologyPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Diagnosis> form = new Form<Diagnosis>("form");
        add(form);
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.PATHOLOGY);
    }
}
