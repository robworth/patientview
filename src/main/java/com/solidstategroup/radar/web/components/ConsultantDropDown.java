package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.model.Consultant;
import com.solidstategroup.radar.service.UtilityManager;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConsultantDropDown extends DropDownChoice<Consultant> {

    @SpringBean
    private UtilityManager utilityManager;

    public ConsultantDropDown(String id) {
        super(id);
        setChoices(utilityManager.getConsultants());
        setChoiceRenderer(new ChoiceRenderer<Consultant>("fullName", "id"));

        /*
        setChoiceRenderer(new IChoiceRenderer<Consultant>() {
            public Object getDisplayValue(Consultant object) {
                return object.getFullName();
            }

            public String getIdValue(Consultant object, int index) {
                return object.getId().toString();
            }
        }); */
    }
}
