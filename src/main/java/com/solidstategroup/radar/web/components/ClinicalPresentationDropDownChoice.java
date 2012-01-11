package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.ClinicalPresentation;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ClinicalPresentationDropDownChoice extends DropDownChoice<ClinicalPresentation> {

    @SpringBean
    private DiagnosisDao diagnosisDao;

    public ClinicalPresentationDropDownChoice(String id) {
        super(id);
        setChoices(diagnosisDao.getClinicalPresentations());
        setChoiceRenderer(new ChoiceRenderer<ClinicalPresentation>("name", "id"));
    }
}
