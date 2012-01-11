package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.dao.PathologyDao;
import com.solidstategroup.radar.model.sequenced.Pathology;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
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
import java.util.Collections;
import java.util.List;

public class PathologyPanel extends Panel {

    @SpringBean
    private PathologyDao pathologyDao;

    public PathologyPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // Model for tha pathology container, the pathology ID
        final IModel<Pathology> pathologyModel = new Model<Pathology>();

        // Add previous results switcher
        List<Pathology> pathologies;
        if (radarNumberModel.getObject() != null) {
            pathologies = pathologyDao.getPathologyByRadarNumber(radarNumberModel.getObject());
        } else {
            pathologies = Collections.emptyList();
        }

        // Container for form, so we can hide and first then show
        final MarkupContainer pathologyContainer = new WebMarkupContainer("pathologyContainer");
        pathologyContainer.setVisible(false);
        pathologyContainer.setOutputMarkupId(true);
        pathologyContainer.setOutputMarkupPlaceholderTag(true);
        add(pathologyContainer);

        // Switcheroo
        DropDownChoice<Pathology> switcher =
                new DropDownChoice<Pathology>("records", pathologyModel, pathologies, new IChoiceRenderer<Pathology>() {
                    public Object getDisplayValue(Pathology object) {
                        // Todo: Format
                        return object.getBiopsyDate();
                    }

                    public String getIdValue(Pathology object, int index) {
                        return String.valueOf(object.getId());
                    }
                });
        add(switcher);

        // Add new
        add(new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                pathologyContainer.setVisible(true);
                target.add(pathologyContainer);
            }
        });

        // Add Ajax behaviour to switch the container on change
        switcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                pathologyContainer.setVisible(true);
                target.add(pathologyContainer);
            }
        });

        final List<Component> componentsToUpdate = new ArrayList<Component>();
        CompoundPropertyModel<Pathology> model;

        // Set up model
        model = new CompoundPropertyModel<Pathology>(new LoadableDetachableModel<Pathology>() {
            @Override
            protected Pathology load() {
                if (pathologyModel.getObject() != null) {
                    // Return the pathology by the ID from the drop down choice
                    return pathologyDao.getPathology(pathologyModel.getObject().getId());
                }
                // Otherwise we'll create a new one
                return new Pathology();
            }
        });

        Form<Pathology> form = new Form<Pathology>("form", model);
        pathologyContainer.add(form);

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

        form.add(new RadarTextFieldWithValidation("totalNumber", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSclerosed", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberSegmentallySclerosed", new RangeValidator<Integer>(0, 150),
                form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberCellularCrescents", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrousCrescents", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberEndocapillaryHypercelluarity",
                new RangeValidator<Integer>(0, 150), form, componentsToUpdate));
        form.add(new RadarTextFieldWithValidation("numberFibrinoidNecrosis", new RangeValidator<Integer>(0, 150), form,
                componentsToUpdate));

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
            ComponentFeedbackPanel a = (ComponentFeedbackPanel) getParent().get("totalNumberFeedback");
            a.getFeedbackMessages();
        }

        protected abstract List<Component> getComponentsToUpdate();
    }
}
