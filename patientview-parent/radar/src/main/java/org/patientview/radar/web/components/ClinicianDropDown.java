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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.model.Centre;
import org.patientview.model.Clinician;
import org.patientview.model.Patient;
import org.patientview.model.Unit;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UnitManager;
import org.patientview.radar.service.UtilityManager;

import java.util.Collections;
import java.util.List;

public class ClinicianDropDown extends DropDownChoice<Clinician> {

    @SpringBean
    private UnitManager unitManager;

    @SpringBean
    private UtilityManager utilityManager;

    private final IModel<String> centreNumber = new Model<String>();

    public ClinicianDropDown(final String id, final User viewingUser, final Patient patient) {
        super(id);
        Centre centre = getCentreForToInitialiseComponent(viewingUser, patient);
        if (centre != null) {
            centreNumber.setObject(centre.getUnitCode());
        }

        LoadableDetachableModel<List<Clinician>> cliniciansListModel =
                new LoadableDetachableModel<List<Clinician>>() {
            @Override
            protected List<Clinician> load() {
                Centre centre = new Centre();
                centre.setUnitCode(centreNumber.getObject() != null ? centreNumber.getObject() : null);
                if (centre.getUnitCode() != null) {
                    return utilityManager.getCliniciansByCentre(centre);
                }
                return Collections.<Clinician>emptyList();
            }
        };

        setChoices(cliniciansListModel);
        setChoiceRenderer(new ChoiceRenderer<Clinician>("surname", "id"));
        setOutputMarkupPlaceholderTag(true);
    }

    public void updateCentre(String unitCode) {
        centreNumber.setObject(unitCode);
    }

    private Centre getCentreForToInitialiseComponent(User viewingUser, Patient patient) {
        if (patient.hasValidId()) {
            // just show the centre already set on the patient
            return patient.getRenalUnit();

        } else {
            if (viewingUser.getSecurityRole().equals(User.ROLE_SUPER_USER)) {
                // no need to populate, the update renal unit, will trigger a populate for us
                return null;

            } else if (viewingUser.getSecurityRole().equals(User.ROLE_PROFESSIONAL)) {
                // supply the first centre the user has permission to
                List<Unit> usersUnits = unitManager.getRenalUnits(viewingUser);
                if (usersUnits != null && usersUnits.size() > 0) {
                    Centre centre = new Centre();
                    Unit unit = usersUnits.get(0);
                    centre.setUnitCode(unit.getUnitcode());
                    centre.setName(unit.getName());
                    return centre;
                }
            }
        }

        return null;
    }
}
