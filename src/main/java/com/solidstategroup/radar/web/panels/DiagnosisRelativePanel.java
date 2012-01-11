package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Relative;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class DiagnosisRelativePanel extends Panel {

    @SpringBean
    private UtilityDao utilityDao;

    public DiagnosisRelativePanel(String id, int i) {
        super(id);

        // Set the model of this drop down to reflect the relative with number i
        ComponentPropertyModel<Relative> model = new ComponentPropertyModel<Relative>("relativeWithDisease" + i);

        // Drop down with choices and choice renderer setup
        add(new DropDownChoice<Relative>("relative", model, utilityDao.getRelatives(),
                new ChoiceRenderer<Relative>("name", "id")));

        // Add the radar number text field too
        add(new TextField<String>("radarNumber",
                new ComponentPropertyModel<String>("relativeWithDiseaseRadarNumber" + i)));
    }

}
