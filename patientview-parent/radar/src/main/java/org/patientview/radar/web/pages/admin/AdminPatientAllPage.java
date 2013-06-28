package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.Collections;
import java.util.List;

public class AdminPatientAllPage extends AdminsBasePage {

    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    private static final String PARAM_ID = "ID";

    public AdminPatientAllPage(PageParameters parameters) {
        super();

        final Demographics demographics;

        StringValue idValue = parameters.get(PARAM_ID);
        demographics = demographicsManager.getDemographicsByRadarNumber(idValue.toLongObject());

        Diagnosis diagnosis = diagnosisManager.getDiagnosisByRadarNumber(demographics.getId());

        CompoundPropertyModel<Diagnosis> diagnosisModel =
                new CompoundPropertyModel<Diagnosis>(diagnosis);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<Diagnosis> userForm = new Form<Diagnosis>("patientForm", diagnosisModel) {
            protected void onSubmit() {
                try {
                    diagnosisManager.saveDiagnosis(getModelObject());
                } catch (Exception e) {
                    error("Could not save diagnosis: " + e.toString());
                }
            }
        };
        add(userForm);

        userForm.add(new Label("radarNo", demographics.getId().toString()));
        userForm.add(new Label("forename", demographics.getForename()));
        userForm.add(new Label("surname", demographics.getSurname()));

        // get centres and sort by name
        List<DiagnosisCode> diagnosisCodes = diagnosisManager.getDiagnosisCodes();
        Collections.sort(diagnosisCodes, DiagnosisCode.getComparator());

        DropDownChoice<DiagnosisCode> diagnosisCode = new DropDownChoice<DiagnosisCode>("diagnosisCode", diagnosisCodes,
                new ChoiceRenderer<DiagnosisCode>("abbreviation", "id"));
        diagnosisCode.setRequired(true);
        userForm.add(diagnosisCode);

        userForm.add(new AjaxSubmitLink("updateTop") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsAllPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelTop") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsAllPage.class);
            }
        });

        userForm.add(new AjaxSubmitLink("updateBottom") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminPatientsAllPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelBottom") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminPatientsAllPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(Demographics demographics) {
        return new PageParameters().set(PARAM_ID, demographics.getId());
    }
}
