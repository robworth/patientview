package org.patientview.radar.web.components;

import org.patientview.model.Centre;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class CentreDropDown extends DropDownChoice<Centre> {

    @SpringBean
    private UtilityManager utilityManager;

    public CentreDropDown(String id, String nhsNo) {
        super(id);
        setChoices(utilityManager.getCentres(nhsNo));
        setChoiceRenderer(new ChoiceRenderer<Centre>("name", "id"));
    }

    public CentreDropDown(String id, List<Centre> centres) {
        super(id);
        setChoices(centres);
        setChoiceRenderer(new ChoiceRenderer<Centre>("name", "id"));
    }
}
