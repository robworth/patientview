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

package org.patientview.radar.web.choiceRenderers;

import org.patientview.radar.web.RadarApplication;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used by dropdowns to render date with the correct format
 */
public abstract class DateChoiceRenderer extends ChoiceRenderer {
    public DateChoiceRenderer(String displayExpression, String idExpression) {
        super(displayExpression, idExpression);
    }

    @Override
    public Object getDisplayValue(Object object) {
        Date date = getDate(object);
        return date != null ? new SimpleDateFormat(RadarApplication.DATE_PATTERN).format(date) :
                "Date not set";
    }

    protected abstract Date getDate(Object object);
}
