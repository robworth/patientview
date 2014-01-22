/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.web.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jfree.chart.JFreeChart;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.components.JFreeChartImage;

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

       // final Label countLabel = new Label("count", new Model<Integer>(patientCount));
        //countLabel.setOutputMarkupPlaceholderTag(true);
        //add(countLabel);

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
                //target.add(countLabel);
            }
        });

    }

}
