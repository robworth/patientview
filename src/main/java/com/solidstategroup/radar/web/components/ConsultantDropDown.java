package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Consultant;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ConsultantDropDown extends DropDownChoice<Consultant> {

    @SpringBean
    private UtilityDao utilityDao;

    public ConsultantDropDown(String id) {
        super(id);
        setChoices(utilityDao.getConsultants());
        setChoiceRenderer(new IChoiceRenderer<Consultant>() {
            public Object getDisplayValue(Consultant object) {
                return object.getFullName();
            }

            public String getIdValue(Consultant object, int index) {
                return object.getId().toString();
            }
        });
    }
}
