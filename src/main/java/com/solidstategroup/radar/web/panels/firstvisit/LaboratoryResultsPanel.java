package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.model.LabData;
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
import org.apache.wicket.model.Model;

public class LaboratoryResultsPanel extends Panel {

    public LaboratoryResultsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final Form<LabData> form = new Form<LabData>("form", new CompoundPropertyModel<LabData>(new LabData()));
        add(form);

        // Blood fields
        form.add(new TextField("hb"));
        form.add(new TextField("wbc"));
        form.add(new TextField("neutrophils"));
        form.add(new TextField("platelets"));
        form.add(new TextField("sodium"));
        form.add(new TextField("potassium"));
        form.add(new TextField("bun"));
        form.add(new TextField("serumCreatinine"));
        form.add(new TextField("protein"));
        form.add(new TextField("albumin"));
        form.add(new TextField("crp"));
        form.add(new TextField("totalCholesterol"));
        form.add(new TextField("hdlCholesterol"));
        form.add(new TextField("ldlCholesterol"));
        form.add(new TextField("triglycerides"));
        form.add(new TextField("thyroxine"));
        form.add(new TextField("tsh"));
        form.add(new TextField("phosphate"));
        form.add(new TextField("ferritin"));
        form.add(new TextField("inr"));

        // Urinalysis - dipstick
        form.add(new DropDownChoice<LabData.UrineVolumeCondition>("urineVolumeCondition"));
        form.add(new DropDownChoice<LabData.Haematuria>("haematuria"));
        form.add(new DropDownChoice<LabData.Proteinuria>("proteinuria"));
        form.add(new DropDownChoice<LabData.Albuminuria>("albuminuria"));

        form.add(new YesNoNdRadioGroup("leucocytesUrine"));
        form.add(new YesNoNdRadioGroup("nitrite"));
        form.add(new YesNoNdRadioGroup("glucose"));

        // Urinalysis - lab
        form.add(new TextField("urineVolume"));
        form.add(new TextField("proteinCreatinineRatio"));
        form.add(new TextField("albuminCreatinineRatio"));
        form.add(new TextField("osmolality"));
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
        form.add(new TextField("igG"));
        form.add(new TextField("igA"));
        form.add(new TextField("igM"));
        form.add(new TextField("complementC3"));
        form.add(new TextField("complementC4"));
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
        form.add(new TextField("antiClqAntibodies"));
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

    private class YesNoNdRadioGroup extends RadioGroup<Boolean> {
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

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
