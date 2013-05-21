package com.worthsoln.patientview.model.radar;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 */
@Entity(name = "tbl_users")
public class RadarProfessional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uID;

    @Column
    private String uSurname;

    @Column
    private String uForename;

    @Column
    private String uTitle;

    @Column
    private String uGMC;

    @Column
    private String uRole;

    @Column
    private String uPhone;

    @Column
    private Long uCentre;

    @Column
    private Date uDateJoin;

    public Long getuID() {
        return uID;
    }

    public void setuID(Long uID) {
        this.uID = uID;
    }

    public String getuSurname() {
        return uSurname;
    }

    public void setuSurname(String uSurname) {
        this.uSurname = uSurname;
    }

    public String getuForename() {
        return uForename;
    }

    public void setuForename(String uForename) {
        this.uForename = uForename;
    }

    public String getuTitle() {
        return uTitle;
    }

    public void setuTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public String getuGMC() {
        return uGMC;
    }

    public void setuGMC(String uGMC) {
        this.uGMC = uGMC;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Long getuCentre() {
        return uCentre;
    }

    public void setuCentre(Long uCentre) {
        this.uCentre = uCentre;
    }

    public Date getuDateJoin() {
        return uDateJoin;
    }

    public void setuDateJoin(Date uDateJoin) {
        this.uDateJoin = uDateJoin;
    }
}