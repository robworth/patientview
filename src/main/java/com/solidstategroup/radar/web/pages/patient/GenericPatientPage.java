package com.solidstategroup.radar.web.pages.patient;


import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.behaviours.RadarBehaviourFactory;
import com.solidstategroup.radar.web.pages.BasePage;
import com.solidstategroup.radar.web.panels.generic.GenericDemographicsPanel;
import com.solidstategroup.radar.web.panels.generic.MedicalResultsPanel;
import com.solidstategroup.radar.web.visitors.PatientFormVisitor;
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

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class GenericPatientPage extends BasePage {
    private GenericDemographicsPanel genericDemographicsPanel;
    private MedicalResultsPanel medicalResultsPanel;
    private Tab currentTab = Tab.DEMOGRAPHICS;
    private MarkupContainer linksContainer;

    public GenericPatientPage(PageParameters parameters) {
        super();
        genericDemographicsPanel = new GenericDemographicsPanel("demographicsPanel") {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.DEMOGRAPHICS);
            }
        };
        genericDemographicsPanel.setOutputMarkupPlaceholderTag(true);
        medicalResultsPanel = new MedicalResultsPanel("medicalResultsPanel") {
            @Override
            public boolean isVisible() {
                return currentTab.equals(Tab.MEDICAL_RESULTS);
            }
        };
        medicalResultsPanel.setOutputMarkupPlaceholderTag(true);

        add(genericDemographicsPanel, medicalResultsPanel);

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);

        // Add the links to switch tab

        linksContainer.add(new TabAjaxLink("demographicsLink", Tab.DEMOGRAPHICS));
        linksContainer.add(new TabAjaxLink("medicalResultsLink", Tab.MEDICAL_RESULTS));
        add(linksContainer);

        IModel<Integer> pageNumberModel = new Model<Integer>();
        pageNumberModel.setObject(Tab.DEMOGRAPHICS.getPageNumber());

        Label pageNumber = new Label("pageNumber", pageNumberModel);
        pageNumber.setOutputMarkupPlaceholderTag(true);
        add(pageNumber);

        visitChildren(new PatientFormVisitor());
        add(RadarBehaviourFactory.getWarningOnPatientPageExitBehaviour());
    }

    public enum Tab {
        // Used for storing the current tab
        DEMOGRAPHICS(RadarApplication.GENERIC_DEMOGRAPHICS_PAGE_NO),
        MEDICAL_RESULTS(RadarApplication.MEDICAL_RESULTS_PAGE_NO);
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
            GenericPatientPage.this.currentTab = tab;
            // Add the links container to update hover class
            target.add(linksContainer);
            target.add(genericDemographicsPanel, medicalResultsPanel);

            Component pageNumber = getPage().get("pageNumber");
            IModel pageNumberModel = pageNumber.getDefaultModel();
            pageNumberModel.setObject(GenericPatientPage.this.currentTab.getPageNumber());
            target.add(pageNumber);


        }

        @Override
        protected IAjaxCallDecorator getAjaxCallDecorator() {
            return RadarBehaviourFactory.getWarningOnFormExitCallDecorator();
        }
    }
}
