package com.solidstategroup.radar.web.components;

import com.solidstategroup.radar.model.Phenotype;
import org.apache.wicket.markup.html.form.DropDownChoice;

public class PhenotypeChooser extends DropDownChoice<Phenotype> {

    public PhenotypeChooser(String id) {
        super(id);
    }
}
