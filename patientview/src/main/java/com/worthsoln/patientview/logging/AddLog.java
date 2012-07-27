package com.worthsoln.patientview.logging;

import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.utils.LegacySpringUtils;

public class AddLog {

    public static final String PASSWORD_RESET_FORGOTTEN = "password reset forgotten";
    public static final String PASSWORD_RESET = "password reset";
    public static final String PASSWORD_CHANGE = "password change";
    public static final String PASSWORD_LOCKED = "password locked";
    public static final String PASSWORD_UNLOCKED = "password unlocked";
    public static final String ACTOR_SYSTEM = "system";
    public static final String PATIENT_DATA_FOLLOWUP = "patient data load";
    public static final String PATIENT_DATA_FAIL = "patient data fail";
    public static final String PATIENT_DATA_REMOVE = "patient data remove";
    public static final String LOGGED_ON = "logon";
    public static final String PATIENT_ADD = "patient add";
    public static final String PATIENT_DELETE = "patient delete";
    public static final String PATIENT_VIEW = "patient view";
    public static final String ADMIN_ADD = "admin add";
    public static final String UKT_DATA_REPLACE = "ukt data";
    public static final String PATIENT_COUNT = "patient count";
    public static final String EMAIL_VERIFY = "email verified";

    public static void addLog(String actor, String action, String user, String nhsno, String unitcode,
                              String extrainfo) {
        actor = (actor == null) ? "" : actor;
        action = (action == null) ? "" : action;
        user = (user == null) ? "" : user;
        nhsno = (nhsno == null) ? "" : nhsno;
        unitcode = (unitcode == null) ? "" : unitcode;
        extrainfo = (extrainfo == null) ? "" : extrainfo;

        LogEntry entry = new LogEntry(nhsno, user, action, actor, unitcode, extrainfo);
        try {
            LegacySpringUtils.getLogEntryManager().save(entry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
