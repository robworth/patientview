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

package org.patientview.radar.web.panels.followup;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.patientview.radar.model.sequenced.Therapy;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.service.TherapyManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.FollowUpPanel;
import org.patientview.radar.web.panels.subtabs.TreatmentPanel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FollowUpTreatmentPanel extends Panel {
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private TherapyManager therapyManager;
    @SpringBean
    private PatientManager patientManager;

    public FollowUpTreatmentPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        // General details
        final WebMarkupContainer treatmentContainer = new WebMarkupContainer("treatmentContainer");
        treatmentContainer.setVisible(false);
        treatmentContainer.setOutputMarkupPlaceholderTag(true);
        treatmentContainer.setOutputMarkupId(true);
        add(treatmentContainer);

        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        treatmentContainer.add(radarNumber);

        treatmentContainer.add(new TextField("hospitalNumber", RadarModelFactory.
                getHospitalNumberModel(radarNumberModel, patientManager)));

        treatmentContainer.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.
                getDiagnosisCodeModel(radarNumberModel, diagnosisManager), "abbreviation")));

        treatmentContainer.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                patientManager)));
        treatmentContainer.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel,
                patientManager)));
        treatmentContainer.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel,
                patientManager), RadarApplication.DATE_PATTERN));


        final IModel<Therapy> followUpModel = new Model<Therapy>(new Therapy());

        IModel<List> therapiesListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = therapyManager.getTherapyByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice<Therapy> treatmentSwitcher = new DropDownChoice("treatmentSwitcher", followUpModel,
                therapiesListModel, new DateChoiceRenderer("treatmentRecordDate", "id") {

            @Override
            protected Date getDate(Object object) {
                return ((Therapy) object).getTreatmentRecordDate();
            }
        });
        add(treatmentSwitcher);

        final TreatmentPanel treatmentPanel = new TreatmentPanel("treatmentPanel", radarNumberModel, false,
                followUpModel,
                Arrays.<Component>asList(treatmentSwitcher));
        treatmentPanel.setVisible(false);
        add(treatmentPanel);
        treatmentPanel.setOutputMarkupId(true);
        treatmentPanel.setOutputMarkupPlaceholderTag(true);

        treatmentSwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                treatmentContainer.setVisible(true);
                treatmentPanel.setVisible(true);
                target.add(treatmentContainer);
                target.add(treatmentPanel);
            }
        });

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                followUpModel.setObject(new Therapy());
                treatmentContainer.setVisible(true);
                treatmentPanel.setVisible(true);
                treatmentSwitcher.clearInput();
                target.add(treatmentContainer);
                target.add(treatmentPanel);
                target.add(treatmentSwitcher);
            }
        };

        add(addNew);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.TREATMENT);
    }
}
