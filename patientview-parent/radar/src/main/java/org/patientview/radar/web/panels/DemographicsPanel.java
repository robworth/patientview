package org.patientview.radar.web.panels;

import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Ethnicity;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.Status;
import org.patientview.radar.model.enums.NhsNumberType;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.ClinicalDataManager;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.LabDataManager;
import org.patientview.radar.service.TherapyManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.service.generic.DiseaseGroupManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.CentreDropDown;
import org.patientview.radar.web.components.ClinicianDropDown;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.components.RadarRequiredDropdownChoice;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.components.RadarTextFieldWithValidation;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.PatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemographicsPanel extends Panel {

    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiseaseGroupManager diseaseGroupManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private ClinicalDataManager clinicalDataManager;
    @SpringBean
    private LabDataManager labDataManager;
    @SpringBean
    private TherapyManager therapyManager;
    @SpringBean
    private UtilityManager utilityManager;

    @SpringBean
    private UserManager userManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemographicsPanel.class);

    public DemographicsPanel(String id, final IModel<Long> radarNumberModel) {
        this(id, radarNumberModel, null);
    }

    public DemographicsPanel(String id, final IModel<Long> radarNumberModel, final PageParameters pageParameters) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        User user = RadarSecuredSession.get().getUser();

        // Set up model - if given radar number loadable detachable getting demographics by radar number
        final CompoundPropertyModel<Demographics> model = new CompoundPropertyModel<Demographics>(
                new LoadableDetachableModel<Demographics>() {
                    @Override
                    public Demographics load() {
                        Demographics demographicsModelObject = null;
                        if (radarNumberModel.getObject() != null) {
                            Long radarNumber;
                            try {
                                radarNumber = radarNumberModel.getObject();
                            } catch (ClassCastException e) {
                                Object obj = radarNumberModel.getObject();
                                radarNumber = Long.parseLong((String) obj);
                            }
                            demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                        }

                        if (demographicsModelObject == null) {
                            demographicsModelObject = new Demographics();

                            if (pageParameters != null) {
                                // if page parameter exists then adding a demographics
                                // set the id type and val
                                String idType = pageParameters.get("idType").toString();

                                demographicsModelObject.setNhsNumber(pageParameters.get("idVal").toString());

                                if (idType.equals(NhsNumberType.CHI_NUMBER.toString())) {
                                    demographicsModelObject.setNhsNumberType(NhsNumberType.CHI_NUMBER);
                                } else if (idType.equals(NhsNumberType.NHS_NUMBER.toString())) {
                                    demographicsModelObject.setNhsNumberType(NhsNumberType.NHS_NUMBER);
                                }

                                String diseaseGroupId = pageParameters.get("diseaseGroupId").toString();

                                if (diseaseGroupId != null) {
                                    demographicsModelObject.setDiseaseGroup(
                                            diseaseGroupManager.getById(diseaseGroupId));
                                }


                                StringValue idValue = pageParameters.get("renalUnitId");
                                if (!idValue.isEmpty()) {
                                    demographicsModelObject.setRenalUnit(
                                            utilityManager.getCentre(idValue.toLongObject()));
                                }
                            }
                        }

                        return demographicsModelObject;
                    }
                });

        // Set up form
        final Form<Demographics> form = new Form<Demographics>("form", model) {
            @Override
            protected void onSubmit() {
                Demographics demographics = getModelObject();
                // shouldnt need to do this but for some reason the id comes back with null!
                if (radarNumberModel.getObject() != null) {
                    demographics.setId(radarNumberModel.getObject());
                }
                demographicsManager.saveDemographics(demographics);
                try {
                    userManager.registerPatient(demographics);
                } catch (Exception e) {
                    String message = "Error registering new patient to accompany this demographic";
                    LOGGER.error("{}, message {}", message, e.getMessage());
                    error(message);
                }
                radarNumberModel.setObject(demographics.getId());

                // create new diagnosis if it doesnt exist becuase diagnosis code is set in demographics tab
                Diagnosis diagnosis = diagnosisManager.getDiagnosisByRadarNumber(demographics.getId());
                if (diagnosis == null) {
                    Diagnosis diagnosisNew = new Diagnosis();
                    diagnosisNew.setRadarNumber(demographics.getId());
                    DiagnosisCode diagnosisCode = (DiagnosisCode) ((DropDownChoice) get("diagnosis")).getModelObject();
                    diagnosisNew.setDiagnosisCode(diagnosisCode);
                    diagnosisManager.saveDiagnosis(diagnosisNew);
                }

            }
        };

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

        form.add(nhsNumberContainer, chiNumberContainer);

        form.setOutputMarkupId(true);
        form.setOutputMarkupPlaceholderTag(true);

        add(form);

        final List<Component> componentsToUpdateList = new ArrayList<Component>();

        form.add(new Label("addNewPatientLabel", "Add a New Patient") {
            @Override
            public boolean isVisible() {
                return radarNumberModel.getObject() == null;
            }
        });

        TextField<Long> radarNumberField = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumberField.setEnabled(false);
        form.add(radarNumberField);

        DateTextField dateRegistered = DateTextField.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN);
        if (radarNumberModel.getObject() == null) {
            model.getObject().setDateRegistered(new Date());
        }
        form.add(dateRegistered);

        RadarRequiredDropdownChoice diagnosis =
                new RadarRequiredDropdownChoice("diagnosis", RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                        diagnosisManager),
                        diagnosisManager.getDiagnosisCodes(),
                        new ChoiceRenderer("abbreviation", "id"), form, componentsToUpdateList) {
                    @Override
                    public boolean isEnabled() {
                        RadarSecuredSession securedSession = RadarSecuredSession.get();
                        if (securedSession.getRoles().hasRole(User.ROLE_PATIENT)) {
                            return false;
                        }
                        return getModelObject() == null;
                    }
                };

        if (pageParameters != null) {
            //if pageParameters not null then creating new demographics - set the diagnosis
            String diseaseGroup = pageParameters.get("diagnosis").toString();
            DiagnosisCode diagnosisCode = new DiagnosisCode();

            if (diseaseGroup.equals(DiagnosisCode.SRNS_ID + "")) {
                diagnosisCode.setId(DiagnosisCode.SRNS_ID);
            } else if (diseaseGroup.equals(DiagnosisCode.MPGN_ID + "")) {
                diagnosisCode.setId(DiagnosisCode.MPGN_ID);
            }

            diagnosis.setModel(new Model(diagnosisCode));
        }

        /**
         * Basic fields
         */
        RadarRequiredTextField surname = new RadarRequiredTextField("surname", form, componentsToUpdateList);
        RadarRequiredTextField forename = new RadarRequiredTextField("forename", form, componentsToUpdateList);
        RadarRequiredDateTextField dateOfBirth = new RadarRequiredDateTextField("dateOfBirth", form,
                componentsToUpdateList);

        dateOfBirth.setRequired(true);

        form.add(diagnosis, surname, forename, dateOfBirth);

        /**
         *  Add basic fields for header too... apparently we can't render same component twice in wicket!..
         *
         *  As we cant set demographicsModelObject as final outside isVisible() implementations there's a bunch of
         *      code duplication
         */

        // forename
        Label nameLabel = new Label("nameLabel", "Name") {
            @Override
            public boolean isVisible() {
                Demographics demographicsModelObject = null;
                if (radarNumberModel.getObject() != null) {
                    Long radarNumber;
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }
                    demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                }

                if (demographicsModelObject == null) {
                    demographicsModelObject = new Demographics();
                }

                return StringUtils.isNotBlank(demographicsModelObject.getForename());
            }
        };
        nameLabel.setOutputMarkupId(true);
        nameLabel.setOutputMarkupPlaceholderTag(true);
        form.add(nameLabel);

        TextField forenameForHeader = new TextField("forenameForHeader", RadarModelFactory.getFirstNameModel(
                radarNumberModel, demographicsManager)) {
            @Override
            public boolean isVisible() {
                Demographics demographicsModelObject = null;
                if (radarNumberModel.getObject() != null) {
                    Long radarNumber;
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }
                    demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                }

                if (demographicsModelObject == null) {
                    demographicsModelObject = new Demographics();
                }

                return StringUtils.isNotBlank(demographicsModelObject.getForename());
            }
        };
        forenameForHeader.setOutputMarkupId(true);
        forenameForHeader.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(forenameForHeader);


        // surname
        TextField surnameForHeader = new TextField("surnameForHeader", RadarModelFactory.getSurnameModel(
                radarNumberModel, demographicsManager)) {
            @Override
            public boolean isVisible() {
                Demographics demographicsModelObject = null;
                if (radarNumberModel.getObject() != null) {
                    Long radarNumber;
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }
                    demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                }

                if (demographicsModelObject == null) {
                    demographicsModelObject = new Demographics();
                }

                return StringUtils.isNotBlank(demographicsModelObject.getSurname());
            }
        };
        surnameForHeader.setOutputMarkupId(true);
        surnameForHeader.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(surnameForHeader);

        // date of birth
        Label dobLabel = new Label("dobLabel", "DoB") {
            @Override
            public boolean isVisible() {
                Demographics demographicsModelObject = null;
                if (radarNumberModel.getObject() != null) {
                    Long radarNumber;
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }
                    demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                }

                if (demographicsModelObject == null) {
                    demographicsModelObject = new Demographics();
                }

                return demographicsModelObject.getDateOfBirth() != null;
            }
        };
        dobLabel.setOutputMarkupId(true);
        dobLabel.setOutputMarkupPlaceholderTag(true);
        form.add(dobLabel);

        TextField dateOfBirthForHeader = new org.apache.wicket.extensions.markup.html.form.DateTextField(
                "dateOfBirthForHeader",
                RadarModelFactory.getDobModel(radarNumberModel, demographicsManager), RadarApplication.DATE_PATTERN) {
            @Override
            public boolean isVisible() {
                Demographics demographicsModelObject = null;
                if (radarNumberModel.getObject() != null) {
                    Long radarNumber;
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }
                    demographicsModelObject = demographicsManager.getDemographicsByRadarNumber(radarNumber);
                }

                if (demographicsModelObject == null) {
                    demographicsModelObject = new Demographics();
                }

                return demographicsModelObject.getDateOfBirth() != null;
            }
        };
        dateOfBirthForHeader.setOutputMarkupId(true);
        dateOfBirthForHeader.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(dateOfBirthForHeader);

        form.add(diagnosis, surnameForHeader, forenameForHeader, dateOfBirthForHeader);

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

        // Archive fields
        TextField surnameAlias = new TextField("surnameAlias");
        TextField previousPostcode = new TextField("previousPostcode");
        form.add(surnameAlias, previousPostcode);

        // More info
        RadarRequiredTextField hospitalNumber =
                new RadarRequiredTextField("hospitalNumber", form, componentsToUpdateList);
        TextField renalRegistryNumber = new TextField("renalRegistryNumber");
        TextField ukTransplantNumber = new TextField("ukTransplantNumber");
        form.add(hospitalNumber, renalRegistryNumber, ukTransplantNumber);

        // Status, consultants and centres drop down boxes
        form.add(new DropDownChoice<Status>("status", demographicsManager.getStatuses(),
                new ChoiceRenderer<Status>("abbreviation", "id")));

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
        DropDownChoice<Centre> renalUnitAuthorised = new CentreDropDown("renalUnitAuthorised");
        form.add(consent, renalUnitAuthorised);

        form.add(new ExternalLink("consentFormsLink", "http://www.rarerenal.org/join/criteria-and-consent/"));

        final Label successMessageTop = RadarComponentFactory.getSuccessMessageLabel("successMessageTop", form,
                componentsToUpdateList);
        final Label successMessageBottom = RadarComponentFactory.getSuccessMessageLabel("successMessageBottom", form,
                componentsToUpdateList);

        Label errorMessage = RadarComponentFactory.getErrorMessageLabel("errorMessage", form, componentsToUpdateList);

        AjaxSubmitLink ajaxSubmitLinkTop = new AjaxSubmitLink("saveTop") {

            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
                successMessageTop.setVisible(true);
                successMessageBottom.setVisible(true);
                ajaxRequestTarget.add(successMessageTop);
                ajaxRequestTarget.add(successMessageBottom);
                ajaxRequestTarget.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
                successMessageTop.setVisible(false);
                successMessageBottom.setVisible(false);
                ajaxRequestTarget.add(successMessageTop);
                ajaxRequestTarget.add(successMessageBottom);
            }
        };

        ajaxSubmitLinkTop.add(new AttributeModifier("value", new AbstractReadOnlyModel() {
            @Override
            public Object getObject() {
                return radarNumberModel.getObject() == null ? "Add this patient" : "Update";
            }
        }));

        form.add(ajaxSubmitLinkTop);

        AjaxSubmitLink ajaxSubmitLinkBottom = new AjaxSubmitLink("saveBottom") {

            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
                successMessageTop.setVisible(true);
                successMessageBottom.setVisible(true);
                ajaxRequestTarget.add(successMessageTop);
                ajaxRequestTarget.add(successMessageBottom);
                ajaxRequestTarget.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdateList.toArray(new Component[componentsToUpdateList.size()]));
                successMessageTop.setVisible(false);
                successMessageBottom.setVisible(false);
                ajaxRequestTarget.add(successMessageTop);
                ajaxRequestTarget.add(successMessageBottom);
            }
        };

        ajaxSubmitLinkBottom.add(new AttributeModifier("value", new AbstractReadOnlyModel() {
            @Override
            public Object getObject() {
                return radarNumberModel.getObject() == null ? "Add this patient" : "Update";
            }
        }));

        form.add(ajaxSubmitLinkBottom);
    }

    @Override
    public boolean isVisible() {
        return ((SrnsPatientPage) getPage()).getCurrentTab().equals(SrnsPatientPage.CurrentTab.DEMOGRAPHICS);
    }
}