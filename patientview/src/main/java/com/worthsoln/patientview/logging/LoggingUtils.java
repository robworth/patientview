package com.worthsoln.patientview.logging;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

class LoggingUtils {

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
        endDate.set(Calendar.HOUR_OF_DAY, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);
        endDate.set(Calendar.MILLISECOND, 999);

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
