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

package org.patientview.radar.web.visitors;


import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.validators.RadarDateValidator;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

public class PatientFormVisitor implements IVisitor<Component, Object> {
    public void component(Component component, IVisit iVisit) {
        //add onkeyup event to date to santise input - tried attaching behaviour in the component class itself
        // but did not work
        if (component instanceof RadarDateTextField || component instanceof RadarRequiredDateTextField) {
            component.add(new AttributeModifier("onkeyup", "radarUtility.sanitiseDateInput(this);"));
        }

        // add validator to date components - adding it inside the component constructor does not work
        if (component instanceof RadarDateTextField || component instanceof RadarRequiredDateTextField) {
            component.add(new RadarDateValidator());
        }

        //if form component - mark form as dirty onchange
        if (component instanceof FormComponent || component instanceof Radio) {
            // ignore the subform components
            String[] ignoreParents = {"immunosuppression", "plasmapheresispanel", "dialysiscontainer",
                    "transplantscontainer", "rejectDataContainer", "editTransplantContainer", "addTransplantForm"};

            // ignore record switchers
            String[] ignoreIds = {"switcher"};

            boolean ignoreComponent = false;
            for (String ignore : ignoreParents) {
                if (component.getPath().toLowerCase().contains(ignore.toLowerCase())) {
                    ignoreComponent = true;
                    break;
                }
            }
            for (String ignore : ignoreIds) {
                if (component.getId().toLowerCase().contains(ignore.toLowerCase())) {
                    ignoreComponent = true;
                    break;
                }
            }
            if (!ignoreComponent) {
                component.add(new AttributeAppender("onchange", RadarApplication.FORM_IS_DIRTY_TRUE_SCRIPT));
            }
        }

    }
}
