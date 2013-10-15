package org.patientview.radar.web.panels.hnf1b;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.patientview.model.Patient;
import org.patientview.radar.model.alport.Deafness;
import org.patientview.radar.service.alport.DeafnessManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.panels.PatientDetailPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class ProteinuriaPanel extends Panel {

    @SpringBean
    private DeafnessManager deafnessManager;

    public ProteinuriaPanel(final String id, final Patient patient) {
        super(id);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Deafness deafness = null;

        if (patient.hasValidId()) {
            deafness = deafnessManager.get(patient.getId());
        }

        if (deafness == null) {
            deafness = new Deafness();
            deafness.setRadarNo(patient.getId());
        }

        // main model for this tab
        IModel<Deafness> model = new Model<Deafness>(deafness);

        // components to update on ajax refresh
        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        // general feedback for messages that are not to do with a certain component in the form
        final FeedbackPanel formFeedback = new FeedbackPanel("formFeedbackPanel");
        formFeedback.setOutputMarkupId(true);
        formFeedback.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(formFeedback);

        Form<Deafness> form = new Form<Deafness>("form", new CompoundPropertyModel<Deafness>(model)) {
            @Override
            protected void onSubmit() {
                Deafness deafness = getModelObject();

                if (deafness.getEvidenceOfDeafness() == null) {
                    error("Please select any evidence of deafness");
                }

                if (!hasError()) {
                    deafness.setRadarNo(patient.getId());
                    deafnessManager.save(deafness);
                }
            }
        };

        add(form);

        // Date picker
        DateTextField dateProblemFirstNoticed = new RadarRequiredDateTextField("dateProblemFirstNoticed",
                form, componentsToUpdateList);
        DateTextField dateStartedUsingHearingAid = new RadarRequiredDateTextField("dateStartedUsingHearingAid",
                form, componentsToUpdateList);

        // have to set the generic feedback panel to only pick up msgs for them form
        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(form);
        formFeedback.setFilter(filter);
        form.add(formFeedback);

        // add the patient detail bar to the tab
        PatientDetailPanel patientDetail = new PatientDetailPanel("patientDetail", patient, "Deafness");
        patientDetail.setOutputMarkupId(true);
        form.add(patientDetail);
        componentsToUpdateList.add(patientDetail);

        RadioGroup<Deafness.EvidenceOfDeafness> evidenceOfDeafnessRadioGroup =
                new RadioGroup<Deafness.EvidenceOfDeafness>("evidenceOfDeafness");
        form.add(evidenceOfDeafnessRadioGroup);

        evidenceOfDeafnessRadioGroup.add(new Radio<Deafness.EvidenceOfDeafness>("evidenceOfDeafnessNo",
                new Model<Deafness.EvidenceOfDeafness>(Deafness.EvidenceOfDeafness.NO)));
        evidenceOfDeafnessRadioGroup.add(new Radio<Deafness.EvidenceOfDeafness>("evidenceOfDeafnessYesMinor",
                new Model<Deafness.EvidenceOfDeafness>(Deafness.EvidenceOfDeafness.YES_MINOR)));
        evidenceOfDeafnessRadioGroup.add(new Radio<Deafness.EvidenceOfDeafness>("evidenceOfDeafnessYesHearingAidNeeded",
                new Model<Deafness.EvidenceOfDeafness>(Deafness.EvidenceOfDeafness.YES_HEARING_AID_NEEDED)));

        final Label successMessage = RadarComponentFactory.getSuccessMessageLabel("successMessage", form,
                componentsToUpdateList);
        Label errorMessage = RadarComponentFactory.getErrorMessageLabel("errorMessage", form, componentsToUpdateList);

        form.add(new AjaxSubmitLink("saveTop") {
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

        form.add(new AjaxSubmitLink("saveBottom") {
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
