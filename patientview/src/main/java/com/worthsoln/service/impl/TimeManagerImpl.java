package com.worthsoln.service.impl;

import com.worthsoln.service.TimeManager;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service(value = "timeManager")
public class TimeManagerImpl implements TimeManager {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}