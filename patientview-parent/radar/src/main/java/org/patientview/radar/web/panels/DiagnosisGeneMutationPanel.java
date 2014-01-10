/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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
