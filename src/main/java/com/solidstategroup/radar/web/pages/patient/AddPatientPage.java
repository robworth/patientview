package com.solidstategroup.radar.web.pages.patient;


import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import java.io.Serializable;
import java.util.Arrays;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class AddPatientPage extends BasePage {
    public AddPatientPage() {

        // create form
        Form<AddPatientModel> form = new Form<AddPatientModel>("form", new CompoundPropertyModel(new AddPatientModel())) {
            @Override
            protected void onSubmit() {
                AddPatientModel model = getModelObject();
                // if srsn of mpgn chosen redirect to previous patient page, otherwise redirect to
                // generic patients page
                if (model.getDiseaseGroup() != null) {
                    if (model.getDiseaseGroup().equals("SRNS") || model.getDiseaseGroup().equals("MGPN")) {
                        setResponsePage(PatientPage.class);
                    } else {
                        setResponsePage(GenericPatientPage.class);
                    }
                }

            }
        };

        // create components
        TextField id = new TextField("id");
        DropDownChoice idType = new DropDownChoice("idType", Arrays.asList("NHS", "CHI"));
        DropDownChoice diseaseGroup = new DropDownChoice("diseaseGroup", Arrays.asList("SRNS", "MPGN", "ALPORT"));
        Label pageNumber = new Label("pageNumber", RadarApplication.ADD_PATIENT_PAGE_N0 + "");

        // submit link
        AjaxSubmitLink submit = new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
            }
        };

        // add the components to hierachy
        form.add(id, idType, diseaseGroup, submit);
        add(form, pageNumber);
    }

    // model class
    public static class AddPatientModel implements Serializable {
        private String id;
        private String idType;
        private String diseaseGroup;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public String getDiseaseGroup() {
            return diseaseGroup;
        }

        public void setDiseaseGroup(String diseaseGroup) {
            this.diseaseGroup = diseaseGroup;
        }
    }

}
