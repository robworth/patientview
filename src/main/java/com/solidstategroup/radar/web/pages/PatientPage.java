package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.panels.DemographicsPanel;
import com.solidstategroup.radar.web.panels.DiagnosisPanel;
import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.HospitalisationPanel;
import com.solidstategroup.radar.web.panels.PathologyPanel;
import com.solidstategroup.radar.web.panels.RelapsePanel;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_PATIENT})
public class PatientPage extends BasePage {

    private static final String PARAM_ID = "id";
    @SpringBean
    DiagnosisDao diagnosisDao;
    @SpringBean
    DemographicsDao demographicsDao;

    public enum CurrentTab {
        // Used for storing the current tab
        DEMOGRAPHICS, DIAGNOSIS, FIRST_VISIT, FOLLOW_UP, PATHOLOGY, RELAPSE, HOSPITALISATION
    }

    private IModel<Long> radarNumberModel = new Model<Long>();

    private DemographicsPanel demographicsPanel;
    private DiagnosisPanel diagnosisPanel;
    private FirstVisitPanel firstVisitPanel;
    private FollowUpPanel followUpPanel;
    private PathologyPanel pathologyPanel;
    private RelapsePanel relapsePanel;
    private HospitalisationPanel hospitalisationPanel;

    private MarkupContainer linksContainer;

    private CurrentTab currentTab = CurrentTab.DEMOGRAPHICS;

    public PatientPage(PageParameters parameters) {
        super();

        // Get radar number from parameters - we might not have one for new patients
        StringValue idValue = parameters.get(PARAM_ID);
        if (!idValue.isEmpty()) {
            radarNumberModel.setObject(idValue.toLongObject());
        }

        // Construct panels for each of the tabs
        demographicsPanel = new DemographicsPanel("demographicsPanel", radarNumberModel);
        diagnosisPanel = new DiagnosisPanel("diagnosisPanel", radarNumberModel);
        firstVisitPanel = new FirstVisitPanel("firstVisitPanel", radarNumberModel);
        followUpPanel = new FollowUpPanel("followUpPanel", radarNumberModel);
        pathologyPanel = new PathologyPanel("pathologyPanel", radarNumberModel);
        relapsePanel = new RelapsePanel("relapsePanel", radarNumberModel);
        hospitalisationPanel = new HospitalisationPanel("hospitalisationPanel", radarNumberModel);

        // Add them all to the page
        add(demographicsPanel, diagnosisPanel, firstVisitPanel, followUpPanel, pathologyPanel, relapsePanel,
                hospitalisationPanel);

        // Add a container for the links to update the highlighted tab
        linksContainer = new WebMarkupContainer("linksContainer");
        linksContainer.setOutputMarkupId(true);

        // Add the links to switch tab
        linksContainer.add(new TabAjaxLink("demographicsLink", CurrentTab.DEMOGRAPHICS));
        linksContainer.add(new TabAjaxLink("diagnosisLink", CurrentTab.DIAGNOSIS));
        linksContainer.add(new TabAjaxLink("firstVisitLink", CurrentTab.FIRST_VISIT));
        linksContainer.add(new TabAjaxLink("followUpLink", CurrentTab.FOLLOW_UP));
        linksContainer.add(new TabAjaxLink("pathologyLink", CurrentTab.PATHOLOGY));
        linksContainer.add(new TabAjaxLink("relapseLink", CurrentTab.RELAPSE));
        linksContainer.add(new TabAjaxLink("hospitalisationLink", CurrentTab.HOSPITALISATION));
        add(linksContainer);
    }

    public CurrentTab getCurrentTab() {
        return currentTab;
    }

    private class TabAjaxLink extends AjaxLink {
        private CurrentTab tab;

        public TabAjaxLink(String id, CurrentTab tab) {
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
            if (radarNumberModel.getObject() != null) {
                currentTab = tab;
                // Add the links container to update hover class
                target.add(linksContainer);
                target.add(demographicsPanel, diagnosisPanel, firstVisitPanel, followUpPanel, pathologyPanel, relapsePanel,
                        hospitalisationPanel);
            }

        }
    }

    public static PageParameters getParameters(Demographics demographics) {
        return new PageParameters().set(PARAM_ID, demographics.getId());
    }
}
