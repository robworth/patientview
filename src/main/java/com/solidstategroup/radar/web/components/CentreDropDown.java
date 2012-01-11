package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.dao.UtilityDao;
import com.solidstategroup.radar.model.Centre;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class CentreDropDown extends DropDownChoice<Centre> {

    @SpringBean
    private UtilityDao utilityDao;

    public CentreDropDown(String id) {
        super(id);
        setChoices(utilityDao.getCentres());
        setChoiceRenderer(new ChoiceRenderer<Centre>("abbreviation", "id"));
    }

}
