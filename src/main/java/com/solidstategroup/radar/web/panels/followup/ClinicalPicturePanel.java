package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.model.ClinicalData;
import com.solidstategroup.radar.web.components.PhenotypeChooser;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
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

public class ClinicalPicturePanel extends Panel {

    public ClinicalPicturePanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Add the visits drop down
        add(new DropDownChoice("previousVisits"));

        // Button for adding new record
        add(new AjaxLink("addNewRecord") {
            @Override
            public void onClick(AjaxRequestTarget target) {
            }
        });

        // Container for details once selected or new record button pressed
        MarkupContainer clinicalFeaturesContainer = new WebMarkupContainer("clinicalFeaturesContainer");
        add(clinicalFeaturesContainer);

        Form<ClinicalData> form =
                new Form<ClinicalData>("form", new CompoundPropertyModel<ClinicalData>(new ClinicalData()));
        clinicalFeaturesContainer.add(form);

        // Save button
        AjaxSubmitLink saveLink = new AjaxSubmitLink("saveLink") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        };
        form.add(saveLink);

        // Height, weight, blood pressure
        form.add(new TextField("height"));
        form.add(new TextField("weight"));
        form.add(new TextField("systolicBloodPressure"));
        form.add(new TextField("diastolicBloodPressure"));
        form.add(new TextField("meanArterialPressure"));

        // Phenotypes
        // Todo: Only visible for certain diagnosis
        MarkupContainer phenotypesContainer = new WebMarkupContainer("phenotypesContainer");
        phenotypesContainer.add(new PhenotypeChooser("phenotype1"));
        phenotypesContainer.add(new PhenotypeChooser("phenotype2"));
        phenotypesContainer.add(new PhenotypeChooser("phenotype3"));
        phenotypesContainer.add(new PhenotypeChooser("phenotype4"));
        form.add(phenotypesContainer);

        // Comments
        form.add(new TextArea("comments"));

        // Course of disease - from current code this is never enabled
        MarkupContainer courseOfDiseaseContainer = new WebMarkupContainer("courseOfDiseaseContainer");
        courseOfDiseaseContainer.setVisible(false);
        courseOfDiseaseContainer.add(new DropDownChoice<ClinicalData.CourseOfDisease>("courseOfDisease"));
        form.add(courseOfDiseaseContainer);

        // Additional significant diagnosis 1 and 2
        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Complications
        form.add(new YesNoRadioGroup("infectionNecessitatingHospitalisation", false, false));
        MarkupContainer infectionDetailContainer = new WebMarkupContainer("infectionDetailContainer");
        infectionDetailContainer.add(new TextArea("infectionDetail"));
        form.add(infectionDetailContainer);

        form.add(new YesNoRadioGroup("complicationThrombosis", false, false));
        MarkupContainer complicationThrombosisDetailContainer =
                new WebMarkupContainer("complicationThrombosisContainer");
        complicationThrombosisDetailContainer.add(new TextArea("complicationThrombosisDetail"));
        form.add(complicationThrombosisDetailContainer);

        // Hypertension
        form.add(new YesNoRadioGroup("hypertension", true));

        // CKD stage
        form.add(new CkdStageRadioGroup("ckdStage"));

        // Listed for transplant?
        form.add(new YesNoRadioGroup("listedForTransplant"));

        // Save button at bottom of page
        AjaxSubmitLink saveLinkBottom = new AjaxSubmitLink("saveLinkBottom") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        };
        form.add(saveLinkBottom);
    }

    private class CkdStageRadioGroup extends RadioGroup<ClinicalData.CkdStage> {
        private CkdStageRadioGroup(String id) {
            super(id);

            Radio<ClinicalData.CkdStage> ckdStageOne =
                    new Radio<ClinicalData.CkdStage>("one",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.ONE));
            add(ckdStageOne, new FormComponentLabel("oneLabel", ckdStageOne));

            Radio<ClinicalData.CkdStage> ckdStageTwo =
                    new Radio<ClinicalData.CkdStage>("two",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.TWO));
            add(ckdStageTwo, new FormComponentLabel("twoLabel", ckdStageTwo));

            Radio<ClinicalData.CkdStage> ckdStageThree = new Radio<ClinicalData.CkdStage>("three",
                    new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.THREE));
            add(ckdStageThree, new FormComponentLabel("threeLabel", ckdStageThree));

            Radio<ClinicalData.CkdStage> ckdStageFour =
                    new Radio<ClinicalData.CkdStage>("four",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.FOUR));
            add(ckdStageFour, new FormComponentLabel("fourLabel", ckdStageFour));

            Radio<ClinicalData.CkdStage> ckdStageFive =
                    new Radio<ClinicalData.CkdStage>("five",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.FIVE));
            add(ckdStageFive, new FormComponentLabel("fiveLabel", ckdStageFive));

            Radio<ClinicalData.CkdStage> ckdStageUnknown = new Radio<ClinicalData.CkdStage>("unknown",
                    new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.UNKNOWN));
            add(ckdStageUnknown, new FormComponentLabel("unknownLabel", ckdStageUnknown));
        }
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.CLINICAL_PICTURE);
    }
}
