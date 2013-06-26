package org.patientview.radar.web.panels.firstvisit;

import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.service.ClinicalDataManager;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.components.ComponentHelper;
import org.patientview.radar.web.components.PhenotypeChooser;
import org.patientview.radar.web.components.RadarComponentFactory;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.components.RadarTextFieldWithValidation;
import org.patientview.radar.web.components.TextAreaWithHelpText;
import org.patientview.radar.web.components.YesNoRadioGroup;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.DiagnosisPanel;
import org.patientview.radar.web.panels.FirstVisitPanel;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClinicalPicturePanel extends Panel {

    // ids of elements where the id is referenced more than once
    private static final String URTICARIA_CONTAINER_ID = "urticariaContainer";
    private static final String URTICARIA_DETAIL_CONTAINER_ID = "urticariaDetailContainer";
    private static final String PARTIAL_LIPODYSTROPHY_CONTAINER_ID = "partialLipodystrophyContainer";
    private static final String PRECEEDING_INFECTION_CONTAINER_ID = "preceedingInfectionContainer";
    private static final String PRECEEDING_INFECTION_DETAIL_CONTAINER_ID = "preceedingInfectionDetailContainer";
    private static final String CHRONIC_INFECTION_ACTIVE_CONTAINER_ID = "chronicInfectionActiveContainer";
    private static final String CHRONIC_INFECTION_DETAIL_CONTAINER_ID = "chronicInfectionDetailContainer";
    private static final String THROMBOSIS_CONTAINER_ID = "thrombosisContainer";
    private static final String PERITONITIS_CONTAINER_ID = "peritonitisContainer";
    private static final String PULMONARY_OEDEMA_CONTAINER_ID = "pulmonaryOedemaContainer";
    private static final String DIABETES_TYPE_CONTAINER_ID = "diabetesTypeContainer";
    private static final String RASH_CONTAINER_ID = "rashContainer";
    private static final String RASH_DETAIL_CONTAINER_ID = "rashDetailContainer";
    private static final String POSSIBLE_IMMUNISATION_TRIGGER_CONTAINER_ID = "possibleImmunisationTriggerContainer";
    public static final String PHENOTYPE_CONTAINER_1 = "phenotypeContainer1";
    public static final String PHENOTYPE_CONTAINER_2 = "phenotypeContainer2";
    public static final String PHENOTYPE_CONTAINER_3 = "phenotypeContainer3";
    public static final String PHENOTYPE_CONTAINER_4 = "phenotypeContainer4";
    public static final String PATIENT_DETAILS_CONTAINER = "patientDetailsContainer";

    // elements to hide if diagnosis is srns
    private List<String> srnsElementsToHide;

    // elements to hide if diagnosis is mpgn
    private List<String> mpgnElementsToHide;

    // elements to hide if diagnosis is srns and followup
    private List<String> srnsElementsToHideFollowup;

    @SpringBean
    private ClinicalDataManager clinicalDataManager;
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    public ClinicalPicturePanel(String id, final IModel<Long> radarNumberModel, final boolean isFirstVisit) {

        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // set srns elements to hide
        srnsElementsToHide = Arrays.asList(URTICARIA_CONTAINER_ID, URTICARIA_DETAIL_CONTAINER_ID,
                PARTIAL_LIPODYSTROPHY_CONTAINER_ID, PRECEEDING_INFECTION_CONTAINER_ID,
                PRECEEDING_INFECTION_DETAIL_CONTAINER_ID, CHRONIC_INFECTION_ACTIVE_CONTAINER_ID,
                CHRONIC_INFECTION_DETAIL_CONTAINER_ID);

        // set mpgn elements to hide
        mpgnElementsToHide = Arrays.asList(THROMBOSIS_CONTAINER_ID, PERITONITIS_CONTAINER_ID,
                PULMONARY_OEDEMA_CONTAINER_ID, DIABETES_TYPE_CONTAINER_ID, RASH_CONTAINER_ID, RASH_DETAIL_CONTAINER_ID,
                POSSIBLE_IMMUNISATION_TRIGGER_CONTAINER_ID, PHENOTYPE_CONTAINER_1, PHENOTYPE_CONTAINER_2,
                PHENOTYPE_CONTAINER_3, PHENOTYPE_CONTAINER_4);

        // set srns elements to hide on follow up
        srnsElementsToHideFollowup = Arrays.asList(PATIENT_DETAILS_CONTAINER);

        final WebMarkupContainer clinicalPictureContainer = new WebMarkupContainer("clinicalPictureContainer");
        clinicalPictureContainer.setVisible(isFirstVisit);
        clinicalPictureContainer.setOutputMarkupId(true);
        clinicalPictureContainer.setOutputMarkupPlaceholderTag(true);
        add(clinicalPictureContainer);

        final TextField<Double> diastolicBloodPressure = new TextField<Double>("diastolicBloodPressure");

        final CompoundPropertyModel<ClinicalData> firstVisitModel =
                new CompoundPropertyModel<ClinicalData>(new LoadableDetachableModel<ClinicalData>() {
                    @Override
                    protected ClinicalData load() {
                        if (radarNumberModel.getObject() != null) {
                            // If we have a radar number get the list from DAO
                            ClinicalData clinicalData;
                            clinicalData = clinicalDataManager.getFirstClinicalDataByRadarNumber(radarNumberModel.
                                    getObject());

                            if (clinicalData != null) {
                                return clinicalData;
                            }
                        }
                        // By default just return new one
                        ClinicalData clinicalDataNew = new ClinicalData();
                        clinicalDataNew.setSequenceNumber(1);
                        return clinicalDataNew;
                    }
                });


        final IModel<ClinicalData> followUpModel = new LoadableDetachableModel<ClinicalData>() {
            private Long id;

            @Override
            protected ClinicalData load() {
                if (id == null) {
                    return new ClinicalData();
                } else {
                    return clinicalDataManager.getClinicalData(id);
                }
            }

            @Override
            public void detach() {
                ClinicalData clinicalData = getObject();
                if (clinicalData != null) {
                    id = clinicalData.getId();
                }
                super.detach();
            }
        };

        final IModel<ClinicalData> formModel;
        if (isFirstVisit) {
            formModel = firstVisitModel;
        } else {
            formModel = new CompoundPropertyModel<ClinicalData>(followUpModel);
        }

        IModel<List> clinicalPictureListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = clinicalDataManager.getClinicalDataByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        WebMarkupContainer followupContainer = new WebMarkupContainer("followupContainer");
        followupContainer.setVisible(!isFirstVisit);
        followupContainer.setOutputMarkupPlaceholderTag(true);

        final DropDownChoice clinicalPicturesSwitcher = new DropDownChoice("clinicalPicturesSwitcher", followUpModel,
                clinicalPictureListModel, new DateChoiceRenderer("clinicalPictureDate", "id") {
            @Override
            protected Date getDate(Object object) {
                return ((ClinicalData) object).getClinicalPictureDate();
            }
        });

        clinicalPicturesSwitcher.setNullValid(true);

        clinicalPicturesSwitcher.setOutputMarkupId(true);
        clinicalPictureContainer.setOutputMarkupPlaceholderTag(true);
        followupContainer.add(clinicalPicturesSwitcher);
        clinicalPicturesSwitcher.add(new AjaxFormComponentUpdatingBehavior("onChange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                clinicalPictureContainer.setVisible(true);
                target.add(clinicalPictureContainer);

            }
        });

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                ClinicalData clinicalData = new ClinicalData();
                Diagnosis daignosis = RadarModelFactory.getDiagnosisModel(radarNumberModel, diagnosisManager).
                        getObject();
                if (daignosis != null) {
                    clinicalData.setSignificantDiagnosis1(daignosis.getSignificantDiagnosis1());
                    clinicalData.setSignificantDiagnosis2(daignosis.getSignificantDiagnosis2());
                }

                ClinicalData firstClinicalData = clinicalDataManager.getFirstClinicalDataByRadarNumber(radarNumberModel.
                        getObject());
                if (firstClinicalData != null) {
                    clinicalData.setPhenotype1(firstClinicalData.getPhenotype1());
                    clinicalData.setPhenotype2(firstClinicalData.getPhenotype2());
                    clinicalData.setPhenotype3(firstClinicalData.getPhenotype3());
                    clinicalData.setPhenotype4(firstClinicalData.getPhenotype4());
                }

                formModel.setObject(clinicalData);
                clinicalPictureContainer.setVisible(true);
                clinicalPicturesSwitcher.clearInput();
                target.add(clinicalPictureContainer, clinicalPicturesSwitcher);

            }
        };

        followupContainer.add(addNew);
        add(followupContainer);

        final List<Component> componentsToUpdate = new ArrayList<Component>();
        if (clinicalPicturesSwitcher.isVisible()) {
            componentsToUpdate.add(clinicalPicturesSwitcher);
        }

        final Form<ClinicalData> form = new Form<ClinicalData>("form", formModel) {
            @Override
            protected void onValidateModelObjects() {
                super.onValidateModelObjects();
                ClinicalData clinicalData = getModelObject();
                Integer systolicBloodPressureVal = clinicalData.getSystolicBloodPressure();
                Integer diastolicBloodPressureVal = clinicalData.getDiastolicBloodPressure();
                if (systolicBloodPressureVal != null && diastolicBloodPressureVal != null) {
                    if (!(systolicBloodPressureVal.compareTo(diastolicBloodPressureVal) > 0)) {
                        diastolicBloodPressure.error("This value has to be less than the first value");
                    }
                }

            }

            @Override
            protected void onSubmit() {
                ClinicalData clinicalData = getModelObject();
                Long radarNumber;

                if (clinicalData.getRadarNumber() == null) {
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        radarNumber = Long.parseLong((String) obj);
                    }

                    clinicalData.setRadarNumber(radarNumber);
                }
                clinicalDataManager.saveClinicalDate(clinicalData);

                // if first visit - follow through phenotypes to following visit records
                if (isFirstVisit) {
                    List<ClinicalData> clinicalDatas = clinicalDataManager.getClinicalDataByRadarNumber(
                            clinicalData.getRadarNumber());

                    for (ClinicalData cd : clinicalDatas) {
                        // ignore first visit
                        if (clinicalData.getId().equals(cd.getId())) {
                            continue;
                        }
                        cd.setPhenotype1(clinicalData.getPhenotype1());
                        cd.setPhenotype2(clinicalData.getPhenotype2());
                        cd.setPhenotype3(clinicalData.getPhenotype3());
                        cd.setPhenotype4(clinicalData.getPhenotype4());
                        clinicalDataManager.saveClinicalDate(cd);
                    }
                }
            }
        };

        clinicalPictureContainer.add(form);

        final IModel<Boolean> isSrnsModel = new AbstractReadOnlyModel<Boolean>() {
            private DiagnosisCode diagnosisCode = null;

            @Override
            public Boolean getObject() {
                if (diagnosisCode == null) {
                    if (radarNumberModel.getObject() != null) {
                        Diagnosis diagnosis = diagnosisManager.getDiagnosisByRadarNumber(radarNumberModel.getObject());
                        diagnosisCode = diagnosis != null ? diagnosis.getDiagnosisCode() : null;
                    }
                }

                if (diagnosisCode != null) {
                    return diagnosisCode.getId().equals(DiagnosisPanel.SRNS_ID);
                }
                return false;
            }
        };

        Label successLabel = RadarComponentFactory.getSuccessMessageLabel("successMessage", form, componentsToUpdate);
        Label successLabelDown = RadarComponentFactory.getSuccessMessageLabel("successMessageDown", form,
                componentsToUpdate);

        Label errorLabel = RadarComponentFactory.getErrorMessageLabel("errorMessage", form, componentsToUpdate);
        Label errorLabelDown = RadarComponentFactory.getErrorMessageLabel("errorMessageDown", form,
                componentsToUpdate);


        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        form.add(radarNumber);

        form.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(radarNumberModel,
                demographicsManager)));


        form.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisManager), "abbreviation")));

        form.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                demographicsManager)));

        form.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsManager)));


        form.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsManager),
                RadarApplication.DATE_PATTERN));

        RadarRequiredDateTextField clinicalPictureDate =
                new RadarRequiredDateTextField("clinicalPictureDate", form, componentsToUpdate);
        form.add(clinicalPictureDate);

        RadarTextFieldWithValidation height =
                new RadarTextFieldWithValidation("height", new RangeValidator<Double>(
                        RadarApplication.MIN_HEIGHT, RadarApplication.MAX_HEIGHT), form,
                        componentsToUpdate);
        form.add(height);

        RadarTextFieldWithValidation weight =
                new RadarTextFieldWithValidation("weight", new RangeValidator<Double>(3.0, 100.0), form,
                        componentsToUpdate);
        form.add(weight);
        // Blood pressure
        TextField<Double> systolicBloodPressure = new TextField("systolicBloodPressure");
        systolicBloodPressure.add(new RangeValidator<Integer>(50, 200));
        form.add(systolicBloodPressure);

        final ComponentFeedbackPanel systolicBloodPressureFeedback =
                new ComponentFeedbackPanel("systolicBloodPressureFeedback", systolicBloodPressure);
        systolicBloodPressureFeedback.setOutputMarkupId(true);
        systolicBloodPressureFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(systolicBloodPressureFeedback);

        diastolicBloodPressure.add(new RangeValidator<Integer>(20, 150));
        form.add(diastolicBloodPressure);

        final ComponentFeedbackPanel diastolicBloodPressureFeedback =
                new ComponentFeedbackPanel("diastolicBloodPressureFeedback", diastolicBloodPressure);
        diastolicBloodPressureFeedback.setOutputMarkupId(true);
        diastolicBloodPressureFeedback.setOutputMarkupPlaceholderTag(true);
        form.add(diastolicBloodPressureFeedback);

        Component meanArterialPressure = new TextField("meanArterialPressure").setEnabled(false);
        meanArterialPressure.setOutputMarkupPlaceholderTag(true);
        meanArterialPressure.setOutputMarkupId(true);
        form.add(meanArterialPressure);
        componentsToUpdate.add(meanArterialPressure);

        WebMarkupContainer phenotypeContainer1 = new WebMarkupContainer(PHENOTYPE_CONTAINER_1) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        WebMarkupContainer phenotypeContainer2 = new WebMarkupContainer(PHENOTYPE_CONTAINER_2) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        WebMarkupContainer phenotypeContainer3 = new WebMarkupContainer(PHENOTYPE_CONTAINER_3) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        WebMarkupContainer phenotypeContainer4 = new WebMarkupContainer(PHENOTYPE_CONTAINER_4) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        phenotypeContainer1.add(new PhenotypeChooser("phenotype1"));
        phenotypeContainer2.add(new PhenotypeChooser("phenotype2"));
        phenotypeContainer3.add(new PhenotypeChooser("phenotype3"));
        phenotypeContainer4.add(new PhenotypeChooser("phenotype4"));

        form.add(phenotypeContainer1);
        form.add(phenotypeContainer2);
        form.add(phenotypeContainer3);
        form.add(phenotypeContainer4);

        form.add(new TextArea("comments"));
        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Yes/No/Unknown for the following
        WebMarkupContainer patientDetailsContainer = new WebMarkupContainer(PATIENT_DETAILS_CONTAINER) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        patientDetailsContainer.setOutputMarkupPlaceholderTag(true);
        form.add(patientDetailsContainer);

        patientDetailsContainer.add(new YesNoRadioGroup("oedema", true));
        patientDetailsContainer.add(new YesNoRadioGroup("hypovalaemia", true));
        patientDetailsContainer.add(new YesNoRadioGroup("fever", true));

        WebMarkupContainer thrombosisContainer = new WebMarkupContainer(THROMBOSIS_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        YesNoRadioGroup thrombosis = new YesNoRadioGroup("thrombosis", true);
        thrombosisContainer.add(thrombosis);
        patientDetailsContainer.add(thrombosisContainer);

        WebMarkupContainer peritonitisContainer = new WebMarkupContainer(PERITONITIS_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        peritonitisContainer.add(new YesNoRadioGroup("peritonitis", true));
        patientDetailsContainer.add(peritonitisContainer);

        WebMarkupContainer pulmonaryOedemaContainer = new WebMarkupContainer(PULMONARY_OEDEMA_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        pulmonaryOedemaContainer.add(new YesNoRadioGroup("pulmonaryOedema", true));
        patientDetailsContainer.add(pulmonaryOedemaContainer);
        patientDetailsContainer.add(new YesNoRadioGroup("hypertension", true));

        //urticaria
        boolean showUrticariaOnInit = form.getModelObject().getUrticaria() != null ? form.getModelObject().
                getUrticaria() : false;

        // only show if diag is mpgn/dd
        if (isSrnsModel.getObject().equals(true)) {
            showUrticariaOnInit = false;
        }

        final IModel<Boolean> showUrticariaIModel = new Model<Boolean>(showUrticariaOnInit);

        MarkupContainer urticariaDetailContainer = new WebMarkupContainer(URTICARIA_DETAIL_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                if (!hideElement(isFirstVisit, isSrnsModel.getObject(), getId())) {
                    return showUrticariaIModel.getObject();
                }
                return false;
            }
        };
        componentsToUpdate.add(urticariaDetailContainer);

        urticariaDetailContainer.add(new TextArea("rashDetail")); // shares same field in db as rash detail It seems
        patientDetailsContainer.add(urticariaDetailContainer);
        urticariaDetailContainer.setOutputMarkupId(true);
        urticariaDetailContainer.setOutputMarkupPlaceholderTag(true);

        // More yes/no options
        WebMarkupContainer urticariaContainer = new WebMarkupContainer(URTICARIA_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        YesNoRadioGroup urticaria = new YesNoRadioGroup("urticaria", true);
        urticariaContainer.add(urticaria);
        patientDetailsContainer.add(urticariaContainer);
        urticaria.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showUrticariaIModel.setObject(Boolean.TRUE.equals(form.getModelObject().getUrticaria()));
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdate);
            }
        });

        // Diabetes
        WebMarkupContainer diabetesTypeContainer = new WebMarkupContainer(DIABETES_TYPE_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        diabetesTypeContainer.add(new DropDownChoice<ClinicalData.DiabetesType>("diabetesType",
                Arrays.asList(ClinicalData.DiabetesType.TYPE_I, ClinicalData.DiabetesType.TYPE_II,
                        ClinicalData.DiabetesType.NO), new ChoiceRenderer<ClinicalData.DiabetesType>("label", "id")));
        patientDetailsContainer.add(diabetesTypeContainer);

        boolean showRashDetailsOnInit = form.getModelObject().getRash() != null ? form.getModelObject().getRash()
                : false;
        // only show if diag is srns
        if (isSrnsModel.getObject().equals(false)) {
            showRashDetailsOnInit = false;
        }

        final IModel<Boolean> showRashDetailsIModel = new Model<Boolean>(showRashDetailsOnInit);


        // Rash details needs show/hide
        final MarkupContainer rashDetailContainer = new WebMarkupContainer(RASH_DETAIL_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                if (!hideElement(isFirstVisit, isSrnsModel.getObject(), getId())) {
                    return showRashDetailsIModel.getObject();
                }
                return false;
            }
        };

        rashDetailContainer.add(new TextArea("rashDetail"));
        patientDetailsContainer.add(rashDetailContainer);
        rashDetailContainer.setOutputMarkupId(true);
        rashDetailContainer.setOutputMarkupPlaceholderTag(true);
        componentsToUpdate.add(rashDetailContainer);

        // More yes/no options
        WebMarkupContainer rashContainer = new WebMarkupContainer(RASH_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };
        YesNoRadioGroup rash = new YesNoRadioGroup("rash", true);
        rashContainer.add(rash);
        patientDetailsContainer.add(rashContainer);

        rash.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showRashDetailsIModel.setObject(Boolean.TRUE.equals(form.getModelObject().getRash()));
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdate);
            }
        });

        WebMarkupContainer possibleImmunisationTriggerContainer = new WebMarkupContainer(
                POSSIBLE_IMMUNISATION_TRIGGER_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        YesNoRadioGroup possibleImmunisationTrigger = new YesNoRadioGroup("possibleImmunisationTrigger", true);
        possibleImmunisationTriggerContainer.add(possibleImmunisationTrigger);
        patientDetailsContainer.add(possibleImmunisationTriggerContainer);


        WebMarkupContainer partialLipodystrophyContainer = new WebMarkupContainer(PARTIAL_LIPODYSTROPHY_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        YesNoRadioGroup partialLipodystrophy = new YesNoRadioGroup("partialLipodystrophy", true);

        partialLipodystrophyContainer.add(partialLipodystrophy);
        patientDetailsContainer.add(partialLipodystrophyContainer);

        WebMarkupContainer preceedingInfectionContainer = new WebMarkupContainer(PRECEEDING_INFECTION_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        // preceding infection
        boolean showPrecedingInfectionOnInit = form.getModelObject().getPreceedingInfection() != null ?
                form.getModelObject().getPreceedingInfection() : false;

        // only show if diag is mpgn/dd
        if (isSrnsModel.getObject().equals(true)) {
            showPrecedingInfectionOnInit = false;
        }

        final IModel<Boolean> showPrecedingInfectioModel = new Model<Boolean>(showPrecedingInfectionOnInit);

        YesNoRadioGroup preceedingInfection = new YesNoRadioGroup("preceedingInfection", true);
        preceedingInfectionContainer.add(preceedingInfection);
        preceedingInfection.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showPrecedingInfectioModel.setObject(Boolean.TRUE.equals(form.getModelObject().
                        getPreceedingInfection()));
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdate);
            }
        });
        patientDetailsContainer.add(preceedingInfectionContainer);

        // Preceeding infection show/hide
        MarkupContainer preceedingInfectionDetailContainer =
                new WebMarkupContainer(PRECEEDING_INFECTION_DETAIL_CONTAINER_ID) {
                    @Override
                    public boolean isVisible() {
                        return showPrecedingInfectioModel.getObject();
                    }
                };
        TextAreaWithHelpText preceedingInfectionDetail = new TextAreaWithHelpText("preceedingInfectionDetail",
                "Enter details") {
            @Override
            public String getModelData() {
                ClinicalData clinicalData = formModel.getObject();
                return clinicalData != null ? clinicalData.getPreceedingInfectionDetail() != null ?
                        clinicalData.getPreceedingInfectionDetail() : "" : "";
            }

            @Override
            public void setModelData(String data) {
                ClinicalData clinicalData = formModel.getObject();
                if (clinicalData != null) {
                    clinicalData.setPreceedingInfectionDetail(data);
                }
            }
        };
        preceedingInfectionDetail.initBehaviour();
        preceedingInfectionDetailContainer.add(preceedingInfectionDetail);
        patientDetailsContainer.add(preceedingInfectionDetailContainer);
        componentsToUpdate.add(preceedingInfectionDetailContainer);
        preceedingInfectionDetailContainer.setOutputMarkupId(true);
        preceedingInfectionDetailContainer.setOutputMarkupPlaceholderTag(true);

        // chronic infection
        boolean showChronicOnInit = form.getModelObject().getChronicInfection() != null ? form.getModelObject().
                getChronicInfection() : false;

        // only show if diag is mpgn/dd
        if (isSrnsModel.getObject().equals(true)) {
            showChronicOnInit = false;
        }

        final IModel<Boolean> showChronicModel = new Model<Boolean>(showChronicOnInit);

        WebMarkupContainer chronicInfectionActiveContainer = new WebMarkupContainer(
                CHRONIC_INFECTION_ACTIVE_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                return !hideElement(isFirstVisit, isSrnsModel.getObject(), getId());
            }
        };

        YesNoRadioGroup chronicInfection = new YesNoRadioGroup("chronicInfection", true);
        chronicInfection.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showChronicModel.setObject(Boolean.TRUE.equals(form.getModelObject().getChronicInfection()));
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdate);
            }
        });
        chronicInfectionActiveContainer.add(chronicInfection);
        patientDetailsContainer.add(chronicInfectionActiveContainer);

        // Chronic infection show/hide
        MarkupContainer chronicInfectionDetailContainer = new WebMarkupContainer(
                CHRONIC_INFECTION_DETAIL_CONTAINER_ID) {
            @Override
            public boolean isVisible() {
                if (!hideElement(isFirstVisit, isSrnsModel.getObject(), getId())) {
                    return showChronicModel.getObject();
                }
                return false;
            }
        };
        TextAreaWithHelpText chronicInfectionDetail = new TextAreaWithHelpText("chronicInfectionDetail",
                "Enter Details") {
            @Override
            public String getModelData() {
                ClinicalData clinicalData = formModel.getObject();
                return clinicalData != null ? clinicalData.getChronicInfectionDetail() != null ?
                        clinicalData.getChronicInfectionDetail() : "" : "";
            }

            @Override
            public void setModelData(String data) {
                ClinicalData clinicalData = formModel.getObject();
                if (clinicalData != null) {
                    clinicalData.setChronicInfectionDetail(data);
                }
            }
        };

        chronicInfectionDetail.initBehaviour();

        chronicInfectionDetailContainer.add(chronicInfectionDetail);
        componentsToUpdate.add(chronicInfectionDetailContainer);
        chronicInfectionDetailContainer.setOutputMarkupId(true);
        chronicInfectionDetailContainer.setOutputMarkupPlaceholderTag(true);

        patientDetailsContainer.add(chronicInfectionDetailContainer);

        boolean showOphthalmoscopyDetailsOnInit = form.getModelObject().getOphthalmoscopy() != null ?
                form.getModelObject().getOphthalmoscopy() : false;

        final IModel<Boolean> showOphthalmoscopyDetailsIModel = new Model<Boolean>(showOphthalmoscopyDetailsOnInit);

        YesNoRadioGroup ophthalmoscopy = new YesNoRadioGroup("ophthalmoscopy", true);
        patientDetailsContainer.add(ophthalmoscopy);
        ophthalmoscopy.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showOphthalmoscopyDetailsIModel.setObject(Boolean.TRUE.equals(form.getModelObject().
                        getOphthalmoscopy()));
                ComponentHelper.updateComponentsIfParentIsVisible(target, componentsToUpdate);
            }
        });

        // Ophthalmoscopy show/hide
        MarkupContainer ophthalmoscopyDetailContainer = new WebMarkupContainer("ophthalmoscopyDetailContainer") {
            @Override
            public boolean isVisible() {
                return showOphthalmoscopyDetailsIModel.getObject();
            }
        };
        ophthalmoscopyDetailContainer.setOutputMarkupId(true);
        ophthalmoscopyDetailContainer.setOutputMarkupPlaceholderTag(true);
        componentsToUpdate.add(ophthalmoscopyDetailContainer);

        TextAreaWithHelpText ophthalmoscopyDetail = new TextAreaWithHelpText("ophthalmoscopyDetail", ClinicalData.
                OPHTHALMOSCOPY_HELP_TEXT) {
            @Override
            public String getModelData() {
                ClinicalData clinicalData = formModel.getObject();
                return clinicalData != null ? clinicalData.getOphthalmoscopyDetail() != null ?
                        clinicalData.getOphthalmoscopyDetail() : "" : "";
            }

            @Override
            public void setModelData(String data) {
                ClinicalData clinicalData = formModel.getObject();
                if (clinicalData != null) {
                    clinicalData.setOphthalmoscopyDetail(data);
                }
            }
        };

        ophthalmoscopyDetail.initBehaviour();

        ophthalmoscopyDetailContainer.add(ophthalmoscopyDetail);

        patientDetailsContainer.add(ophthalmoscopyDetailContainer);

        componentsToUpdate.add(systolicBloodPressureFeedback);
        componentsToUpdate.add(diastolicBloodPressureFeedback);

        // Complications
        WebMarkupContainer complicationsContainer = new WebMarkupContainer("complicationsContainer") {
            @Override
            public boolean isVisible() {
                return !isFirstVisit && isSrnsModel.getObject();
            }
        };

        complicationsContainer.add(new YesNoRadioGroup("infectionNecessitatingHospitalisation", false, false));
        MarkupContainer infectionDetailContainer = new WebMarkupContainer("infectionDetailContainer");
        infectionDetailContainer.add(new TextArea("infectionDetail"));
        complicationsContainer.add(infectionDetailContainer);

        complicationsContainer.add(new YesNoRadioGroup("complicationThrombosis", false, false));
        MarkupContainer complicationThrombosisDetailContainer =
                new WebMarkupContainer("complicationThrombosisContainer");
        complicationThrombosisDetailContainer.add(new TextArea("complicationThrombosisDetail"));
        complicationsContainer.add(complicationThrombosisDetailContainer);

        // Hypertension
        complicationsContainer.add(new YesNoRadioGroup("hypertension", true));

        // CKD stage
        complicationsContainer.add(new CkdStageRadioGroup("ckdStage"));
        form.add(complicationsContainer);

        // Listed for transplant?

        WebMarkupContainer listedForTransplantContainer = new WebMarkupContainer("listedForTransplantContainer") {
            @Override
            public boolean isVisible() {
                return !isFirstVisit && isSrnsModel.getObject();
            }
        };

        form.add(listedForTransplantContainer);
        listedForTransplantContainer.add(new YesNoRadioGroup("listedForTransplant"));

        ClinicalAjaxSubmitLink save = new ClinicalAjaxSubmitLink("save") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };

        ClinicalAjaxSubmitLink saveDown = new ClinicalAjaxSubmitLink("saveDown") {
            @Override
            protected List<? extends Component> getComponentsToUpdate() {
                return componentsToUpdate;
            }
        };

        form.add(save, saveDown);
    }

    @Override
    public boolean isVisible() {
        if (getParent() instanceof FirstVisitPanel) {
            return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.CLINICAL_PICTURE);
        } else if (getParent() instanceof org.patientview.radar.web.panels.followup.ClinicalPicturePanel) {
            return true;
        }
        return false;
    }

    private boolean hideElement(boolean isFirstVisit, boolean isSrns, String componenetId) {
        boolean hideElement = false;

        // hide on both first and second visit
        if (isSrns) {
            hideElement = srnsElementsToHide.contains(componenetId);
        } else if (!isSrns) {
            hideElement = mpgnElementsToHide.contains(componenetId);
        }

        // if not still hidden
        if (!hideElement) {
            // hid only on srns follow up
            if (!isFirstVisit) {
                if (isSrns) {
                    hideElement = srnsElementsToHideFollowup.contains(componenetId);
                }
            }
        }

        return hideElement;
    }

    private abstract class ClinicalAjaxSubmitLink extends AjaxSubmitLink {

        public ClinicalAjaxSubmitLink(String id) {
            super(id);
        }

        @Override
        public void onSubmit(AjaxRequestTarget target, Form<?> form) {
            ComponentHelper.updateComponentsIfParentIsVisible(target, getComponentsToUpdate());
            target.appendJavaScript(RadarApplication.FORM_IS_DIRTY_FALSE_SCRIPT);
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            ComponentHelper.updateComponentsIfParentIsVisible(target, getComponentsToUpdate());
        }

        protected abstract List<? extends Component> getComponentsToUpdate();
    }

    private final class CkdStageRadioGroup extends RadioGroup<ClinicalData.CkdStage> {
        private CkdStageRadioGroup(String id) {
            super(id);

            Radio<ClinicalData.CkdStage> ckdStageOne =
                    new Radio<ClinicalData.CkdStage>("one",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.ONE));
            add(ckdStageOne, new FormComponentLabel("oneLabel", ckdStageOne));

            Radio<ClinicalData.CkdStage> ckdStageTwo =
                    new Radio<ClinicalData.CkdStage>("two",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.TWO));
            add(ckdStageTwo, new FormComponentLabel("twoLabel", ckdStageTwo));

            Radio<ClinicalData.CkdStage> ckdStageThree = new Radio<ClinicalData.CkdStage>("three",
                    new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.THREE));
            add(ckdStageThree, new FormComponentLabel("threeLabel", ckdStageThree));

            Radio<ClinicalData.CkdStage> ckdStageFour =
                    new Radio<ClinicalData.CkdStage>("four",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.FOUR));
            add(ckdStageFour, new FormComponentLabel("fourLabel", ckdStageFour));

            Radio<ClinicalData.CkdStage> ckdStageFive =
                    new Radio<ClinicalData.CkdStage>("five",
                            new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.FIVE));
            add(ckdStageFive, new FormComponentLabel("fiveLabel", ckdStageFive));

            Radio<ClinicalData.CkdStage> ckdStageUnknown = new Radio<ClinicalData.CkdStage>("unknown",
                    new Model<ClinicalData.CkdStage>(ClinicalData.CkdStage.UNKNOWN));
            add(ckdStageUnknown, new FormComponentLabel("unknownLabel", ckdStageUnknown));
        }
    }
}
