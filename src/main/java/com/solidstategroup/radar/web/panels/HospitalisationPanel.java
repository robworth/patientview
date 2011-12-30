package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Hospitalisation;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class HospitalisationPanel extends Panel {
    public HospitalisationPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Hospitalisation> form =
                new Form<Hospitalisation>("form", new CompoundPropertyModel<Hospitalisation>(new Hospitalisation()));
        add(form);

        form.add(new DateTextField("dateOfAdmission"));
        form.add(new DateTextField("dateOfDischarge"));
        form.add(new TextArea("reason"));
        form.add(new TextArea("comments"));
        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                // Todo: Implement
            }
        });
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.HOSPITALISATION);
    }
}
