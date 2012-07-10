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
        Calendar startdate = Calendar.getInstance();
        startdate.set(Calendar.HOUR_OF_DAY, 23);
        startdate.set(Calendar.MINUTE, 59);
        return startdate;
    }

    static Calendar getStartDateForLogQuery(int calendarField, int amountToAdd) {
        Calendar startdate = Calendar.getInstance();
        startdate.set(Calendar.HOUR_OF_DAY, 0);
        startdate.set(Calendar.MINUTE, 0);
        startdate.add(calendarField, amountToAdd);
        return startdate;
    }
}
