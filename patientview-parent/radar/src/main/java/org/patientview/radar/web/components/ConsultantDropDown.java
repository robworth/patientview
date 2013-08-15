package org.patientview.radar.web.components;

import org.patientview.radar.model.Centre;
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
