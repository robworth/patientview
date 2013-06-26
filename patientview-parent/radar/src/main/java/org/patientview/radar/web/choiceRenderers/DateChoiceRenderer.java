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
