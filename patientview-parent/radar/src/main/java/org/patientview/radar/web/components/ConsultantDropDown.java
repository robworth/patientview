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

package org.patientview.radar.web.components;

import org.patientview.model.Centre;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Collections;
import java.util.List;

public class ConsultantDropDown extends DropDownChoice<Consultant> {

    @SpringBean
    private UtilityManager utilityManager;

    public ConsultantDropDown(String id) {
        super(id);
        setChoices(utilityManager.getConsultants());
        setChoiceRenderer(new ChoiceRenderer<Consultant>("fullName", "id"));
        setOutputMarkupPlaceholderTag(true);
    }

    public ConsultantDropDown(String id, final IModel<Long> centreNumber) {
        super(id);
        LoadableDetachableModel<List<Consultant>> consultantsListModel =
                new LoadableDetachableModel<List<Consultant>>() {
            @Override
            protected List<Consultant> load() {
                Centre centre = new Centre();
                centre.setId(centreNumber.getObject() != null ? centreNumber.getObject() : null);
                if (centre.getId() != null) {
                    return utilityManager.getConsultantsByCentre(centre);
                }
                return Collections.<Consultant>emptyList();
            }
        };

        setChoices(consultantsListModel);
        setChoiceRenderer(new ChoiceRenderer<Consultant>("fullName", "id"));
        setOutputMarkupPlaceholderTag(true);
    }
}
