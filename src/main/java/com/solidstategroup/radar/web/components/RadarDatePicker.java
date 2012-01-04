package com.solidstategroup.radar.web.components;


import org.apache.wicket.extensions.yui.calendar.DatePicker;

public class RadarDatePicker extends DatePicker{
    public RadarDatePicker() {
        super();
        setShowOnFieldClick(true);
    }

    @Override
            protected boolean enableMonthYearSelection() {
                return true;
            }
}
