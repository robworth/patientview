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

package org.patientview.radar.web.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.patientview.radar.model.Relative;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagnosisRelativePanel extends Panel {

    @SpringBean
    private UtilityManager utilityManager;
    private IModel<Boolean> isVisibleModel;
    private static Map<Integer, String> map;

    static {
        map = new HashMap<Integer, String>();
        map.put(1, "1st");
        map.put(2, "2nd");
        map.put(3, "3rd");
        map.put(4, "4th");
        map.put(5, "5th");
        map.put(6, "6th");

    }
    public DiagnosisRelativePanel(String id, int i, CompoundPropertyModel model, IModel<Boolean> isVisibleModel,
                                  List<Component> componentsToUpdate) {
        super(id);
        this.isVisibleModel = isVisibleModel;
        add(new Label("relativeLabel", map.get(i) + " Relative with same disease"));
        // Drop down with choices and choice renderer setup
        add(new DropDownChoice<Relative>("relative", model.bind("relativeWithDisease" + i),
                utilityManager.getRelatives(), new ChoiceRenderer<Relative>("name", "id")));

        // Add the radar number text field too
        add(new TextField<Integer>("radarNumber", model.bind("relativeWithDiseaseRadarNumber" + i)));
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        componentsToUpdate.add(this);
    }

    @Override
    public boolean isVisible() {
        return isVisibleModel.getObject();
    }
}
