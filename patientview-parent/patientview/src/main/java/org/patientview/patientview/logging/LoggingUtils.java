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

package org.patientview.patientview.logging;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

final class LoggingUtils {

    private static final int LAST_HOUR = 23;
    private static final int LAST_MINUTE = 59;
    private static final int LAST_SECOND = 59;
    private static final int LAST_MILLISECOND = 999;

    private LoggingUtils() {
    }

    static void defaultDatesInForm(ActionForm form, Calendar startdate, Calendar enddate)
            throws IllegalAccessException, InvocationTargetException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        BeanUtils.setProperty(form, "startdate", dateFormat.format(startdate.getTime()));
        BeanUtils.setProperty(form, "enddate", dateFormat.format(enddate.getTime()));
    }

    static Calendar getDefaultStartDateForLogQuery() {
        return getStartDateForLogQuery(Calendar.MONTH, -1);
    }

    static Calendar getDefaultEndDateForLogQuery() {
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.HOUR_OF_DAY, LAST_HOUR);
        endDate.set(Calendar.MINUTE, LAST_MINUTE);
        endDate.set(Calendar.SECOND, LAST_SECOND);
        endDate.set(Calendar.MILLISECOND, LAST_MILLISECOND);

        return endDate;
    }

    static Calendar getStartDateForLogQuery(int calendarField, int amountToAdd) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        startDate.add(calendarField, amountToAdd);

        return startDate;
    }
}
