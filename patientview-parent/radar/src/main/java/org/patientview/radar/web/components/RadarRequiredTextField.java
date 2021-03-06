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

package org.patientview.radar.web.components;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;

import java.util.List;

public class RadarRequiredTextField extends TextField {
    public RadarRequiredTextField(String id, WebMarkupContainer parent, List<Component> componentsToUpdateList) {
        super(id);
        init(parent, componentsToUpdateList);
    }

    private void init(WebMarkupContainer parent, List<Component> componentsToUpdateList) {
        setRequired(true);
        RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                new RadarFormComponentFeedbackIndicator(getId() + "FeedbackIndicator", this);
        parent.add(radarFormComponentFeedbackIndicator);
        radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
        radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
        componentsToUpdateList.add(radarFormComponentFeedbackIndicator);
    }
}
