package org.patientview.radar.web.panels.hnf1b;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.enums.YesNo;
import org.patientview.radar.model.hnf1b.HNF1BMisc;
import org.patientview.radar.service.hnf1b.HNF1BMiscManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.panels.PatientDetailPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class HNF1BMiscPanel extends Panel {

    @SpringBean
    private HNF1BMiscManager hnf1BMiscManager;

    public HNF1BMiscPanel(final String id, final Demographics demographics) {
        super(id);

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        HNF1BMisc hnf1BMisc = null;

        if (demographics.hasValidId()) {
            hnf1BMisc = hnf1BMiscManager.get(demographics.getId());
        }

        if (hnf1BMisc == null) {
            hnf1BMisc = new HNF1BMisc();
            hnf1BMisc.setRadarNo(demographics.getId());
        }

        // main model for this tab
        IModel<HNF1BMisc> model = new Model<HNF1BMisc>(hnf1BMisc);

        // components to update on ajax refresh
        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        // general feedback for messages that are not to do with a certain component in the form
        final FeedbackPanel formFeedback = new FeedbackPanel("formFeedbackPanel");
        formFeedback.setOutputMarkupId(true);
        formFeedback.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(formFeedback);

        Form<HNF1BMisc> form = new Form<HNF1BMisc>("form", new CompoundPropertyModel<HNF1BMisc>(model)) {
            @Override
            protected void onSubmit() {
                HNF1BMisc hnf1BMisc = getModelObject();

                // check all the radio buttons have values
                if (hnf1BMisc.getRenalCysts() == null
                        || hnf1BMisc.getSingleKidney() == null
                        || hnf1BMisc.getOtherRenalMalformations() == null
                        || hnf1BMisc.getDiabetes() == null
                        || hnf1BMisc.getGout() == null
                        || hnf1BMisc.getGenitalMalformation() == null) {
                    error("Please select a value for each radio button");

                } else {
                    // if all the radios are complete check that we have any additional required data
                    if (hnf1BMisc.getOtherRenalMalformations().equals(YesNo.YES)
                            && !StringUtils.hasText(hnf1BMisc.getOtherRenalMalformationsDetails())) {
                        error("Please provide other renal malformation details");
                    }

                    if (hnf1BMisc.getGenitalMalformation().equals(YesNo.YES)
                            && !StringUtils.hasText(hnf1BMisc.getGenitalMalformationDetails())) {
                        error("Please provide genital malformation details");
                    }
                }

                if (!hasError()) {
                    hnf1BMisc.setRadarNo(demographics.getId());
                    hnf1BMiscManager.save(hnf1BMisc);
                }
            }
        };

        add(form);

        int maxAge = 90;
        int minAge = 1;

        // the list has to be strings so we can have the first one as N/A
        List<String> ages = new ArrayList<String>();
        ages.add("N/A");

        for (int x = minAge; x <= maxAge; x++) {
            ages.add(Integer.toString(x));
        }

        // have to set the generic feedback panel to only pick up msgs for them form
        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(form);
        formFeedback.setFilter(filter);
        form.add(formFeedback);

        // add the patient detail bar to the tab
        PatientDetailPanel patientDetail = new PatientDetailPanel("patientDetail", demographics, "HNF1B Misc");
        patientDetail.setOutputMarkupId(true);
        form.add(patientDetail);
        componentsToUpdateList.add(patientDetail);

        RadioGroup<YesNo> renalCystsRadioGroup = new RadioGroup<YesNo>("renalCysts");
        form.add(renalCystsRadioGroup);
        renalCystsRadioGroup.add(new Radio<YesNo>("renalCystsNo", new Model<YesNo>(YesNo.NO)));
        renalCystsRadioGroup.add(new Radio<YesNo>("renalCystsYes", new Model<YesNo>(YesNo.YES)));

        RadioGroup<YesNo> singleKidneyRadioGroup = new RadioGroup<YesNo>("singleKidney");
        form.add(singleKidneyRadioGroup);
        singleKidneyRadioGroup.add(new Radio<YesNo>("singleKidneyNo", new Model<YesNo>(YesNo.NO)));
        singleKidneyRadioGroup.add(new Radio<YesNo>("singleKidneyYes", new Model<YesNo>(YesNo.YES)));

        RadioGroup<YesNo> otherRenalMalformationsRadioGroup = new RadioGroup<YesNo>("otherRenalMalformations");
        form.add(otherRenalMalformationsRadioGroup);
        otherRenalMalformationsRadioGroup.add(new Radio<YesNo>("otherRenalMalformationsNo",
                new Model<YesNo>(YesNo.NO)));
        otherRenalMalformationsRadioGroup.add(new Radio<YesNo>("otherRenalMalformationsYes",
                new Model<YesNo>(YesNo.YES)));

        TextArea otherClinicianAndContactInfo = new TextArea("otherRenalMalformationsDetails");
        form.add(otherClinicianAndContactInfo);

        RadioGroup<YesNo> diabetesRadioGroup = new RadioGroup<YesNo>("diabetes");
        form.add(diabetesRadioGroup);
        diabetesRadioGroup.add(new Radio<YesNo>("diabetesNo", new Model<YesNo>(YesNo.NO)));
        diabetesRadioGroup.add(new Radio<YesNo>("diabetesYes", new Model<YesNo>(YesNo.YES)));

        DropDownChoice<String> ageAtDiabetesDiagnosisDropDown =
                new DropDownChoice<String>("ageAtDiabetesDiagnosis",
                        new PropertyModel<String>(model, "ageAtDiabetesDiagnosisAsString"), ages);
        form.add(ageAtDiabetesDiagnosisDropDown);

        RadioGroup<YesNo> goutRadioGroup = new RadioGroup<YesNo>("gout");
        form.add(goutRadioGroup);
        goutRadioGroup.add(new Radio<YesNo>("goutNo", new Model<YesNo>(YesNo.NO)));
        goutRadioGroup.add(new Radio<YesNo>("goutYes", new Model<YesNo>(YesNo.YES)));

        DropDownChoice<String> ageAtGoutDiagnosisDropDown =
                new DropDownChoice<String>("ageAtGoutDiagnosis",
                        new PropertyModel<String>(model, "ageAtGoutDiagnosisAsString"), ages);
        form.add(ageAtGoutDiagnosisDropDown);

        RadioGroup<YesNo> genitalMalformationRadioGroup = new RadioGroup<YesNo>("genitalMalformation");
        form.add(genitalMalformationRadioGroup);
        genitalMalformationRadioGroup.add(new Radio<YesNo>("genitalMalformationNo", new Model<YesNo>(YesNo.NO)));
        genitalMalformationRadioGroup.add(new Radio<YesNo>("genitalMalformationYes", new Model<YesNo>(YesNo.YES)));

        TextArea genitalMalformationDetails = new TextArea("genitalMalformationDetails");
        form.add(genitalMalformationDetails);

        final Label successMessageTop = RadarComponentFactory.getSuccessMessageLabel("successMessageTop", form,
                componentsToUpdateList);
        Label errorMessageTop = RadarComponentFactory.getErrorMessageLabel("errorMessageTop", form,
                componentsToUpdateList);

        final Label successMessageBottom = RadarComponentFactory.getSuccessMessageLabel("successMessageBottom", form,
                        componentsToUpdateList);
        Label errorMessageBottom = RadarComponentFactory.getErrorMessageLabel("errorMessageBottom", form,
                componentsToUpdateList);

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
