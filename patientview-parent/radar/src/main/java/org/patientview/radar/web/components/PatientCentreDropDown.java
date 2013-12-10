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
                    centre.setUnitCode(unit.getUnitcode());
                    centre.setName(unit.getName());
                    centres.add(centre);
                }
            }
        }

        setChoices(centres);
        setChoiceRenderer(new ChoiceRenderer<Centre>("name", "id"));
    }
}
