package org.patientview.radar.web.pages;

import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.JFreeChartImage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jfree.chart.JFreeChart;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class ProfessionalsPage extends BasePage {

    @SpringBean
    private UtilityManager utilityManager;

    public ProfessionalsPage() {

        JFreeChart chart = utilityManager.getPatientCountPerUnitChart();
        final JFreeChartImage graph = new JFreeChartImage("graph", chart, 700, 400);

        graph.setVisible(false);
        graph.setOutputMarkupId(true);
        graph.setOutputMarkupPlaceholderTag(true);
        add(graph);

        final AuthenticatedWebSession session = RadarSecuredSession.get();
        int patientCount = 0;
        if (session.isSignedIn()) {
            if (session.getRoles().hasRole(User.ROLE_PROFESSIONAL) || session.getRoles().
                    hasRole(User.ROLE_SUPER_USER)) {
                ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();
                patientCount = utilityManager.getPatientCountByUnit(user.getCentre());
            }
        }

        final Label countLabel = new Label("count", new Model<Integer>(patientCount));
        countLabel.setOutputMarkupPlaceholderTag(true);
        add(countLabel);

        String renalUnit = "";
        if (session.isSignedIn()) {
            if (session.getRoles().hasRole(User.ROLE_PROFESSIONAL) || session.getRoles()
                    .hasRole(User.ROLE_SUPER_USER)) {
                ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();
                renalUnit = user.getCentre() != null ? user.getCentre().getName() : "";
            }
        }

        add(new Label("renalUnit", renalUnit));

        add(new AjaxLink("showGraph") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                graph.setVisible(true);
                target.add(graph);
                target.add(countLabel);
            }
        });

    }
}
