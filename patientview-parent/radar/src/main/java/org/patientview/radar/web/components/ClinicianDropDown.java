package org.patientview.radar.web.components;

import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Clinician;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Collections;
import java.util.List;

public class ClinicianDropDown extends DropDownChoice<Clinician> {

    @SpringBean
    private UtilityManager utilityManager;

    public ClinicianDropDown(String id, final IModel<String> centreNumber) {
        super(id);
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
}
