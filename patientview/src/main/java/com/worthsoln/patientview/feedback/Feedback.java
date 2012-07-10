package com.worthsoln.patientview.feedback;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Feedback {


    private int id;
    private String username;
    private String name;
    private String nhsno;
    private String unitcode;
    private Calendar datestamped;
    private String comment;
    private String commentedited;
    private boolean anonymous;
    private boolean makepublic;

    public Feedback() {
    }

    public Feedback(int id) {
        this.id = id;
    }

    public Feedback(String username, String name, String nhsno, String unitcode, String comment, boolean anonymous) {
        setUsername(username);
        setName(name);
        setNhsno(nhsno);
        setUnitcode(unitcode);
        setComment(comment);
        setCommentedited(comment);
        setAnonymous(anonymous);
        this.datestamped = Calendar.getInstance();
        setMakepublic(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public Calendar getDatestamp() {
        return datestamped;
    }

    public void setDatestamp(Calendar datestamped) {
        this.datestamped = datestamped;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentedited() {
        return commentedited;
    }

    public void setCommentedited(String commentedited) {
        this.commentedited = commentedited;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean isMakepublic() {
        return makepublic;
    }

    public void setMakepublic(boolean makepublic) {
        this.makepublic = makepublic;
    }

    public String getFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((datestamped.get(Calendar.HOUR_OF_DAY) == 0) && (datestamped.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamped.getTime());
        } else {
            return dateTimeFormat.format(datestamped.getTime());
        }
    }
}
