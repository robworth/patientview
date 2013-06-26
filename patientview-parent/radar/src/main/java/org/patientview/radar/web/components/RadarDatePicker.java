package org.patientview.radar.web.components;


import org.apache.wicket.extensions.yui.calendar.DatePicker;

public class RadarDatePicker extends DatePicker{
    public RadarDatePicker() {
        super();
        setShowOnFieldClick(false);
        setAutoHide(true);
        hideOnSelect();
    }

    @Override
            protected boolean enableMonthYearSelection() {
                return true;
            }
}
