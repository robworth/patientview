package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.sequenced.ClinicalData;
import com.solidstategroup.radar.web.components.PhenotypeChooser;
import com.solidstategroup.radar.web.components.RadarComponentFactory;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.panels.DiagnosisPanel;
import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
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
import java.util.List;

public class ClinicalPicturePanel extends Panel {

    @SpringBean
    private ClinicalDataDao clinicalDataDao;
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;

    public ClinicalPicturePanel(String id, final IModel<Long> radarNumberModel, final boolean firstVisit) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final WebMarkupContainer clinicalPictureContainer = new WebMarkupContainer("clinicalPictureContainer");
        clinicalPictureContainer.setVisible(firstVisit);
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
                            clinicalData = clinicalDataDao.getFirstClinicalDataByRadarNumber(radarNumberModel.
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

        final IModel<ClinicalData> followUpModel = new Model<ClinicalData>(new ClinicalData());

        final IModel<ClinicalData> formModel;
        if (firstVisit) {
            formModel = firstVisitModel;
        } else {
            formModel = new CompoundPropertyModel(followUpModel);
        }

        IModel<List> clinicalPictureListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = clinicalDataDao.getClinicalDataByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice clinicalPicturesDropdown = new DropDownChoice("clinicalPictures", followUpModel,
                clinicalPictureListModel, new ChoiceRenderer("clinicalPictureDate", "id"));
        clinicalPicturesDropdown.setOutputMarkupId(true);
        clinicalPictureContainer.setOutputMarkupPlaceholderTag(true);
        clinicalPicturesDropdown.setVisible(!firstVisit);
        add(clinicalPicturesDropdown);
        clinicalPicturesDropdown.add(new AjaxFormComponentUpdatingBehavior("onChange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(clinicalPictureContainer);
                clinicalPictureContainer.setVisible(true);
            }
        });

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                formModel.setObject(new ClinicalData());
                clinicalPictureContainer.setVisible(true);
                target.add(clinicalPictureContainer);
            }
        };

        addNew.setVisible(!firstVisit);
        add(addNew);

        final List<Component> componentsToUpdate = new ArrayList<Component>();
        if (clinicalPicturesDropdown.isVisible()) {
            componentsToUpdate.add(clinicalPicturesDropdown);
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
                clinicalDataDao.saveClinicalDate(clinicalData);
            }
        };

        clinicalPictureContainer.add(form);

        final IModel<Boolean> isSrnsModel = new AbstractReadOnlyModel<Boolean>() {
            private DiagnosisCode diagnosisCode = null;

            @Override
            public Boolean getObject() {
                if (diagnosisCode == null) {
                    if (radarNumberModel.getObject() != null) {
                        diagnosisCode = diagnosisDao.getDiagnosisByRadarNumber(radarNumberModel.getObject())
                                .getDiagnosisCode();
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
                demographicsDao)));


        form.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.getDiagnosisCodeModel(radarNumberModel,
                diagnosisDao), "abbreviation")));

        form.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel, demographicsDao)));

        form.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel, demographicsDao)));


        form.add(new TextField("dob", RadarModelFactory.getDobModel(radarNumberModel, demographicsDao)));

        RadarRequiredDateTextField clinicalPictureDate =
                new RadarRequiredDateTextField("clinicalPictureDate", form, componentsToUpdate);
        form.add(clinicalPictureDate);

        RadarTextFieldWithValidation height =
                new RadarTextFieldWithValidation("height", new RangeValidator<Double>(35.0, 185.0), form,
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

        form.add(new PhenotypeChooser("phenotype1"));
        form.add(new PhenotypeChooser("phenotype2"));
        form.add(new PhenotypeChooser("phenotype3"));
        form.add(new PhenotypeChooser("phenotype4"));

        form.add(new TextArea("comments"));
        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Yes/No/Unknown for the following
        form.add(new YesNoRadioGroup("oedema", true));
        form.add(new YesNoRadioGroup("hypovalaemia", true));
        form.add(new YesNoRadioGroup("fever", true));

        WebMarkupContainer thrombosisContainer = new WebMarkupContainer("thrombosisContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        YesNoRadioGroup thrombosis = new YesNoRadioGroup("thrombosis", true);
        thrombosisContainer.add(thrombosis);
        form.add(thrombosisContainer);

        WebMarkupContainer peritonitisContainer = new WebMarkupContainer("peritonitisContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        peritonitisContainer.add(new YesNoRadioGroup("peritonitis", true));
        form.add(peritonitisContainer);

        WebMarkupContainer pulmonaryOedemaContainer = new WebMarkupContainer("pulmonaryOedemaContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        pulmonaryOedemaContainer.add(new YesNoRadioGroup("pulmonaryOedema", true));
        form.add(pulmonaryOedemaContainer);
        form.add(new YesNoRadioGroup("hypertension", true));

        //urticaria
        boolean showUrticariaOnInit = form.getModelObject().getUrticaria() != null ? form.getModelObject().
                getUrticaria() : false;

        // only show if diag is mpgn/dd
        if (isSrnsModel.getObject().equals(true)) {
            showUrticariaOnInit = false;
        }

        final IModel<Boolean> showUrticariaIModel = new Model<Boolean>(showUrticariaOnInit);

        MarkupContainer urticariaDetailContainer = new WebMarkupContainer("urticariaDetailContainer") {
            @Override
            public boolean isVisible() {
                return showUrticariaIModel.getObject();
            }
        };
        componentsToUpdate.add(urticariaDetailContainer);

        urticariaDetailContainer.add(new TextArea("rashDetail")); // shares same field in db as rash detail It seems
        form.add(urticariaDetailContainer);
        urticariaDetailContainer.setOutputMarkupId(true);
        urticariaDetailContainer.setOutputMarkupPlaceholderTag(true);

        // More yes/no options
        WebMarkupContainer urticariaContainer = new WebMarkupContainer("urticariaContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject().equals(false);
            }
        };
        YesNoRadioGroup urticaria = new YesNoRadioGroup("urticaria", true);
        urticariaContainer.add(urticaria);
        form.add(urticariaContainer);
        urticaria.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showUrticariaIModel.setObject(Boolean.TRUE.equals(form.getModelObject().getUrticaria()));
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });

        // Diabetes
        WebMarkupContainer diabetesTypeContainer = new WebMarkupContainer("diabetesTypeContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        diabetesTypeContainer.add(new DropDownChoice<ClinicalData.DiabetesType>("diabetesType",
                Arrays.asList(ClinicalData.DiabetesType.TYPE_I, ClinicalData.DiabetesType.TYPE_II,
                        ClinicalData.DiabetesType.NO), new ChoiceRenderer<ClinicalData.DiabetesType>("label", "id")));
        form.add(diabetesTypeContainer);

        boolean showRashDetailsOnInit = form.getModelObject().getRash() != null ? form.getModelObject().getRash()
                : false;
        // only show if diag is srns
        if (isSrnsModel.getObject().equals(false)) {
            showRashDetailsOnInit = false;
        }

        final IModel<Boolean> showRashDetailsIModel = new Model<Boolean>(showRashDetailsOnInit);


        // Rash details needs show/hide
        final MarkupContainer rashDetailContainer = new WebMarkupContainer("rashDetailContainer") {
            @Override
            public boolean isVisible() {
                return showRashDetailsIModel.getObject();
            }
        };

        rashDetailContainer.add(new TextArea("rashDetail"));
        form.add(rashDetailContainer);
        rashDetailContainer.setOutputMarkupId(true);
        rashDetailContainer.setOutputMarkupPlaceholderTag(true);
        componentsToUpdate.add(rashDetailContainer);

        // More yes/no options
        WebMarkupContainer rashContainer = new WebMarkupContainer("rashContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        YesNoRadioGroup rash = new YesNoRadioGroup("rash", true);
        rashContainer.add(rash);
        form.add(rashContainer);

        rash.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showRashDetailsIModel.setObject(Boolean.TRUE.equals(form.getModelObject().getRash()));
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });

        WebMarkupContainer possibleImmunisationTriggerContainer = new WebMarkupContainer
                ("possibleImmunisationTriggerContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject().equals(true);
            }
        };

        YesNoRadioGroup possibleImmunisationTrigger = new YesNoRadioGroup("possibleImmunisationTrigger", true);
        possibleImmunisationTriggerContainer.add(possibleImmunisationTrigger);
        form.add(possibleImmunisationTriggerContainer);


        WebMarkupContainer partialLipodystrophyContainer = new WebMarkupContainer("partialLipodystrophyContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject().equals(false);
            }
        };

        YesNoRadioGroup partialLipodystrophy = new YesNoRadioGroup("partialLipodystrophy", true);

        partialLipodystrophyContainer.add(partialLipodystrophy);
        form.add(partialLipodystrophyContainer);

        WebMarkupContainer preceedingInfectionContainer = new WebMarkupContainer("preceedingInfectionContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject().equals(false);
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
                showPrecedingInfectioModel.setObject(Boolean.TRUE.equals(form.getModelObject().getPreceedingInfection()));
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });
        form.add(preceedingInfectionContainer);

        // Preceeding infection show/hide
        MarkupContainer preceedingInfectionDetailContainer =
                new WebMarkupContainer("preceedingInfectionDetailContainer") {
                    @Override
                    public boolean isVisible() {
                        return showPrecedingInfectioModel.getObject();
                    }
                };
        preceedingInfectionDetailContainer.add(new TextArea("preceedingInfectionDetail"));
        form.add(preceedingInfectionDetailContainer);
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

        WebMarkupContainer chronicInfectionActiveContainer = new WebMarkupContainer("chronicInfectionActiveContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject().equals(false);
            }
        };

        YesNoRadioGroup chronicInfection = new YesNoRadioGroup("chronicInfection", true);
        chronicInfection.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showChronicModel.setObject(Boolean.TRUE.equals(form.getModelObject().getChronicInfection()));
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }
        });
        chronicInfectionActiveContainer.add(chronicInfection);
        form.add(chronicInfectionActiveContainer);

        // Chronic infection show/hide
        MarkupContainer chronicInfectionDetailContainer = new WebMarkupContainer("chronicInfectionDetailContainer") {
            @Override
            public boolean isVisible() {
                return showChronicModel.getObject();
            }
        };
        chronicInfectionDetailContainer.add(new TextArea("chronicInfectionDetail"));
        componentsToUpdate.add(chronicInfectionDetailContainer);
        chronicInfectionDetailContainer.setOutputMarkupId(true);
        chronicInfectionDetailContainer.setOutputMarkupPlaceholderTag(true);

        form.add(chronicInfectionDetailContainer);

        boolean showOphthalmoscopyDetailsOnInit = form.getModelObject().getOphthalmoscopy() != null ?
                form.getModelObject().getOphthalmoscopy() : false;

        final IModel<Boolean> showOphthalmoscopyDetailsIModel = new Model<Boolean>(showOphthalmoscopyDetailsOnInit);

        YesNoRadioGroup ophthalmoscopy = new YesNoRadioGroup("ophthalmoscopy", true);
        form.add(ophthalmoscopy);
        ophthalmoscopy.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                showOphthalmoscopyDetailsIModel.setObject(Boolean.TRUE.equals(form.getModelObject().getOphthalmoscopy()));
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
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

        ophthalmoscopyDetailContainer.add(new TextArea("ophthalmoscopyDetail"));
        form.add(ophthalmoscopyDetailContainer);

        componentsToUpdate.add(systolicBloodPressureFeedback);
        componentsToUpdate.add(diastolicBloodPressureFeedback);

        // Complications
        WebMarkupContainer complicationsContainer = new WebMarkupContainer("complicationsContainer"){
            @Override
            public boolean isVisible() {
                return !firstVisit;
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

        WebMarkupContainer listedForTransplantContainer = new WebMarkupContainer("listedForTransplantContainer"){
            @Override
            public boolean isVisible() {
                return !firstVisit && isSrnsModel.getObject();
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
        } else if (getParent() instanceof com.solidstategroup.radar.web.panels.followup.ClinicalPicturePanel) {
            return true;
        }
        return false;
    }

    private abstract class ClinicalAjaxSubmitLink extends AjaxSubmitLink {

        public ClinicalAjaxSubmitLink(String id) {
            super(id);
        }

        @Override
        public void onSubmit(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
        }

        @Override
        protected void onError(AjaxRequestTarget target, Form<?> form) {
            target.add(getComponentsToUpdate().toArray(new Component[getComponentsToUpdate().size()]));
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
