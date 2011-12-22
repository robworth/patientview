package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Pathology;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

public class PatientPathologyPanel extends Panel {

    public PatientPathologyPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Pathology> form = new Form<Pathology>("form");

        // Add inputs
        form.add(new TextField("sampleLabNumber"));
        
        form.add(new TextArea("interstitalInflmatoryInfilitrate"));
        form.add(new TextArea("arterialAbnormalities"));
        form.add(new TextArea("immunohistologicalFindings"));
        form.add(new TextArea("electronMicroscopicFindings"));

        form.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                // Todo: Implement
            }
        });

        form.add(new TextField("estimatedTubules"));
        form.add(new TextField("measuredTubules"));
        form.add(new TextArea("tubulesOtherFeature"));

        add(form);
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.PATHOLOGY);
    }
}
