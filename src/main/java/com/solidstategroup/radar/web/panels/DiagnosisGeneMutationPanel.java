package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Diagnosis;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class DiagnosisGeneMutationPanel extends Panel {

    public DiagnosisGeneMutationPanel(String id, int i) {
        super(id);

        // Models have to use component model as this is reusable and have to use different model fields
        IModel<Diagnosis.MutationYorN> yOrNModel =
                new ComponentPropertyModel<Diagnosis.MutationYorN>("mutationYorN" + i);
        IModel<Diagnosis.MutationSorSN> sOrSNModel =
                new ComponentPropertyModel<Diagnosis.MutationSorSN>("mutationSorSN" + i);

        // Add the field for mutationYorN
        RadioGroup<Diagnosis.MutationYorN> mutationYorN =
                new RadioGroup<Diagnosis.MutationYorN>("mutationYorN", yOrNModel);
        mutationYorN.add(new Radio<Diagnosis.MutationYorN>("y",
                new Model<Diagnosis.MutationYorN>(Diagnosis.MutationYorN.Y)));
        mutationYorN.add(new Radio<Diagnosis.MutationYorN>("n",
                new Model<Diagnosis.MutationYorN>(Diagnosis.MutationYorN.N)));
        add(mutationYorN);

        // Add the field for mutationSorSN
        RadioGroup<Diagnosis.MutationSorSN> mutationSorSN =
                new RadioGroup<Diagnosis.MutationSorSN>("mutationSorSN", sOrSNModel);
        mutationSorSN.add(new Radio<Diagnosis.MutationSorSN>("s",
                new Model<Diagnosis.MutationSorSN>(Diagnosis.MutationSorSN.S)));
        mutationSorSN.add(new Radio<Diagnosis.MutationSorSN>("sn",
                new Model<Diagnosis.MutationSorSN>(Diagnosis.MutationSorSN.SN)));
        add(mutationSorSN);
    }

}
