package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.model.Phenotype;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PhenotypeChooser extends DropDownChoice<Phenotype> {

    @SpringBean
    private ClinicalDataDao clinicalDataDao;

    public PhenotypeChooser(String id) {
        super(id);
        setChoices(clinicalDataDao.getPhenotypes());
        setChoiceRenderer(new ChoiceRenderer<Phenotype>("description", "id"));
    }
}
