package com.solidstategroup.radar.web.panels.alport;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.alport.Medicine;
import com.solidstategroup.radar.service.alport.MedicineManager;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.ComponentHelper;
import com.solidstategroup.radar.web.panels.PatientDetailPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class MedicinePanel extends Panel {

    @SpringBean
    private MedicineManager medicineManager;

    public MedicinePanel(final String id, final Medicine medicine, final Demographics demographics) {
        super(id);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // main model for this tab
        IModel<Medicine> model = new Model<Medicine>(medicine);

        // components to update on ajax refresh
        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        // general feedback for messages that are not to do with a certain component in the form
        final FeedbackPanel formFeedback = new FeedbackPanel("formFeedbackPanel");
        formFeedback.setOutputMarkupId(true);
        formFeedback.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(formFeedback);

        Form<Medicine> form = new Form<Medicine>("form", new CompoundPropertyModel<Medicine>(model)) {
            @Override
            protected void onSubmit() {
                Medicine medicine = getModelObject();
            }
        };

        add(form);

        // have to set the generic feedback panel to only pick up msgs for them form
        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(form);
        formFeedback.setFilter(filter);
        form.add(formFeedback);

        // add the patient detail bar to the tab
        PatientDetailPanel patientDetail = new PatientDetailPanel("patientDetail", demographics, "Medicine");
        patientDetail.setOutputMarkupId(true);
        form.add(patientDetail);
        componentsToUpdateList.add(patientDetail);

        form.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
                target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
                target.add(formFeedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
                target.add(formFeedback);
            }
        });
    }
}
