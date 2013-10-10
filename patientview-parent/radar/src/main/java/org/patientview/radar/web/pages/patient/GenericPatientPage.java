package org.patientview.radar.web.pages.patient;

import org.patientview.model.Patient;
import org.patientview.radar.model.generic.AddPatientModel;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.generic.MedicalResultManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.panels.NonAlportGeneticsPanel;
import org.patientview.radar.web.panels.alport.MedicinePanel;
import org.patientview.radar.web.panels.generic.GenericDemographicsPanel;
import org.patientview.radar.web.panels.generic.MedicalResultsPanel;
import org.patientview.radar.web.visitors.PatientFormVisitor;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class GenericPatientPage extends BasePage {
    private GenericDemographicsPanel genericDemographicsPanel;
    private MedicalResultsPanel medicalResultsPanel;
    private NonAlportGeneticsPanel geneticsPanel;
    private MedicinePanel medicinePanel;
    private Tab currentTab = Tab.DEMOGRAPHICS;
    private MarkupContainer linksContainer;

    private Patient patient;

    @SpringBean
    private DemographicsManager demographicsManager;

    @SpringBean
    private MedicalResultManager medicalResultManager;

    public GenericPatientPage(AddPatientModel patientModel) {
        // this constructor is used when adding a new patient
        super();

        patient = demographicsManager.getDemographicsByNhsNoAndUnitCode(patientModel.getPatientId(),
                patientModel.getDiseaseGroup().getId());

        if (patient == null) {
            patient = new Patient();
            patient.setDiseaseGroup(patientModel.getDiseaseGroup());
            patient.setRenalUnit(patientModel.getCentre());
            patient.setNhsno(patientModel.getPatientId());
            patient.setNhsNumberType(patientModel.getNhsNumberType());
        }
        // set the nhs id or chi id based on model

        init(patient);
    }

    public GenericPatientPage(PageParameters pageParameters) {
        // this constructor is used when a patient exists
        // get the demographics based on radar id
        StringValue idValue = pageParameters.get("id");
        Long id = idValue.toLong();
        patient = demographicsManager.getDemographicsByRadarNumber(id);

        init(patient);
    }

    public void init(Patient patient) {
        // init all the panels
        genericDemographicsPanel = new GenericDemographicsPanel("demographicsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.DEMOGRAPHICS);
            }
        };

        genericDemographicsPanel.setOutputMarkupPlaceholderTag(true);

        medicalResultsPanel = new MedicalResultsPanel("medicalResultsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.MEDICAL_RESULTS);
            }
        };

        medicalResultsPanel.setOutputMarkupPlaceholderTag(true);

        add(genericDemographicsPanel, medicalResultsPanel);

        geneticsPanel = new NonAlportGeneticsPanel("geneticsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.GENETICS);
            }
        };
        geneticsPanel.setOutputMarkupPlaceholderTag(true);
        add(geneticsPanel);

        medicinePanel = new MedicinePanel("medicinePanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.MEDICINE);
            }
        };
        medicinePanel.setOutputMarkupPlaceholderTag(true);
        add(medicinePanel);

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);

        // Add the links to switch tab

        linksContainer.add(new TabAjaxLink("demographicsLink", Tab.DEMOGRAPHICS));
        linksContainer.add(new TabAjaxLink("medicalResultsLink", Tab.MEDICAL_RESULTS));
        linksContainer.add(new TabAjaxLink("geneticsLink", Tab.GENETICS));
        linksContainer.add(new TabAjaxLink("medicineLink", Tab.MEDICINE));

        add(linksContainer);

        IModel<Integer> pageNumberModel = new Model<Integer>();
        pageNumberModel.setObject(Tab.DEMOGRAPHICS.getPageNumber());

        Label pageNumber = new Label("pageNumber", pageNumberModel);
        pageNumber.setOutputMarkupPlaceholderTag(true);
        add(pageNumber);

        visitChildren(new PatientFormVisitor());
        add(RadarBehaviourFactory.getWarningOnPatientPageExitBehaviour());
    }

    public static PageParameters getPageParameters(Patient patient) {
        PageParameters pageParameters = new PageParameters();
        Long id = patient.getId();
        pageParameters.set("id", id);
        return pageParameters;
    }

    public enum Tab {
        // Used for storing the current tab
        DEMOGRAPHICS(RadarApplication.GENERIC_DEMOGRAPHICS_PAGE_NO),
        MEDICAL_RESULTS(RadarApplication.MEDICAL_RESULTS_PAGE_NO),
        GENETICS(RadarApplication.GENETICE_PAGE_NO),
        MEDICINE(RadarApplication.MEDICINE_PAGE_NO);
        private int pageNumber;

        Tab(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageNumber() {
            return pageNumber;
        }
    }

    private class TabAjaxLink extends AjaxLink {
        private Tab tab;

        public TabAjaxLink(String id, Tab tab) {
            super(id);
            this.tab = tab;

            // Decorate span with class="hovered" if we're active tab
            MarkupContainer span = new WebMarkupContainer("span");
            span.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return GenericPatientPage.this.currentTab.equals(TabAjaxLink.this.tab) ? "hovered" : "";
                }
            }));
            add(span);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            if (patient.hasValidId()) {
                GenericPatientPage.this.currentTab = tab;
                // Add the links container to update hover class
                target.add(linksContainer);
                target.add(genericDemographicsPanel, medicalResultsPanel, geneticsPanel, medicinePanel);

                Component pageNumber = getPage().get("pageNumber");
                IModel pageNumberModel = pageNumber.getDefaultModel();
                pageNumberModel.setObject(GenericPatientPage.this.currentTab.getPageNumber());
                target.add(pageNumber);
            }
        }

        @Override
        protected IAjaxCallDecorator getAjaxCallDecorator() {
            return RadarBehaviourFactory.getWarningOnFormExitCallDecorator();
        }
    }
}
