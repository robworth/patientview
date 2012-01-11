package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.sequenced.Pathology;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.List;

public class PathologyPanel extends Panel {

    public PathologyPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        Form<Pathology> form = new Form<Pathology>("form", new CompoundPropertyModel<Pathology>(new Pathology()));

        // Add inputs
        form.add(new RadarRequiredDateTextField("biopsyDate", form, componentsToUpdate));
        form.add(new TextField("sampleLabNumber"));
        form.add(new TextArea("interstitalInflmatoryInfilitrate"));
        form.add(new TextArea("arterialAbnormalities"));
        form.add(new TextArea("immunohistologicalFindings"));
        form.add(new TextArea("electronMicroscopicFindings"));

        form.add(new TextField("estimatedTubules"));
        form.add(new TextField("measuredTubules"));
        form.add(new TextArea("tubulesOtherFeature"));

        form.add(new TextField("imageUrl1"));
        form.add(new TextField("imageUrl2"));
        form.add(new TextField("imageUrl3"));
        form.add(new TextField("imageUrl4"));
        form.add(new TextField("imageUrl5"));

        form.add(new RadarTextFieldWithValidation("totalNumber", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSclerosed", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSegmentallySclerosed", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberCellularCrescents", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrousCrescents", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberEndocapillaryHypercelluarity", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrinoidNecrosis", new RangeValidator<Integer>(0, 150), form, componentsToUpdate));

        form.add(new TextArea("otherFeature"));

        form.add(new TextArea("histologicalSummary"));

        form.add(new PathologySubmitLink("save", form) {
            @Override
            protected List<Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });

        form.add(new PathologySubmitLink("saveDown", form) {
            @Override
            protected List<Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        });

        add(form);
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.PATHOLOGY);
    }

    private abstract class PathologySubmitLink extends AjaxSubmitLink {

        protected PathologySubmitLink(String id, Form<?> form) {
            super(id, form);
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        protected abstract List<Component> getComponentsToUpdate();
    }
}
