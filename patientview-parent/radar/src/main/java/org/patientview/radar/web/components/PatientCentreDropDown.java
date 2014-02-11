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

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.model.Centre;
import org.patientview.model.Patient;
import org.patientview.model.Unit;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UnitManager;
import org.patientview.radar.service.UtilityManager;

import java.util.ArrayList;
import java.util.List;

/**
 *  This component has special behaviour:
 *
 *  If this is for a new patient:
 *      - super admins can add any unit
 *      - unit admins can add any unit they have mappings for
 *
 *  If this is for an existing patient, it will be just show the centre already set on the patient
 */
public class PatientCentreDropDown extends DropDownChoice<Centre> {

    @SpringBean
    private UnitManager unitManager;

    @SpringBean
    private UtilityManager utilityManager;

    public PatientCentreDropDown(String id, User viewingUser, Patient patient) {
        super(id);

        // work out the list of centres to show based on the viewing user and viewed patient
        List<Centre> centres = new ArrayList<Centre>();
        if (patient.hasValidId()) {
            // just show the centre already set on the patient
            centres.add(patient.getRenalUnit());

        } else {
            if (viewingUser.getSecurityRole().equals(User.ROLE_SUPER_USER)) {
                centres = utilityManager.getCentres();

            } else if (viewingUser.getSecurityRole().equals(User.ROLE_PROFESSIONAL)) {
                for (Unit unit : unitManager.getRenalUnits(viewingUser)) {
                    Centre centre = new Centre();
                    centre.setId(unit.getId());
                    centre.setAbbreviation(unit.getUnitcode());
                    centre.setRenalAdminEmail(centre.getRenalAdminEmail());
                    centre.setUnitCode(unit.getUnitcode());
                    centre.setName(unit.getName());
                    centres.add(centre);
                }
            }
        }

        setChoices(centres);
        setChoiceRenderer(new ChoiceRenderer<Centre>("name", "id"));

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }
}
