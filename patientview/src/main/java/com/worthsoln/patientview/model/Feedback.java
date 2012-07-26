package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@Entity
public class Feedback extends BaseModel {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true)
    private String commentedited;

    @Column(nullable = false)
    private boolean anonymous;

    @Column(nullable = false)
    private boolean makepublic;

    public Feedback() {
    }

    public Feedback(Long id) {
        this.setId(id);
    }

    public Feedback(String username, String name, String nhsno, String unitcode, String comment, boolean anonymous) {
        setUsername(username);
        setName(name);
        setNhsno(nhsno);
        setUnitcode(unitcode);
        setComment(comment);
        setCommentedited(comment);
        setAnonymous(anonymous);
        this.datestamp = Calendar.getInstance();
        setMakepublic(false);
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
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
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
        if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamp.getTime());
        } else {
            return dateTimeFormat.format(datestamp.getTime());
        }
    }
}
