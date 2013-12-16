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
import org.patientview.radar.model.sequenced.LabData;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.LabDataManager;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.choiceRenderers.DateChoiceRenderer;
import org.patientview.radar.web.models.RadarModelFactory;
import org.patientview.radar.web.panels.FollowUpPanel;
import org.patientview.radar.web.panels.subtabs.LaboratoryResultsPanel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FollowUpLaboratoryResultsPanel extends Panel {
    @SpringBean
    private DiagnosisManager diagnosisManager;
    @SpringBean
    private LabDataManager labDataManager;
    @SpringBean
    private PatientManager patientManager;

    public FollowUpLaboratoryResultsPanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final WebMarkupContainer labResultsContainer = new WebMarkupContainer("labResultsContainer");
        labResultsContainer.setVisible(false);
        labResultsContainer.setOutputMarkupPlaceholderTag(true);
        labResultsContainer.setOutputMarkupId(true);

        // General details
        TextField<Long> radarNumber = new TextField<Long>("radarNumber", radarNumberModel);
        radarNumber.setEnabled(false);
        labResultsContainer.add(radarNumber);

        labResultsContainer.add(new TextField("hospitalNumber", RadarModelFactory.getHospitalNumberModel(
                radarNumberModel, patientManager)));

        labResultsContainer.add(new TextField("diagnosis", new PropertyModel(RadarModelFactory.
                getDiagnosisCodeModel(radarNumberModel, diagnosisManager), "abbreviation")));

        labResultsContainer.add(new TextField("firstName", RadarModelFactory.getFirstNameModel(radarNumberModel,
                patientManager)));
        labResultsContainer.add(new TextField("surname", RadarModelFactory.getSurnameModel(radarNumberModel,
                patientManager)));
        labResultsContainer.add(new DateTextField("dob", RadarModelFactory.getDobModel(radarNumberModel,
                patientManager), RadarApplication.DATE_PATTERN));

        final IModel<LabData> followUpModel = new Model<LabData>(new LabData());

        IModel<List> labResultsListModel = new AbstractReadOnlyModel<List>() {
            @Override
            public List getObject() {

                if (radarNumberModel.getObject() != null) {
                    List list = labDataManager.getLabDataByRadarNumber(radarNumberModel.getObject());
                    return !list.isEmpty() ? list : Collections.emptyList();
                }

                return Collections.emptyList();
            }
        };

        final DropDownChoice<LabData> labResultsSwitcher = new DropDownChoice("labResultsSwitcher", followUpModel,
                labResultsListModel, new DateChoiceRenderer("date", "id"){
            @Override
            protected Date getDate(Object object) {
                return ((LabData) object).getDate();
            }
        });

        final LaboratoryResultsPanel formContainer = new LaboratoryResultsPanel("formContainer", radarNumberModel,
                false, followUpModel, Arrays.<Component>asList(labResultsSwitcher));

        labResultsContainer.add(formContainer);
        add(labResultsContainer);

        labResultsSwitcher.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                labResultsContainer.setVisible(true);
                target.add(labResultsContainer);
            }
        });

        add(labResultsSwitcher);

        AjaxLink addNew = new AjaxLink("addNew") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                followUpModel.setObject(new LabData());
                labResultsContainer.setVisible(true);
                labResultsSwitcher.clearInput();
                target.add(labResultsContainer, labResultsSwitcher);
            }
        };

        add(addNew);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.LABORATORY_RESULTS);

    }
}
