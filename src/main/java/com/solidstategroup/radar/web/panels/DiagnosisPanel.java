package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDatePicker;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.Arrays;
import java.util.List;


public class DiagnosisPanel extends Panel {

    public DiagnosisPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Diagnosis> form = new Form<Diagnosis>("form", new CompoundPropertyModel<Diagnosis>(new Diagnosis())) {
            @Override
            protected void onValidateModelObjects() {
                super.onValidateModelObjects();
                // validate same clinical presentation here
            }
        };
        add(form);

        form.add(new DropDownChoice<DiagnosisCode>("diagnosisCode"));
        form.add(new TextArea("text"));
        form.add(new Label("diagnosisOrBiopsy", "Date of original biopsy"));

        DateTextField biopsyDate = DateTextField.forDatePattern("biopsyDate", RadarApplication.DATE_PATTERN);
        biopsyDate.add(new RadarDatePicker());
        form.add(biopsyDate);

        final ComponentFeedbackPanel biopsyDateFeedback = new ComponentFeedbackPanel("biopsyDateFeedback", biopsyDate);
        biopsyDateFeedback.setOutputMarkupId(true);
        biopsyDateFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(biopsyDateFeedback);


        DateTextField esrfDate = DateTextField.forDatePattern("esrfDate", RadarApplication.DATE_PATTERN);
        form.add(esrfDate);
        esrfDate.add(new RadarDatePicker());

        final ComponentFeedbackPanel esrfDateFeedback = new ComponentFeedbackPanel("esrfDateFeedback", esrfDate);
        esrfDateFeedback.setOutputMarkupId(true);
        esrfDateFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(esrfDateFeedback);

        form.add(new TextField("ageAtDiagnosis"));
        form.add(new CheckBox("prepubertalAtDiagnosis"));
        final TextField heightAtDiagnosis = new TextField("heightAtDiagnosis");

        heightAtDiagnosis.add(new AjaxEventBehavior("onchange") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                heightAtDiagnosis.add(new RangeValidator<Double>(35.0, 185.0));
            }
        });

        form.add(heightAtDiagnosis);

        final ComponentFeedbackPanel heightAtDiagnosisFeedback = new ComponentFeedbackPanel("heightAtDiagnosisFeedback", heightAtDiagnosis);
        heightAtDiagnosisFeedback.setOutputMarkupId(true);
        heightAtDiagnosisFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(heightAtDiagnosisFeedback);

        ClinicalPresentation tempClinicalPresentation = new ClinicalPresentation();
        tempClinicalPresentation.setName("temp");
        List<ClinicalPresentation> clinicalPresentationList = Arrays.asList(tempClinicalPresentation);


        final DropDownChoice <ClinicalPresentation> clinicalPresentationA = new DropDownChoice<ClinicalPresentation>("clinicalPresentationA", clinicalPresentationList);
        clinicalPresentationA.setOutputMarkupId(true);
        clinicalPresentationA.setOutputMarkupPlaceholderTag(true);

        final DropDownChoice <ClinicalPresentation> clinicalPresentationB = new DropDownChoice<ClinicalPresentation>("clinicalPresentationB", clinicalPresentationList);
        clinicalPresentationB.setOutputMarkupId(true);
        clinicalPresentationB.setOutputMarkupPlaceholderTag(true);

        form.add(clinicalPresentationA, clinicalPresentationB);
        form.add(DateTextField.forDatePattern("onsetSymptomsDate", RadarApplication.DATE_PATTERN));

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

        final List<? extends Component> componentsToUpdateOnError = Arrays.asList(heightAtDiagnosisFeedback, biopsyDateFeedback, esrfDateFeedback);

        DiagnosisAjaxSubmitLink save = new DiagnosisAjaxSubmitLink("save") {
            @Override
            protected List<? extends Component> getComponentsToUpdateOnError() {
                return componentsToUpdateOnError;
            }
        };

        DiagnosisAjaxSubmitLink saveDown = new DiagnosisAjaxSubmitLink("saveDown") {

            @Override
            protected List<? extends Component> getComponentsToUpdateOnError() {
                return componentsToUpdateOnError;
            }
        };


        form.add(save, saveDown);
    }


    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DIAGNOSIS);
    }

    private abstract class DiagnosisAjaxSubmitLink extends AjaxSubmitLink{

        public DiagnosisAjaxSubmitLink(String id) {
            super(id);
        }

        @Override
        public void onSubmit(AjaxRequestTarget target, Form<?> form) {
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdateOnError().toArray(new Component[getComponentsToUpdateOnError().size()]));
        }

        protected abstract List<? extends Component> getComponentsToUpdateOnError();
    }
}
