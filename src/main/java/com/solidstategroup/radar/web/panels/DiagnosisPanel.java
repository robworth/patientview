package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.Karotype;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.ClinicalPresentationDropDownChoice;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.commons.lang.StringUtils;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiagnosisPanel extends Panel {

    @SpringBean
    private DiagnosisDao diagnosisDao;

    public DiagnosisPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Set up model
        CompoundPropertyModel<Diagnosis> model;

        // Set up loadable detachable, working for null radar numbers (new patients) and existing
        model = new CompoundPropertyModel<Diagnosis>(new LoadableDetachableModel<Diagnosis>() {
            @Override
            protected Diagnosis load() {
                if (radarNumberModel.getObject() != null) {
                    return diagnosisDao.getDiagnosisByRadarNumber(radarNumberModel.getObject());
                } else {
                    return new Diagnosis();
                }
            }
        });

        // Clinical presentation A - goes here in the file as referenced in form submit
        final DropDownChoice<ClinicalPresentation> clinicalPresentationA =
                new ClinicalPresentationDropDownChoice("clinicalPresentationA");
        clinicalPresentationA.setOutputMarkupId(true);
        clinicalPresentationA.setOutputMarkupPlaceholderTag(true);

        final Form<Diagnosis> form =
                new Form<Diagnosis>("form", model) {
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

        form.add(new DropDownChoice<DiagnosisCode>("diagnosisCode", diagnosisDao.getDiagnosisCodes(),
                new ChoiceRenderer<DiagnosisCode>("abbreviation", "id")));
        form.add(new TextArea("text"));
        form.add(new Label("diagnosisOrBiopsy", "Date of original biopsy"));

        RadarDateTextField biopsyDate =
                new RadarDateTextField("biopsyDate", form, componentsToUpdate);
        form.add(biopsyDate);

        RadarDateTextField esrfDate =
                new RadarDateTextField("esrfDate", form, componentsToUpdate);
        form.add(esrfDate);

        form.add(new TextField("ageAtDiagnosis"));
        form.add(new CheckBox("prepubertalAtDiagnosis"));

        final RadarTextFieldWithValidation heightAtDiagnosis =
                new RadarTextFieldWithValidation("heightAtDiagnosis", new RangeValidator<Double>(35.0, 185.0), form,
                        componentsToUpdate);
        form.add(heightAtDiagnosis);

        // Clinical presentation B - A is further up the file
        final DropDownChoice<ClinicalPresentation> clinicalPresentationB =
                new ClinicalPresentationDropDownChoice("clinicalPresentationB");
        clinicalPresentationB.setOutputMarkupId(true);
        clinicalPresentationB.setOutputMarkupPlaceholderTag(true);

        form.add(clinicalPresentationA, clinicalPresentationB);
        form.add(new RadarDateTextField("onsetSymptomsDate", form, componentsToUpdate));

        ComponentFeedbackPanel clinicalPresentationFeedback =
                new ComponentFeedbackPanel("clinicalPresentationFeedback", clinicalPresentationA);
        clinicalPresentationFeedback.setOutputMarkupId(true);
        clinicalPresentationFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(clinicalPresentationFeedback);

        // Steroid resistance radio groups
        RadioGroup steroidContainer = new RadioGroup("steroidResistance");
        steroidContainer.add(new Radio<Diagnosis.SteroidResistance>("primarySteroidResistance",
                new Model<Diagnosis.SteroidResistance>(Diagnosis.SteroidResistance.PRIMARY)));
        steroidContainer.add(new Radio<Diagnosis.SteroidResistance>("secondarySteroidResistance",
                new Model<Diagnosis.SteroidResistance>(Diagnosis.SteroidResistance.SECONDARY)));
        steroidContainer.add(new Radio<Diagnosis.SteroidResistance>("presumedSteroidResistance",
                new Model<Diagnosis.SteroidResistance>(Diagnosis.SteroidResistance.PRESUMED)));
        steroidContainer.add(new Radio<Diagnosis.SteroidResistance>("biopsyProven",
                new Model<Diagnosis.SteroidResistance>(Diagnosis.SteroidResistance.BPS)));
        form.add(steroidContainer);

        // Additional significant diagnosis
        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Biopsy Diagnosis visibilities...
        // The label changes according to some logic, will finish later
        DropDownChoice biopsyDiagnosis = new YesNoDropDownChoice("biopsyProvenDiagnosis");
        FormComponentLabel biopsyDiagnosisLabel = new FormComponentLabel("biopsyDiagnosisLabel", biopsyDiagnosis);
        form.add(biopsyDiagnosis, biopsyDiagnosisLabel);

        // Gene mutations
        form.add(new DiagnosisGeneMutationPanel("nphs1Container", 1));
        form.add(new DiagnosisGeneMutationPanel("nphs2Container", 2));
        form.add(new DiagnosisGeneMutationPanel("nphs3Container", 3));
        form.add(new DiagnosisGeneMutationPanel("wt1Container", 4));
        form.add(new DiagnosisGeneMutationPanel("cd2apContainer", 5));
        form.add(new DiagnosisGeneMutationPanel("trpc6Container", 6));
        form.add(new DiagnosisGeneMutationPanel("actn4Container", 7));
        form.add(new DiagnosisGeneMutationPanel("lamb2Container", 8));
        form.add(new DiagnosisGeneMutationPanel("otherContainer", 9));

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
        form.add(new DropDownChoice<Karotype>("karotype", diagnosisDao.getKarotypes(),
                new ChoiceRenderer<Karotype>("description", "id")));

        // Parental consanguinity and family history
        form.add(new YesNoDropDownChoice("parentalConsanguinity"));
        form.add(new YesNoDropDownChoice("familyHistory"));

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

    private class YesNoDropDownChoice extends DropDownChoice<Diagnosis.YesNo> {
        public YesNoDropDownChoice(String id) {
            super(id);
            setChoices(Arrays.asList(Diagnosis.YesNo.values()));

            // Capitalise value
            setChoiceRenderer(new IChoiceRenderer<Diagnosis.YesNo>() {
                public Object getDisplayValue(Diagnosis.YesNo object) {
                    return StringUtils.capitalize(object.toString().toLowerCase());
                }

                public String getIdValue(Diagnosis.YesNo object, int index) {
                    return String.valueOf(object.getId());
                }
            });
        }
    }
}
