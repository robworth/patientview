package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.Karotype;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiagnosisPanel extends Panel {

    public DiagnosisPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        ClinicalPresentation tempClinicalPresentation = new ClinicalPresentation();
        tempClinicalPresentation.setName("temp");
        List<ClinicalPresentation> clinicalPresentationList = Arrays.asList(tempClinicalPresentation);

        IChoiceRenderer<ClinicalPresentation> clinicalPresentationChoiceRenderer =
                new ChoiceRenderer<ClinicalPresentation>("name");

        final DropDownChoice<ClinicalPresentation> clinicalPresentationA =
                new DropDownChoice<ClinicalPresentation>("clinicalPresentationA", clinicalPresentationList,
                        clinicalPresentationChoiceRenderer);
        clinicalPresentationA.setOutputMarkupId(true);
        clinicalPresentationA.setOutputMarkupPlaceholderTag(true);

        final Form<Diagnosis> form =
                new Form<Diagnosis>("form", new CompoundPropertyModel<Diagnosis>(new Diagnosis())) {
                    @Override
                    protected void onValidateModelObjects() {
                        super.onValidateModelObjects();
                        Diagnosis diagnosis = getModelObject();
                        ClinicalPresentation presentationA = diagnosis.getClinicalPresentationA();
                        ClinicalPresentation presentationB = diagnosis.getClinicalPresentationB();

                        // Validate that the two aren't the same
                        if (presentationA != null && presentationB != null && presentationA.equals(presentationB)) {
                            clinicalPresentationA.error("A and B cannot be the same");
                        }
                    }
                };
        add(form);

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        form.add(new DropDownChoice<DiagnosisCode>("diagnosisCode"));
        form.add(new TextArea("text"));
        form.add(new Label("diagnosisOrBiopsy", "Date of original biopsy"));

        RadarDateTextField biopsyDate =
                new RadarDateTextField("biopsyDate", RadarApplication.DATE_PATTERN, form, componentsToUpdate);
        form.add(biopsyDate);

        RadarDateTextField esrfDate =
                new RadarDateTextField("esrfDate", RadarApplication.DATE_PATTERN, form, componentsToUpdate);
        form.add(esrfDate);

        form.add(new TextField("ageAtDiagnosis"));
        form.add(new CheckBox("prepubertalAtDiagnosis"));

        final RadarTextFieldWithValidation heightAtDiagnosis =
                new RadarTextFieldWithValidation("heightAtDiagnosis", new RangeValidator<Double>(35.0, 185.0), form,
                        componentsToUpdate);
        form.add(heightAtDiagnosis);


        final DropDownChoice<ClinicalPresentation> clinicalPresentationB =
                new DropDownChoice<ClinicalPresentation>("clinicalPresentationB", clinicalPresentationList,
                        clinicalPresentationChoiceRenderer);
        clinicalPresentationB.setOutputMarkupId(true);
        clinicalPresentationB.setOutputMarkupPlaceholderTag(true);

        form.add(clinicalPresentationA, clinicalPresentationB);
        form.add(new RadarDateTextField("onsetSymptomsDate", RadarApplication.DATE_PATTERN, form, componentsToUpdate));

        ComponentFeedbackPanel clinicalPresentationFeedback =
                new ComponentFeedbackPanel("clinicalPresentationFeedback", clinicalPresentationA);
        clinicalPresentationFeedback.setOutputMarkupId(true);
        clinicalPresentationFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(clinicalPresentationFeedback);

        // Steroid resistance radio groups
        RadioGroup steroidContainer = new RadioGroup("steroidResistance");
        steroidContainer.add(new Radio("primarySteroidResistance", new Model(Diagnosis.SteroidResistance.PRIMARY)));
        steroidContainer.add(new Radio("secondarySteroidResistance", new Model(Diagnosis.SteroidResistance.SECONDARY)));
        steroidContainer.add(new Radio("presumedSteroidResistance", new Model(Diagnosis.SteroidResistance.PRESUMED)));
        steroidContainer.add(new Radio("biopsyProven", new Model(Diagnosis.SteroidResistance.BPS)));
        form.add(steroidContainer);

        // Additional significant diagnosis
        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Biopsy Diagnosis visibilites...
        // The label changes according to some logic, will finish later
        DropDownChoice biopsyDiagnosis = new DropDownChoice("biopsyProvenDiagnosis");
        FormComponentLabel biopsyDiagnosisLabel = new FormComponentLabel("biopsyDiagnosisLabel", biopsyDiagnosis);
        form.add(biopsyDiagnosis, biopsyDiagnosisLabel);

        // Gene mutations
        form.add(new DiagnosisGeneMutationPanel("nphs1Container"));
        form.add(new DiagnosisGeneMutationPanel("nphs2Container"));
        form.add(new DiagnosisGeneMutationPanel("nphs3Container"));
        form.add(new DiagnosisGeneMutationPanel("wt1Container"));
        form.add(new DiagnosisGeneMutationPanel("cd2apContainer"));
        form.add(new DiagnosisGeneMutationPanel("trpc6Container"));
        form.add(new DiagnosisGeneMutationPanel("actn4Container"));
        form.add(new DiagnosisGeneMutationPanel("lamb2Container"));
        form.add(new DiagnosisGeneMutationPanel("otherContainer"));

        // Other gene mutation container
        MarkupContainer otherGeneMutationContainer = new WebMarkupContainer("otherGeneMutationContainer") {
            @Override
            public boolean isVisible() {
                // Only show if other is checked Y
                return Diagnosis.MutationYorN.Y.equals(form.getModelObject().getMutationYorN9());
            }
        };
        otherGeneMutationContainer.setOutputMarkupId(true);
        otherGeneMutationContainer.setOutputMarkupPlaceholderTag(true);
        otherGeneMutationContainer.add(new TextArea("otherGeneMutation"));
        form.add(otherGeneMutationContainer);

        // Add Karotype
        form.add(new DropDownChoice<Karotype>("karotype"));

        // Parental consanguinity and family history
        form.add(new DropDownChoice("parentalConsanguinity"));
        form.add(new DropDownChoice("familyHistory"));

        // Family history containers
        form.add(new DiagnosisRelativePanel("relative1Container", 1));
        form.add(new DiagnosisRelativePanel("relative2Container", 2));
        form.add(new DiagnosisRelativePanel("relative3Container", 3));
        form.add(new DiagnosisRelativePanel("relative4Container", 4));
        form.add(new DiagnosisRelativePanel("relative5Container", 5));
        form.add(new DiagnosisRelativePanel("relative6Container", 6));

        componentsToUpdate.add(clinicalPresentationFeedback);

        DiagnosisAjaxSubmitLink save = new DiagnosisAjaxSubmitLink("save") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };

        DiagnosisAjaxSubmitLink saveDown = new DiagnosisAjaxSubmitLink("saveDown") {

            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };


        form.add(save, saveDown);
    }


    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DIAGNOSIS);
    }

    private abstract class DiagnosisAjaxSubmitLink extends AjaxSubmitLink {

        public DiagnosisAjaxSubmitLink(String id) {
            super(id);
        }

        @Override
        public void onSubmit(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        protected abstract List<? extends Component> getComponentsToUpdate();
    }
}
