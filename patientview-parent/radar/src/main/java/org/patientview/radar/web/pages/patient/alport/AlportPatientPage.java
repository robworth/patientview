package org.patientview.radar.web.pages.patient.alport;

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
import org.patientview.model.Patient;
import org.patientview.radar.model.user.User;
import org.patientview.radar.web.behaviours.RadarBehaviourFactory;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.panels.GeneticsPanel;
import org.patientview.radar.web.panels.alport.DeafnessPanel;
import org.patientview.radar.web.panels.alport.MedicinePanel;
import org.patientview.radar.web.panels.generic.GenericDemographicsPanel;
import org.patientview.radar.web.panels.generic.MedicalResultsPanel;
import org.patientview.radar.web.visitors.PatientFormVisitor;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class AlportPatientPage extends BasePage {

    public enum Tab {
        // Used for storing the current tab
        DEMOGRAPHICS(1),
        MEDICAL_RESULTS(2),
        GENETICS(3),
        DEAFNESS(4),
        MEDICINE(5);

        private int pageNumber;

        Tab(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageNumber() {
            return pageNumber;
        }
    }

    protected static final String PARAM_ID = "id";


    private Patient patient;
    private MarkupContainer linksContainer;

    // The panels we are using
    private GenericDemographicsPanel genericDemographicsPanel;
    private MedicalResultsPanel medicalResultsPanel;
    private GeneticsPanel geneticsPanel;
    private DeafnessPanel deafnessPanel;
    private MedicinePanel medicinePanel;

    private Tab currentTab = Tab.DEMOGRAPHICS;

    public AlportPatientPage(Patient patient) {
        init(patient);
    }

    public AlportPatientPage() {
        init(new Patient());
    }

    public void init(Patient patient) {
        // init all the panels
        genericDemographicsPanel = new GenericDemographicsPanel("demographicsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.DEMOGRAPHICS);
            }
        };
        add(genericDemographicsPanel);

        medicalResultsPanel = new MedicalResultsPanel("medicalResultsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.MEDICAL_RESULTS);
            }
        };
        add(medicalResultsPanel);

        geneticsPanel = new GeneticsPanel("geneticsPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.GENETICS);
            }
        };
        add(geneticsPanel);

        deafnessPanel = new DeafnessPanel("deafnessPanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.DEAFNESS);
            }
        };
        add(deafnessPanel);

        medicinePanel = new MedicinePanel("medicinePanel", patient) {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.MEDICINE);
            }
        };
        add(medicinePanel);

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);
        add(linksContainer);

        // Add the links to switch tab
        linksContainer.add(new TabAjaxLink("demographicsLink", Tab.DEMOGRAPHICS));
        linksContainer.add(new TabAjaxLink("medicalResultsLink", Tab.MEDICAL_RESULTS));
        linksContainer.add(new TabAjaxLink("geneticsLink", Tab.GENETICS));
        linksContainer.add(new TabAjaxLink("deafnessLink", Tab.DEAFNESS));
        linksContainer.add(new TabAjaxLink("medicineLink", Tab.MEDICINE));

        IModel<Integer> pageNumberModel = new Model<Integer>();
        pageNumberModel.setObject(Tab.DEMOGRAPHICS.getPageNumber());

        Label pageNumber = new Label("pageNumber", pageNumberModel);
        pageNumber.setOutputMarkupPlaceholderTag(true);
        add(pageNumber);

        visitChildren(new PatientFormVisitor());
        add(RadarBehaviourFactory.getWarningOnPatientPageExitBehaviour());
    }

    public static PageParameters getPageParameters(Patient patient) {
        return new PageParameters().set(PARAM_ID, patient.getId());
    }

    public Tab getCurrentTab() {
        return currentTab;
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
                    return currentTab.equals(TabAjaxLink.this.tab) ? "hovered" : "";
                }
            }));

            add(span);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            if (patient != null && patient.hasValidId()) {
                currentTab = tab;
                // Add the links container to update hover class
                target.add(linksContainer);

                // add each panel to the response
                target.add(genericDemographicsPanel, medicalResultsPanel, geneticsPanel, deafnessPanel, medicinePanel);

                Component pageNumber = getPage().get("pageNumber");
                IModel pageNumberModel = pageNumber.getDefaultModel();
                pageNumberModel.setObject(AlportPatientPage.this.currentTab.getPageNumber());
                target.add(pageNumber);
            }
        }

        @Override
        protected IAjaxCallDecorator getAjaxCallDecorator() {
            return RadarBehaviourFactory.getWarningOnFormExitCallDecorator();
        }
    }
}
