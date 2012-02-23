package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.service.UtilityManager;
import com.solidstategroup.radar.web.RadarSecuredSession;
import com.solidstategroup.radar.web.components.JFreeChartImage;
import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

import org.apache.wicket.markup.html.basic.Label;
import sun.font.FontFamily;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;

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

        IModel<Integer> patientCountModel = new LoadableDetachableModel<Integer>() {
            @Override
            protected Integer load() {
                int count = 0;
                if (session.isSignedIn()) {
                    if (session.getRoles().hasRole(User.ROLE_PROFESSIONAL) || session.getRoles().hasRole(User.ROLE_SUPER_USER)) {
                        ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();
                        count = utilityManager.getPatientCountByUnit(user.getCentre());
                    }
                }
                return count;
            }
        };

        final Label countLabel = new Label("count", patientCountModel);
        countLabel.setOutputMarkupPlaceholderTag(true);
        add(countLabel);

        String renalUnit = "";
        if (session.isSignedIn()) {
            if (session.getRoles().hasRole(User.ROLE_PROFESSIONAL) || session.getRoles().hasRole(User.ROLE_SUPER_USER)) {
                ProfessionalUser user = (ProfessionalUser) RadarSecuredSession.get().getUser();
                renalUnit = user.getCentre() != null ? user.getCentre().getName() : "";
            }
        }

        add(new Label ("renalUnit", renalUnit));

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
