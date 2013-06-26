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
