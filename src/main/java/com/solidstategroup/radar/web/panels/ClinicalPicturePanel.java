package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.ClinicalData;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class ClinicalPicturePanel extends Panel {

    public ClinicalPicturePanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<ClinicalData> form =
                new Form<ClinicalData>("form", new CompoundPropertyModel<ClinicalData>(new ClinicalData()));
        add(form);
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.CLINICAL_PICTURE);
    }
}
