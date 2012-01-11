package com.solidstategroup.radar.web.panels.subtabs;

import com.solidstategroup.radar.model.sequenced.LabData;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryResultsPanel extends Panel {

    public LaboratoryResultsPanel(String id, IModel<Long> radarNumberModel) {
        super(id);

        List<Component> feedbackList = new ArrayList<Component>();
        final Form<LabData> form = new Form<LabData>("form", new CompoundPropertyModel<LabData>(new LabData()));
        add(form);

        RadarRequiredDateTextField labResultsDate = new RadarRequiredDateTextField("date", form, feedbackList);
        form.add(labResultsDate);

        // Blood fields
        form.add(new RadarTextFieldWithValidation("hb", new RangeValidator<Double>(2.0, 20.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("wbc", new RangeValidator<Double>(0.1, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("neutrophils", new RangeValidator<Double>(0.1, 80.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("platelets", new RangeValidator<Double>(1.0, 800.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("sodium", new RangeValidator<Double>(90.0, 180.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("potassium", new RangeValidator<Double>(1.0, 9.9), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("bun", new RangeValidator<Double>(1.0, 100.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("serumCreatinine", new RangeValidator<Double>(10.0, 2800.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("protein", new RangeValidator<Double>(5.0, 90.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("albumin", new RangeValidator<Double>(5.0, 60.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("crp", new RangeValidator<Double>(0.0, 200.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("totalCholesterol", new RangeValidator<Double>(1.0, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("hdlCholesterol", new RangeValidator<Double>(0.1, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("ldlCholesterol", new RangeValidator<Double>(1.0, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("triglycerides", new RangeValidator<Double>(0.0, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("thyroxine", new RangeValidator<Double>(0.0, 30.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("tsh", new RangeValidator<Double>(0.0, 50.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("phosphate", new RangeValidator<Double>(0.1, 5.6), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("ferritin", new RangeValidator<Double>(1.0, 5000.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("inr", new RangeValidator<Double>(0.5, 5.0), form, feedbackList));

        // Urinalysis - dipstick
        form.add(new DropDownChoice<LabData.UrineVolumeCondition>("urineVolumeCondition"));
        form.add(new DropDownChoice<LabData.Haematuria>("haematuria"));
        form.add(new DropDownChoice<LabData.Proteinuria>("proteinuria"));
        form.add(new DropDownChoice<LabData.Albuminuria>("albuminuria"));

        form.add(new YesNoNdRadioGroup("leucocytesUrine"));
        form.add(new YesNoNdRadioGroup("nitrite"));
        form.add(new YesNoNdRadioGroup("glucose"));

        // Urinalysis - lab
        form.add(new RadarTextFieldWithValidation("urineVolume", new RangeValidator<Double>(0.0, 4000.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("proteinCreatinineRatio", new RangeValidator<Double>(0.0, 15000.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("albuminCreatinineRatio", new RangeValidator<Double>(1.0, 3000.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("osmolality", new RangeValidator<Double>(200.0, 350.0), form, feedbackList));
        form.add(new CheckBox("bacteria"));
        form.add(new DropDownChoice<LabData.Present>("dysmorphicErythrocytes"));
        form.add(new DropDownChoice<LabData.Present>("redCellCast"));
        form.add(new DropDownChoice<LabData.Present>("whiteCellCasts"));

        // Creatinine clearance
        form.add(new TextField("creatinineClearance"));

        // Antibodies and infections
        form.add(new DropDownChoice<LabData.Anca>("anca"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("ena"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("ana"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("antiDsDna"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("cryoglobulins"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("antiGbm"));
        form.add(new TextArea("dnaAntibodies"));
        form.add(new RadarTextFieldWithValidation("igG", new RangeValidator<Double>(0.0, 20.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("igA", new RangeValidator<Double>(0.0, 10.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("igM", new RangeValidator<Double>(0.0, 10.0), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("complementC3", new RangeValidator<Double>(0.01, 9.99), form, feedbackList));
        form.add(new RadarTextFieldWithValidation("complementC4", new RangeValidator<Double>(0.01, 9.99), form, feedbackList));
        form.add(new CheckBox("complementOther"));

        // Complement other details
        MarkupContainer complementOtherDetailContainer = new WebMarkupContainer("complementOtherDetailContainer") {
            @Override
            public boolean isVisible() {
                return Boolean.TRUE.equals(form.getModelObject().getComplementOther());
            }
        };
        complementOtherDetailContainer.add(new TextArea("complementOtherDetail"));
        form.add(complementOtherDetailContainer);

        form.add(new DropDownChoice<LabData.PositiveNegativeUnknown>("c3NephriticFactor"));
        form.add(new RadarTextFieldWithValidation("antiClqAntibodies", new RangeValidator<Double>(0.0, 150.0), form, feedbackList));
        form.add(new TextField("antistreptolysin"));

        form.add(new DropDownChoice<LabData.PositiveNegativeUnknown>("hepatitisB"));
        form.add(new DropDownChoice<LabData.PositiveNegativeUnknown>("hepatitisC"));
        form.add(new DropDownChoice<LabData.PositiveNegativeUnknown>("hivAntibody"));

        RadioGroup<Boolean> dnaTakenFactorH = new RadioGroup<Boolean>("dnaTakenFactorH");
        dnaTakenFactorH.add(new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE)));
        dnaTakenFactorH.add(new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE)));
        form.add(dnaTakenFactorH);

        form.add(new DropDownChoice<LabData.Immunoglobulins>("ebv"));
        form.add(new DropDownChoice<LabData.Immunoglobulins>("cmvSerology"));
        form.add(new CheckBox("cmvSymptomatic"));
        form.add(new DropDownChoice<LabData.PositiveNegativeNotDone>("parvovirusAntibody"));
        form.add(new CheckBox("otherInfection"));


        final List<Component> componentsToUpdate = new ArrayList<Component>();

        for(Component component : feedbackList) {
            componentsToUpdate.add(component);
        }

        LaboratoryAjaxSubmitLink save = new LaboratoryAjaxSubmitLink("save") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };

        LaboratoryAjaxSubmitLink saveDown = new LaboratoryAjaxSubmitLink("saveDown") {

            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };

        form.add(save, saveDown);
    }

    private final class YesNoNdRadioGroup extends RadioGroup<Boolean> {
        private YesNoNdRadioGroup(String id) {
            super(id);
            // Yes
            Radio<Boolean> yes = new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE));
            add(yes, new FormComponentLabel("yesLabel", yes));

            // No
            Radio<Boolean> no = new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE));
            add(no, new FormComponentLabel("noLabel", no));

            // ND
            Radio<Boolean> nd = new Radio<Boolean>("nd", new Model<Boolean>());
            add(nd, new FormComponentLabel("ndLabel", nd));
        }
    }


    private abstract class LaboratoryAjaxSubmitLink extends AjaxSubmitLink {

        public LaboratoryAjaxSubmitLink(String id) {
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
