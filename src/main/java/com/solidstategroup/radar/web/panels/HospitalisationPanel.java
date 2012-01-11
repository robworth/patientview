package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.dao.HospitalisationDao;
import com.solidstategroup.radar.model.Hospitalisation;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HospitalisationPanel extends Panel {

    @SpringBean
    private HospitalisationDao hospitalisationDao;

    public HospitalisationPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        CompoundPropertyModel<Hospitalisation> model;

        // Set up model
        model = new CompoundPropertyModel<Hospitalisation>(new LoadableDetachableModel<Hospitalisation>() {
            @Override
            protected Hospitalisation load() {
                if (radarNumberModel.getObject() != null) {
                    List<Hospitalisation> hospitalisations =
                            hospitalisationDao.getHospitalisationsByRadarNumber(radarNumberModel.getObject());
                    if (!hospitalisations.isEmpty()) {
                        // Todo: This shouldn't just return the first result
                        return hospitalisations.get(0);
                    }
                }

                return new Hospitalisation();
            }
        });

        Form<Hospitalisation> form =
                new Form<Hospitalisation>("form", model) {
                    @Override
                    protected void onValidateModelObjects() {
                        super.onValidateModelObjects();
                        Hospitalisation hospitalisation = getModelObject();
                        Date dateofAdmission = hospitalisation.getDateOfAdmission();
                        Date dateofDischarge = hospitalisation.getDateOfDischarge();
                        if (dateofAdmission != null && dateofDischarge != null
                                && dateofAdmission.compareTo(dateofDischarge) != -1) {
                            get("dateOfDischarge").error("Date has to be after admission date");
                        }
                    }
                };
        add(form);

        form.add(new RadarRequiredDateTextField("dateOfAdmission", form, componentsToUpdate));
        form.add(new RadarDateTextField("dateOfDischarge", form, componentsToUpdate));
        form.add(new TextArea("reason"));
        form.add(new TextArea("comments"));
        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.HOSPITALISATION);
    }
}
