package org.patientview.radar.web.components;

import org.apache.wicket.markup.html.form.ListChoice;
import org.patientview.model.Patient;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 13:32
 */
public class PatientSearchSelection extends ListChoice<Patient> {


    public PatientSearchSelection(String id) {
        super(id);
    }

    public PatientSearchSelection(String id, List<Patient> patients) {
        super(id);

    }

}


