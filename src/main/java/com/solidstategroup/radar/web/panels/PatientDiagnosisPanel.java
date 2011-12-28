package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class PatientDiagnosisPanel extends Panel {

    public PatientDiagnosisPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Diagnosis> form = new Form<Diagnosis>("form", new CompoundPropertyModel<Diagnosis>(new Diagnosis()));
        add(form);

        form.add(new DropDownChoice<DiagnosisCode>("diagnosisCode"));
        form.add(new TextArea("text"));
        form.add(new DateTextField("biopsyDate"));
        form.add(new DateTextField("esrfDate"));
        form.add(new TextField("ageAtDiagnosis"));
        form.add(new CheckBox("prepubertalAtDiagnosis"));
        form.add(new TextField("heightAtDiagnosis"));

        form.add(new DropDownChoice<ClinicalPresentation>("clinicalPresentationA"));
        form.add(new DropDownChoice<ClinicalPresentation>("clinicalPresentationB"));

        form.add(new DateTextField("onsetSymptomsDate"));

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
        form.add(new PatientDiagnosisGeneMutationPanel("nphs1Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("nphs2Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("nphs3Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("wt1Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("cd2apContainer"));
        form.add(new PatientDiagnosisGeneMutationPanel("trpc6Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("actn4Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("lamb2Container"));
        form.add(new PatientDiagnosisGeneMutationPanel("otherContainer"));

        // Parental consanguinity and family history
        form.add(new DropDownChoice("parentalConsanguinity"));
        form.add(new DropDownChoice("familyHistory"));

        // Family history containers
        form.add(new PatientDiagnosisRelativePanel("relative1Container", 1));
        form.add(new PatientDiagnosisRelativePanel("relative2Container", 2));
        form.add(new PatientDiagnosisRelativePanel("relative3Container", 3));
        form.add(new PatientDiagnosisRelativePanel("relative4Container", 4));
        form.add(new PatientDiagnosisRelativePanel("relative5Container", 5));
        form.add(new PatientDiagnosisRelativePanel("relative6Container", 6));
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DIAGNOSIS);
    }
}
