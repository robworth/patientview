package org.patientview.radar.web.panels.generic;

import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Ethnicity;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.enums.NhsNumberType;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.service.generic.GenericDiagnosisManager;
import org.patientview.radar.util.RadarUtility;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.CentreDropDown;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.ClinicianDropDown;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarFormComponentFeedbackIndicator;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.components.RadarRequiredDropdownChoice;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.components.RadarTextFieldWithValidation;
import org.patientview.radar.web.panels.PatientDetailPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.parse.metapattern.MetaPattern;
import org.apache.wicket.validation.validator.PatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GenericDemographicsPanel extends Panel {

    @SpringBean
    private DemographicsManager demographicsManager;

    @SpringBean
    private UtilityManager utilityManager;

    @SpringBean
    private GenericDiagnosisManager genericDiagnosisManager;

    @SpringBean
    private UserManager userManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDemographicsPanel.class);

    public GenericDemographicsPanel(String id, Demographics demographics) {
        super(id);
        init(demographics);
    }

    private void init(Demographics demographics) {
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();

        if (demographics.getDateRegistered() == null) {
            demographics.setDateRegistered(new Date());
        }

        // components to update on ajax refresh
        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        // add form
        final IModel<Demographics> model = new Model(demographics);


        Form<Demographics> form = new Form<Demographics>("form", new CompoundPropertyModel(model)) {
            @Override
            protected void onSubmit() {
                Demographics demographics = getModel().getObject();

                // make sure diagnosis date is after dob
                if (demographics.getDateOfGenericDiagnosis().compareTo(demographics.getDateOfBirth()) < 0) {
                    get("dateOfGenericDiagnosis").error("Your diagnosis date cannot be before your date of birth");
                }

                demographics.setGeneric(true);
                demographicsManager.saveDemographics(demographics);
                try {
                    userManager.registerPatient(demographics);
                } catch (Exception e) {
                    String message = "Error registering new patient to accompany this demographic";
                    LOGGER.error("{}, message {}", message, e.getMessage());
                    error(message);
                }
            }
        };

        add(form);

        WebMarkupContainer patientDetail = new PatientDetailPanel("patientDetail", demographics, "Demographics");
        patientDetail.setOutputMarkupId(true);
        patientDetail.setOutputMarkupPlaceholderTag(true);
        form.add(patientDetail);
        componentsToUpdateList.add(patientDetail);

        RadarRequiredTextField surname = new RadarRequiredTextField("surname", form, componentsToUpdateList);
        RadarRequiredTextField forename = new RadarRequiredTextField("forename", form, componentsToUpdateList);
        TextField alias = new TextField("surnameAlias");
        RadarRequiredDateTextField dateOfBirth = new RadarRequiredDateTextField("dateOfBirth", form,
                componentsToUpdateList);

        form.add(surname, forename, alias, dateOfBirth);

        // Sex
        RadarRequiredDropdownChoice sex =
                new RadarRequiredDropdownChoice("sex", demographicsManager.getSexes(), new ChoiceRenderer<Sex>("type",
                        "id"), form, componentsToUpdateList);

        // Ethnicity
        DropDownChoice<Ethnicity> ethnicity = new DropDownChoice<Ethnicity>("ethnicity", utilityManager.
                getEthnicities(), new ChoiceRenderer<Ethnicity>("name", "id"));
        form.add(sex, ethnicity);

        // Address fields
        TextField address1 = new TextField("address1");
        TextField address2 = new TextField("address2");
        TextField address3 = new TextField("address3");
        TextField address4 = new TextField("address4");
        RadarTextFieldWithValidation postcode = new RadarTextFieldWithValidation("postcode",
                new PatternValidator("[a-zA-Z]{1,2}[0-9][0-9A-Za-z]{0,1} {0,1}[0-9][A-Za-z]{2}$"), form,
                componentsToUpdateList);
        form.add(address1, address2, address3, address4, postcode);


        // More info
        Label nhsNumber = new Label("nhsNumber");

        WebMarkupContainer nhsNumberContainer = new WebMarkupContainer("nhsNumberContainer") {
            @Override
            public boolean isVisible() {
                return model.getObject().getNhsNumberType().equals(NhsNumberType.NHS_NUMBER);
            }
        };
        nhsNumberContainer.add(nhsNumber);

        Label chiNumber = new Label("chiNumber");

        WebMarkupContainer chiNumberContainer = new WebMarkupContainer("chiNumberContainer") {
            @Override
            public boolean isVisible() {
                return model.getObject().getNhsNumberType().equals(NhsNumberType.CHI_NUMBER);
            }
        };

        chiNumberContainer.add(chiNumber);

        // add new ids section
        final List<Component> addIdComponentsToUpdate = new ArrayList<Component>();

        IModel<AddIdModel> addIdModel = new Model<AddIdModel>(new AddIdModel());
        Form<AddIdModel> addIdForm = new Form<AddIdModel>("addIdForm", new CompoundPropertyModel(addIdModel)) {
            @Override
            protected void onSubmit() {
                AddIdModel idModel = getModel().getObject();
                Demographics demographics = model.getObject();
                String id = idModel.getId();
                if (idModel.getIdType() != null) {
                    if (idModel.getIdType().equals(IdType.CHANNELS_ISLANDS)) {
                        demographics.setChannelIslandsId(id);
                    }
                    if (idModel.getIdType().equals(IdType.HOSPITAL_NUMBER)) {
                        demographics.setHospitalNumber(id);
                    }
                    if (idModel.getIdType().equals(IdType.INDIA)) {
                        demographics.setIndiaId(id);
                    }
                    if (idModel.getIdType().equals(IdType.RENAL_REGISTRY_NUMBER)) {
                        demographics.setRenalRegistryNumber(id);
                    }
                    if (idModel.getIdType().equals(IdType.REPUBLIC_OF_IRELAND)) {
                        demographics.setRepublicOfIrelandId(id);
                    }
                    if (idModel.getIdType().equals(IdType.UK_TRANSPLANT_NUMBER)) {
                        demographics.setUkTransplantNumber(id);
                    }
                }
            }
        };

        AjaxSubmitLink addIdSubmit = new AjaxSubmitLink("addIdSubmit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, addIdComponentsToUpdate);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, addIdComponentsToUpdate);
            }
        };

        TextField addIdValue = new TextField("id");
        DropDownChoice addIdType =
                new DropDownChoice("idType", Arrays.asList(IdType.HOSPITAL_NUMBER,
                        IdType.RENAL_REGISTRY_NUMBER, IdType.UK_TRANSPLANT_NUMBER, IdType.REPUBLIC_OF_IRELAND,
                        IdType.CHANNELS_ISLANDS, IdType.INDIA), new ChoiceRenderer());

        addIdForm.add(addIdValue, addIdType, addIdSubmit);
        form.add(addIdForm);

        TextField hospitalNumber = new TextField("hospitalNumber");
        WebMarkupContainer hospitalNumberContainer = new WebMarkupContainer("hospitalNumberContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getHospitalNumber() != null) {
                    if (!model.getObject().getHospitalNumber().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };

        hospitalNumberContainer.add(hospitalNumber);

        TextField renalRegistryNumber = new TextField("renalRegistryNumber");
        WebMarkupContainer renalRegistryNumberContainer = new WebMarkupContainer("renalRegistryNumberContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getRenalRegistryNumber() != null) {
                    if (!model.getObject().getRenalRegistryNumber().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };
        renalRegistryNumberContainer.add(renalRegistryNumber);

        TextField ukTransplantNumber = new TextField("ukTransplantNumber");

        WebMarkupContainer ukTransplantNumberContainer = new WebMarkupContainer("ukTransplantNumberContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getUkTransplantNumber() != null) {
                    if (!model.getObject().getUkTransplantNumber().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };
        ukTransplantNumberContainer.add(ukTransplantNumber);

        // add other generic ids
        TextField republicOfIrelandId = new TextField("republicOfIrelandId");

        WebMarkupContainer republicOfIrelandIdContainer = new WebMarkupContainer("republicOfIrelandIdContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getRepublicOfIrelandId() != null) {
                    if (!model.getObject().getRepublicOfIrelandId().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };
        republicOfIrelandIdContainer.add(republicOfIrelandId);

        TextField isleOfManId = new TextField("isleOfManId");

        WebMarkupContainer isleOfManIdContainer = new WebMarkupContainer("isleOfManIdContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getIsleOfManId() != null) {
                    if (!model.getObject().getIsleOfManId().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };

        isleOfManIdContainer.add(isleOfManId);

        TextField channelIslandsId = new TextField("channelIslandsId");

        WebMarkupContainer channelIslandsIdContainer = new WebMarkupContainer("channelIslandsIdContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getChannelIslandsId() != null) {
                    if (!model.getObject().getChannelIslandsId().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };
        channelIslandsIdContainer.add(channelIslandsId);

        TextField indiaId = new TextField("indiaId");

        WebMarkupContainer indiaIdContainer = new WebMarkupContainer("indiaIdContainer") {
            @Override
            public boolean isVisible() {
                if (model.getObject().getIndiaId() != null) {
                    if (!model.getObject().getIndiaId().isEmpty()) {
                        return true;
                    }
                }
                return false;
            }
        };
        indiaIdContainer.add(indiaId);

        addIdComponentsToUpdate.add(hospitalNumberContainer);
        addIdComponentsToUpdate.add(renalRegistryNumberContainer);
        addIdComponentsToUpdate.add(ukTransplantNumberContainer);
        addIdComponentsToUpdate.add(republicOfIrelandIdContainer);
        addIdComponentsToUpdate.add(isleOfManIdContainer);
        addIdComponentsToUpdate.add(channelIslandsIdContainer);
        addIdComponentsToUpdate.add(indiaIdContainer);

        for (Component component : Arrays.asList(hospitalNumberContainer, renalRegistryNumberContainer,
                ukTransplantNumberContainer, republicOfIrelandIdContainer, isleOfManIdContainer,
                channelIslandsIdContainer, indiaIdContainer)) {
            component.setOutputMarkupPlaceholderTag(true);
        }

        form.add(hospitalNumberContainer, nhsNumberContainer, renalRegistryNumberContainer,
                ukTransplantNumberContainer, chiNumberContainer);
        form.add(republicOfIrelandIdContainer, isleOfManIdContainer, channelIslandsIdContainer, indiaIdContainer);


        // Consultant and renal unit
        final IModel<String> centreNumber = new Model<String>();
        Centre renalUnitSelected = form.getModelObject().getRenalUnit();
        centreNumber.setObject(renalUnitSelected != null ? renalUnitSelected.getUnitCode() : null);

        final ClinicianDropDown clinician = new ClinicianDropDown("clinician", centreNumber);
        form.add(clinician);

        DropDownChoice<Centre> renalUnit;

        // if its a super user then the drop down will let them change renal units
        // if its a normal user they can only add to their own renal unit
        if (user.getSecurityRole().equals(User.ROLE_SUPER_USER)) {
            renalUnit = new CentreDropDown("renalUnit");

            renalUnit.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    Demographics demographics = model.getObject();
                    if (demographics != null) {
                        centreNumber.setObject(demographics.getRenalUnit() != null ?
                                demographics.getRenalUnit().getUnitCode() :
                                null);
                    }

                    clinician.clearInput();
                    target.add(clinician);
                }
            });
        } else {
            List<Centre> centres = new ArrayList<Centre>();
            centres.add(form.getModelObject().getRenalUnit());

            renalUnit = new CentreDropDown("renalUnit", centres);
        }

        form.add(renalUnit);

        CheckBox consent = new CheckBox("consent");
        form.add(consent);

        form.add(new ExternalLink("consentFormsLink", "http://www.rarerenal.org/join/criteria-and-consent/"));

        // add generic fields
        TextField emailAddress = new TextField("emailAddress");
        TextField phone1 = new TextField("phone1");
        TextField phone2 = new TextField("phone2");

        RadarTextFieldWithValidation mobile = new RadarTextFieldWithValidation("mobile",
                        new PatternValidator(MetaPattern.DIGITS), form,
                        componentsToUpdateList);

        RadarRequiredDropdownChoice genericDiagnosis =
                new RadarRequiredDropdownChoice("genericDiagnosis", genericDiagnosisManager.getByDiseaseGroup(
                        demographics.getDiseaseGroup()), new ChoiceRenderer("term", "id"), form,
                        componentsToUpdateList);

        RadarRequiredDateTextField dateOfGenericDiagnosis = new RadarRequiredDateTextField("dateOfGenericDiagnosis",
                form, componentsToUpdateList);

        TextArea otherClinicianAndContactInfo = new TextArea("otherClinicianAndContactInfo");
        TextArea comments = new TextArea("comments");

        form.add(emailAddress, phone1, phone2, mobile, genericDiagnosis, dateOfGenericDiagnosis,
                otherClinicianAndContactInfo, comments);

        RadioGroup<Demographics.RRTModality> rrtModalityRadioGroup = new RadioGroup<Demographics.RRTModality>(
                "rrtModality");
        rrtModalityRadioGroup.add(new Radio("hd", new Model(Demographics.RRTModality.HD)));
        rrtModalityRadioGroup.add(new Radio("pd", new Model(Demographics.RRTModality.PD)));
        rrtModalityRadioGroup.add(new Radio("tx", new Model(Demographics.RRTModality.Tx)));
        rrtModalityRadioGroup.add(new Radio("none", new Model(Demographics.RRTModality.NONE)));

        /**
         * todo rrt modality feedback - coped from RadarTextFieldWithValidation
         *
         * todo roberts to have a look!
         */

        final ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel("rrtModalityFeedback", this) {
            @Override
            public boolean isVisible() {
                List<FeedbackMessage> feedbackMessages = getCurrentMessages();
                for (FeedbackMessage feedbackMessage : feedbackMessages) {
                    if (feedbackMessage.getMessage().toString().contains("required")) {
                        return false;
                    }
                }
                return super.isVisible();
            }
        };
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);
        componentsToUpdateList.add(feedbackPanel);

        rrtModalityRadioGroup.setRequired(true);
        RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                new RadarFormComponentFeedbackIndicator("rrtModalityFeedbackIndicator", this) {
                    @Override
                    public boolean isVisible() {
                        if (feedbackPanel.isVisible()) {
                            return false;
                        }
                        return super.isVisible();
                    }
                };
        form.add(radarFormComponentFeedbackIndicator);
        radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
        radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(radarFormComponentFeedbackIndicator);

        form.add(rrtModalityRadioGroup);

        /**
         * todo validation ends
         */

        final Label successMessage = RadarComponentFactory.getSuccessMessageLabel("successMessage", form,
                componentsToUpdateList);

        final Label successMessageUp = RadarComponentFactory.getSuccessMessageLabel("successMessageUp", form,
                componentsToUpdateList);

        Label errorMessage = RadarComponentFactory.getErrorMessageLabel("errorMessage", form,
                "Please complete all mandatory fields", componentsToUpdateList);
        Label errorMessageUp = RadarComponentFactory.getErrorMessageLabel("errorMessageUp", form,
                "Please complete all mandatory fields", componentsToUpdateList);

        AjaxSubmitLink ajaxSubmitLinkTop = new AjaxSubmitLink("saveTop") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
                target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
            }
        };

        AjaxSubmitLink ajaxSubmitLinkBottom = new AjaxSubmitLink("saveBottom") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
                target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdateList);
            }
        };

        form.add(ajaxSubmitLinkTop);
        form.add(ajaxSubmitLinkBottom);
    }

    private static class AddIdModel implements Serializable {
        String id;
        IdType idType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public IdType getIdType() {
            return idType;
        }

        public void setIdType(IdType idType) {
            this.idType = idType;
        }
    }

    enum IdType {
        HOSPITAL_NUMBER,
        RENAL_REGISTRY_NUMBER,
        UK_TRANSPLANT_NUMBER,
        REPUBLIC_OF_IRELAND,
        CHANNELS_ISLANDS,
        INDIA;

        @Override
        public String toString() {
            return RadarUtility.getLabelFromEnum(super.toString());
        }
    }
}
