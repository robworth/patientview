package org.patientview.radar.web.panels;

import org.patientview.radar.model.Relative;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class DiagnosisRelativePanel extends Panel {

    @SpringBean
    private UtilityManager utilityManager;
    private IModel<Boolean> isVisibleModel;

    public DiagnosisRelativePanel(String id, int i, CompoundPropertyModel model, IModel<Boolean> isVisibleModel,
                                  List<Component> componentsToUpdate) {
        super(id);
        this.isVisibleModel = isVisibleModel;
        // Drop down with choices and choice renderer setup
        add(new DropDownChoice<Relative>("relative", model.bind("relativeWithDisease" + i),
                utilityManager.getRelatives(), new ChoiceRenderer<Relative>("name", "id")));

        // Add the radar number text field too
        add(new TextField<Integer>("radarNumber", model.bind("relativeWithDiseaseRadarNumber" + i)));
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        componentsToUpdate.add(this);
    }

    @Override
    public boolean isVisible() {
        return isVisibleModel.getObject();
    }
}
