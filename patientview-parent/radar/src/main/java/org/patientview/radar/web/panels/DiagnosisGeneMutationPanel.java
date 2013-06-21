package org.patientview.radar.web.panels;

import org.patientview.radar.model.Diagnosis;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

public class DiagnosisGeneMutationPanel extends Panel {
    private IModel<Boolean> isVisibleModel;

    public DiagnosisGeneMutationPanel(final String id, int i, CompoundPropertyModel model, final IModel<Boolean>
            otherDetailsVisibilityModel, final IModel<Boolean> moreDetailsVisibilityModel,
                                      final List<Component> componentsToUpdateList) {
        super(id);

        // Add the field for mutationYorN
        RadioGroup<Diagnosis.MutationYorN> mutationYorN =
                new RadioGroup<Diagnosis.MutationYorN>("mutationYorN", model.bind("mutationYorN" + i));

        Radio<Diagnosis.MutationYorN> y = new Radio<Diagnosis.MutationYorN>("y",
                new Model<Diagnosis.MutationYorN>(Diagnosis.MutationYorN.Y));
        mutationYorN.add(y);
        y.add(new AjaxEventBehavior("onClick") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                if (id.equals(DiagnosisPanel.OTHER_CONTAINER_ID)) {
                    otherDetailsVisibilityModel.setObject(true);
                } else {
                    moreDetailsVisibilityModel.setObject(true);

                }
                for (Component component : componentsToUpdateList) {
                    if (component.isVisibleInHierarchy()) {
                        target.add(component);
                    }
                }
            }
        });


        Radio<Diagnosis.MutationYorN> n = new Radio<Diagnosis.MutationYorN>("n",
                new Model<Diagnosis.MutationYorN>(Diagnosis.MutationYorN.N));
        mutationYorN.add(n);
        n.add(new AjaxEventBehavior("onClick") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                if (id.equals(DiagnosisPanel.OTHER_CONTAINER_ID)) {
                    otherDetailsVisibilityModel.setObject(false);
                    for (Component component : componentsToUpdateList) {
                        if (component.isVisibleInHierarchy()) {
                            target.add(component);
                        }
                    }
                }
            }
        });
        add(mutationYorN);

        // Add the field for mutationSorSN
        RadioGroup<Diagnosis.MutationSorSN> mutationSorSN =
                new RadioGroup<Diagnosis.MutationSorSN>("mutationSorSN", model.bind("mutationSorSN" + i));

        mutationSorSN.add(new Radio<Diagnosis.MutationSorSN>("s",
                new Model<Diagnosis.MutationSorSN>(Diagnosis.MutationSorSN.S)));

        mutationSorSN.add(new Radio<Diagnosis.MutationSorSN>("sn",
                new Model<Diagnosis.MutationSorSN>(Diagnosis.MutationSorSN.SN)));
        add(mutationSorSN);
    }
}
