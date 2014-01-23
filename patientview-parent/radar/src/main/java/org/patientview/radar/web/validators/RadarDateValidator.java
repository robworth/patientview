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

package org.patientview.radar.web.validators;


import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

import java.util.Calendar;
import java.util.Date;

/**
 * Checks that the year input for dates cannot have less than for digits e.g. 01-01-01 is not valid whilst 01-01-2001
 * is valid.
 */
public class RadarDateValidator extends AbstractValidator{
    @Override
    protected void onValidate(IValidatable iValidatable) {
        // cannot access raw input - can only access the date once converted
        Date date = (Date) iValidatable.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        // if the year input has 3 digits or less then 999 is the max value - not the best way but
        // could not find another way
        if (year < 1000) {
            error(iValidatable);
        }
    }
}
