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

package org.patientview.radar.web.panels.firstvisit;

import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class YesNoRadioGroupPanel extends Panel {

    public YesNoRadioGroupPanel(String id, boolean addLabels, CompoundPropertyModel model, String property){
        this(id, addLabels, model, property, null);

    }

    public YesNoRadioGroupPanel(String id, boolean addLabels, CompoundPropertyModel model, String property,
                                AjaxFormChoiceComponentUpdatingBehavior toggleBehaviour) {
        super(id);

        RadioGroup<Boolean> radioGroup = new RadioGroup<Boolean>("group", model.bind(property));
        if (toggleBehaviour != null) {
           radioGroup.add(toggleBehaviour);
        }
        add(radioGroup);

        // Yes, No
        Radio<Boolean> yes = new Radio<Boolean>("yes", new Model<Boolean>(Boolean.TRUE));
        Radio<Boolean> no = new Radio<Boolean>("no", new Model<Boolean>(Boolean.FALSE));
        radioGroup.add(yes, no);

        // If we're using labels add them too
        if (addLabels) {
            radioGroup.add(new FormComponentLabel("yesLabel", yes));
            radioGroup.add(new FormComponentLabel("noLabel", no));
        }
    }

}
