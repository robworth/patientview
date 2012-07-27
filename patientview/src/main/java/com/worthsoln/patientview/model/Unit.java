package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Unit extends BaseModel {

    @Column(nullable = false, unique = true)
    private String unitcode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortname;

    @Column
    private String unituser;
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String address3;
    @Column
    private String postcode;
    @Column
    private String uniturl;
    @Column
    private String trusturl;
    @Column
    private String rpvadminname;
    @Column
    private String rpvadminphone;
    @Column
    private String rpvadminemail;
    @Column
    private String unitenquiriesphone;
    @Column
    private String unitenquiriesemail;
    @Column
    private String appointmentphone;
    @Column
    private String appointmentemail;
    @Column
    private String outofhours;
    @Column
    private String peritonealdialysisphone;
    @Column
    private String peritonealdialysisemail;
    @Column
    private String haemodialysisunitname1;
    @Column
    private String haemodialysisunitphone1;
    @Column
    private String haemodialysisunitlocation1;
    @Column
    private String haemodialysisuniturl1;
    @Column
    private String haemodialysisunitname2;
    @Column
    private String haemodialysisunitphone2;
    @Column
    private String haemodialysisunitlocation2;
    @Column
    private String haemodialysisuniturl2;
    @Column
    private String haemodialysisunitname3;
    @Column
    private String haemodialysisunitphone3;
    @Column
    private String haemodialysisunitlocation3;
    @Column
    private String haemodialysisuniturl3;
    @Column
    private String haemodialysisunitname4;
    @Column
    private String haemodialysisunitphone4;
    @Column
    private String haemodialysisunitlocation4;
    @Column
    private String haemodialysisuniturl4;
    @Column
    private String haemodialysisunitname5;
    @Column
    private String haemodialysisunitphone5;
    @Column
    private String haemodialysisunitlocation5;
    @Column
    private String haemodialysisuniturl5;
    @Column
    private String haemodialysisunitname6;
    @Column
    private String haemodialysisunitphone6;
    @Column
    private String haemodialysisunitlocation6;
    @Column
    private String haemodialysisuniturl6;
    @Column
    private String haemodialysisunitname7;
    @Column
    private String haemodialysisunitphone7;
    @Column
    private String haemodialysisunitlocation7;
    @Column
    private String haemodialysisuniturl7;
    @Column
    private String haemodialysisunitname8;
    @Column
    private String haemodialysisunitphone8;
    @Column
    private String haemodialysisunitlocation8;
    @Column
    private String haemodialysisuniturl8;
    @Column
    private String haemodialysisunitname9;
    @Column
    private String haemodialysisunitphone9;
    @Column
    private String haemodialysisunitlocation9;
    @Column
    private String haemodialysisuniturl9;
    @Column
    private String haemodialysisunitname10;
    @Column
    private String haemodialysisunitphone10;
    @Column
    private String haemodialysisunitlocation10;
    @Column
    private String haemodialysisuniturl10;
    @Column
    private String haemodialysisunitname11;
    @Column
    private String haemodialysisunitphone11;
    @Column
    private String haemodialysisunitlocation11;
    @Column
    private String haemodialysisuniturl11;
    @Column
    private String haemodialysisunitname12;
    @Column
    private String haemodialysisunitphone12;
    @Column
    private String haemodialysisunitlocation12;
    @Column
    private String haemodialysisuniturl12;

    public Unit() {
    }

    public Unit(String unitcode) {
        setUnitcode(unitcode);
    }

    public Unit(String unitcode, String name, String shortname, String address1, String address2, String address3, String postcode,
                String uniturl, String trusturl, String rpvadminname, String rpvadminphone, String rpvadminemail,
                String unitenquiriesphone,
                String unitenquiriesemail, String appointmentphone, String appointmentemail, String outofhours,
                String peritonealdialysisphone, String peritonealdialysisemail, String haemodialysisunitname1,
                String haemodialysisunitphone1, String haemodialysisunitlocation1, String haemodialysisuniturl1,
                String haemodialysisunitname2, String haemodialysisunitphone2, String haemodialysisunitlocation2,
                String haemodialysisuniturl2, String haemodialysisunitname3, String haemodialysisunitphone3,
                String haemodialysisunitlocation3, String haemodialysisuniturl3, String haemodialysisunitname4,
                String haemodialysisunitphone4, String haemodialysisunitlocation4, String haemodialysisuniturl4,
                String haemodialysisunitname5, String haemodialysisunitphone5, String haemodialysisunitlocation5,
                String haemodialysisuniturl5, String haemodialysisunitname6, String haemodialysisunitphone6,
                String haemodialysisunitlocation6, String haemodialysisuniturl6, String haemodialysisunitname7,
                String haemodialysisunitphone7, String haemodialysisunitlocation7, String haemodialysisuniturl7,
                String haemodialysisunitname8, String haemodialysisunitphone8, String haemodialysisunitlocation8,
                String haemodialysisuniturl8,
                String haemodialysisunitname9, String haemodialysisunitphone9, String haemodialysisunitlocation9,
                String haemodialysisuniturl9,
                String haemodialysisunitname10, String haemodialysisunitphone10, String haemodialysisunitlocation10,
                String haemodialysisuniturl10,
                String haemodialysisunitname11, String haemodialysisunitphone11, String haemodialysisunitlocation11,
                String haemodialysisuniturl11,
                String haemodialysisunitname12, String haemodialysisunitphone12, String haemodialysisunitlocation12,
                String haemodialysisuniturl12) {
        setUnitcode(unitcode);
        this.name = name;
        this.shortname = shortname;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postcode = postcode;
        this.uniturl = uniturl;
        this.trusturl = trusturl;
        this.rpvadminname = rpvadminname;
        this.rpvadminphone = rpvadminphone;
        this.rpvadminemail = rpvadminemail;
        this.unitenquiriesphone = unitenquiriesphone;
        this.unitenquiriesemail = unitenquiriesemail;
        this.appointmentphone = appointmentphone;
        this.appointmentemail = appointmentemail;
        this.outofhours = outofhours;
        this.peritonealdialysisphone = peritonealdialysisphone;
        this.peritonealdialysisemail = peritonealdialysisemail;
        this.haemodialysisunitname1 = haemodialysisunitname1;
        this.haemodialysisunitphone1 = haemodialysisunitphone1;
        this.haemodialysisunitlocation1 = haemodialysisunitlocation1;
        this.haemodialysisuniturl1 = haemodialysisuniturl1;
        this.haemodialysisunitname2 = haemodialysisunitname2;
        this.haemodialysisunitphone2 = haemodialysisunitphone2;
        this.haemodialysisunitlocation2 = haemodialysisunitlocation2;
        this.haemodialysisuniturl2 = haemodialysisuniturl2;
        this.haemodialysisunitname3 = haemodialysisunitname3;
        this.haemodialysisunitphone3 = haemodialysisunitphone3;
        this.haemodialysisunitlocation3 = haemodialysisunitlocation3;
        this.haemodialysisuniturl3 = haemodialysisuniturl3;
        this.haemodialysisunitname4 = haemodialysisunitname4;
        this.haemodialysisunitphone4 = haemodialysisunitphone4;
        this.haemodialysisunitlocation4 = haemodialysisunitlocation4;
        this.haemodialysisuniturl4 = haemodialysisuniturl4;
        this.haemodialysisunitname5 = haemodialysisunitname5;
        this.haemodialysisunitphone5 = haemodialysisunitphone5;
        this.haemodialysisunitlocation5 = haemodialysisunitlocation5;
        this.haemodialysisuniturl5 = haemodialysisuniturl5;
        this.haemodialysisunitname6 = haemodialysisunitname6;
        this.haemodialysisunitphone6 = haemodialysisunitphone6;
        this.haemodialysisunitlocation6 = haemodialysisunitlocation6;
        this.haemodialysisuniturl6 = haemodialysisuniturl6;
        this.haemodialysisunitname7 = haemodialysisunitname7;
        this.haemodialysisunitphone7 = haemodialysisunitphone7;
        this.haemodialysisunitlocation7 = haemodialysisunitlocation7;
        this.haemodialysisuniturl7 = haemodialysisuniturl7;
        this.haemodialysisunitname8 = haemodialysisunitname8;
        this.haemodialysisunitphone8 = haemodialysisunitphone8;
        this.haemodialysisunitlocation8 = haemodialysisunitlocation8;
        this.haemodialysisuniturl8 = haemodialysisuniturl8;
        this.haemodialysisunitname9 = haemodialysisunitname9;
        this.haemodialysisunitphone9 = haemodialysisunitphone9;
        this.haemodialysisunitlocation9 = haemodialysisunitlocation9;
        this.haemodialysisuniturl9 = haemodialysisuniturl9;
        this.haemodialysisunitname10 = haemodialysisunitname10;
        this.haemodialysisunitphone10 = haemodialysisunitphone10;
        this.haemodialysisunitlocation10 = haemodialysisunitlocation10;
        this.haemodialysisuniturl10 = haemodialysisuniturl10;
        this.haemodialysisunitname11 = haemodialysisunitname11;
        this.haemodialysisunitphone11 = haemodialysisunitphone11;
        this.haemodialysisunitlocation11 = haemodialysisunitlocation11;
        this.haemodialysisuniturl11 = haemodialysisuniturl11;
        this.haemodialysisunitname12 = haemodialysisunitname12;
        this.haemodialysisunitphone12 = haemodialysisunitphone12;
        this.haemodialysisunitlocation12 = haemodialysisunitlocation12;
        this.haemodialysisuniturl12 = haemodialysisuniturl12;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getUnituser() {
        return unituser;
    }

    public void setUnituser(String unituser) {
        this.unituser = unituser;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAppointmentemail() {
        return appointmentemail;
    }

    public void setAppointmentemail(String appointmentemail) {
        this.appointmentemail = appointmentemail;
    }

    public String getAppointmentphone() {
        return appointmentphone;
    }

    public void setAppointmentphone(String appointmentphone) {
        this.appointmentphone = appointmentphone;
    }

    public String getOutofhours() {
        return outofhours;
    }

    public void setOutofhours(String outofhours) {
        this.outofhours = outofhours;
    }

    public String getPeritonealdialysisemail() {
        return peritonealdialysisemail;
    }

    public void setPeritonealdialysisemail(String peritonealdialysisemail) {
        this.peritonealdialysisemail = peritonealdialysisemail;
    }

    public String getPeritonealdialysisphone() {
        return peritonealdialysisphone;
    }

    public void setPeritonealdialysisphone(String peritonealdialysisphone) {
        this.peritonealdialysisphone = peritonealdialysisphone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRpvadminname() {
        return rpvadminname;
    }

    public void setRpvadminname(String rpvadminname) {
        this.rpvadminname = rpvadminname;
    }

    public String getRpvadminphone() {
        return rpvadminphone;
    }

    public void setRpvadminphone(String rpvadminphone) {
        this.rpvadminphone = rpvadminphone;
    }

    public String getRpvadminemail() {
        return rpvadminemail;
    }

    public void setRpvadminemail(String rpvadminemail) {
        this.rpvadminemail = rpvadminemail;
    }

    public String getTrusturl() {
        return trusturl;
    }

    public void setTrusturl(String trusturl) {
        this.trusturl = trusturl;
    }

    public String getUnitenquiriesemail() {
        return unitenquiriesemail;
    }

    public void setUnitenquiriesemail(String unitenquiriesemail) {
        this.unitenquiriesemail = unitenquiriesemail;
    }

    public String getUnitenquiriesphone() {
        return unitenquiriesphone;
    }

    public void setUnitenquiriesphone(String unitenquiriesphone) {
        this.unitenquiriesphone = unitenquiriesphone;
    }

    public String getUniturl() {
        return uniturl;
    }

    public void setUniturl(String uniturl) {
        this.uniturl = uniturl;
    }

    public String getHaemodialysisunitlocation1() {
        return haemodialysisunitlocation1;
    }

    public void setHaemodialysisunitlocation1(String haemodialysisunitlocation1) {
        this.haemodialysisunitlocation1 = haemodialysisunitlocation1;
    }

    public String getHaemodialysisunitlocation2() {
        return haemodialysisunitlocation2;
    }

    public void setHaemodialysisunitlocation2(String haemodialysisunitlocation2) {
        this.haemodialysisunitlocation2 = haemodialysisunitlocation2;
    }

    public String getHaemodialysisunitlocation3() {
        return haemodialysisunitlocation3;
    }

    public void setHaemodialysisunitlocation3(String haemodialysisunitlocation3) {
        this.haemodialysisunitlocation3 = haemodialysisunitlocation3;
    }

    public String getHaemodialysisunitlocation4() {
        return haemodialysisunitlocation4;
    }

    public void setHaemodialysisunitlocation4(String haemodialysisunitlocation4) {
        this.haemodialysisunitlocation4 = haemodialysisunitlocation4;
    }

    public String getHaemodialysisunitlocation5() {
        return haemodialysisunitlocation5;
    }

    public void setHaemodialysisunitlocation5(String haemodialysisunitlocation5) {
        this.haemodialysisunitlocation5 = haemodialysisunitlocation5;
    }

    public String getHaemodialysisunitlocation6() {
        return haemodialysisunitlocation6;
    }

    public void setHaemodialysisunitlocation6(String haemodialysisunitlocation6) {
        this.haemodialysisunitlocation6 = haemodialysisunitlocation6;
    }

    public String getHaemodialysisunitlocation7() {
        return haemodialysisunitlocation7;
    }

    public void setHaemodialysisunitlocation7(String haemodialysisunitlocation7) {
        this.haemodialysisunitlocation7 = haemodialysisunitlocation7;
    }

    public String getHaemodialysisunitlocation8() {
        return haemodialysisunitlocation8;
    }

    public void setHaemodialysisunitlocation8(String haemodialysisunitlocation8) {
        this.haemodialysisunitlocation8 = haemodialysisunitlocation8;
    }

    public String getHaemodialysisunitname1() {
        return haemodialysisunitname1;
    }

    public void setHaemodialysisunitname1(String haemodialysisunitname1) {
        this.haemodialysisunitname1 = haemodialysisunitname1;
    }

    public String getHaemodialysisunitname2() {
        return haemodialysisunitname2;
    }

    public void setHaemodialysisunitname2(String haemodialysisunitname2) {
        this.haemodialysisunitname2 = haemodialysisunitname2;
    }

    public String getHaemodialysisunitname3() {
        return haemodialysisunitname3;
    }

    public void setHaemodialysisunitname3(String haemodialysisunitname3) {
        this.haemodialysisunitname3 = haemodialysisunitname3;
    }

    public String getHaemodialysisunitname4() {
        return haemodialysisunitname4;
    }

    public void setHaemodialysisunitname4(String haemodialysisunitname4) {
        this.haemodialysisunitname4 = haemodialysisunitname4;
    }

    public String getHaemodialysisunitname5() {
        return haemodialysisunitname5;
    }

    public void setHaemodialysisunitname5(String haemodialysisunitname5) {
        this.haemodialysisunitname5 = haemodialysisunitname5;
    }

    public String getHaemodialysisunitname6() {
        return haemodialysisunitname6;
    }

    public void setHaemodialysisunitname6(String haemodialysisunitname6) {
        this.haemodialysisunitname6 = haemodialysisunitname6;
    }

    public String getHaemodialysisunitname7() {
        return haemodialysisunitname7;
    }

    public void setHaemodialysisunitname7(String haemodialysisunitname7) {
        this.haemodialysisunitname7 = haemodialysisunitname7;
    }

    public String getHaemodialysisunitname8() {
        return haemodialysisunitname8;
    }

    public void setHaemodialysisunitname8(String haemodialysisunitname8) {
        this.haemodialysisunitname8 = haemodialysisunitname8;
    }

    public String getHaemodialysisunitphone1() {
        return haemodialysisunitphone1;
    }

    public void setHaemodialysisunitphone1(String haemodialysisunitphone1) {
        this.haemodialysisunitphone1 = haemodialysisunitphone1;
    }

    public String getHaemodialysisunitphone2() {
        return haemodialysisunitphone2;
    }

    public void setHaemodialysisunitphone2(String haemodialysisunitphone2) {
        this.haemodialysisunitphone2 = haemodialysisunitphone2;
    }

    public String getHaemodialysisunitphone3() {
        return haemodialysisunitphone3;
    }

    public void setHaemodialysisunitphone3(String haemodialysisunitphone3) {
        this.haemodialysisunitphone3 = haemodialysisunitphone3;
    }

    public String getHaemodialysisunitphone4() {
        return haemodialysisunitphone4;
    }

    public void setHaemodialysisunitphone4(String haemodialysisunitphone4) {
        this.haemodialysisunitphone4 = haemodialysisunitphone4;
    }

    public String getHaemodialysisunitphone5() {
        return haemodialysisunitphone5;
    }

    public void setHaemodialysisunitphone5(String haemodialysisunitphone5) {
        this.haemodialysisunitphone5 = haemodialysisunitphone5;
    }

    public String getHaemodialysisunitphone6() {
        return haemodialysisunitphone6;
    }

    public void setHaemodialysisunitphone6(String haemodialysisunitphone6) {
        this.haemodialysisunitphone6 = haemodialysisunitphone6;
    }

    public String getHaemodialysisunitphone7() {
        return haemodialysisunitphone7;
    }

    public void setHaemodialysisunitphone7(String haemodialysisunitphone7) {
        this.haemodialysisunitphone7 = haemodialysisunitphone7;
    }

    public String getHaemodialysisunitphone8() {
        return haemodialysisunitphone8;
    }

    public void setHaemodialysisunitphone8(String haemodialysisunitphone8) {
        this.haemodialysisunitphone8 = haemodialysisunitphone8;
    }

    public String getHaemodialysisuniturl1() {
        return haemodialysisuniturl1;
    }

    public void setHaemodialysisuniturl1(String haemodialysisuniturl1) {
        this.haemodialysisuniturl1 = haemodialysisuniturl1;
    }

    public String getHaemodialysisuniturl2() {
        return haemodialysisuniturl2;
    }

    public void setHaemodialysisuniturl2(String haemodialysisuniturl2) {
        this.haemodialysisuniturl2 = haemodialysisuniturl2;
    }

    public String getHaemodialysisuniturl3() {
        return haemodialysisuniturl3;
    }

    public void setHaemodialysisuniturl3(String haemodialysisuniturl3) {
        this.haemodialysisuniturl3 = haemodialysisuniturl3;
    }

    public String getHaemodialysisuniturl4() {
        return haemodialysisuniturl4;
    }

    public void setHaemodialysisuniturl4(String haemodialysisuniturl4) {
        this.haemodialysisuniturl4 = haemodialysisuniturl4;
    }

    public String getHaemodialysisuniturl5() {
        return haemodialysisuniturl5;
    }

    public void setHaemodialysisuniturl5(String haemodialysisuniturl5) {
        this.haemodialysisuniturl5 = haemodialysisuniturl5;
    }

    public String getHaemodialysisuniturl6() {
        return haemodialysisuniturl6;
    }

    public void setHaemodialysisuniturl6(String haemodialysisuniturl6) {
        this.haemodialysisuniturl6 = haemodialysisuniturl6;
    }

    public String getHaemodialysisuniturl7() {
        return haemodialysisuniturl7;
    }

    public void setHaemodialysisuniturl7(String haemodialysisuniturl7) {
        this.haemodialysisuniturl7 = haemodialysisuniturl7;
    }

    public String getHaemodialysisuniturl8() {
        return haemodialysisuniturl8;
    }

    public void setHaemodialysisuniturl8(String haemodialysisuniturl8) {
        this.haemodialysisuniturl8 = haemodialysisuniturl8;
    }

    public String getHaemodialysisunitname9() {
        return haemodialysisunitname9;
    }

    public void setHaemodialysisunitname9(String haemodialysisunitname9) {
        this.haemodialysisunitname9 = haemodialysisunitname9;
    }

    public String getHaemodialysisunitphone9() {
        return haemodialysisunitphone9;
    }

    public void setHaemodialysisunitphone9(String haemodialysisunitphone9) {
        this.haemodialysisunitphone9 = haemodialysisunitphone9;
    }

    public String getHaemodialysisunitlocation9() {
        return haemodialysisunitlocation9;
    }

    public void setHaemodialysisunitlocation9(String haemodialysisunitlocation9) {
        this.haemodialysisunitlocation9 = haemodialysisunitlocation9;
    }

    public String getHaemodialysisuniturl9() {
        return haemodialysisuniturl9;
    }

    public void setHaemodialysisuniturl9(String haemodialysisuniturl9) {
        this.haemodialysisuniturl9 = haemodialysisuniturl9;
    }

    public String getHaemodialysisunitname10() {
        return haemodialysisunitname10;
    }

    public void setHaemodialysisunitname10(String haemodialysisunitname10) {
        this.haemodialysisunitname10 = haemodialysisunitname10;
    }

    public String getHaemodialysisunitphone10() {
        return haemodialysisunitphone10;
    }

    public void setHaemodialysisunitphone10(String haemodialysisunitphone10) {
        this.haemodialysisunitphone10 = haemodialysisunitphone10;
    }

    public String getHaemodialysisunitlocation10() {
        return haemodialysisunitlocation10;
    }

    public void setHaemodialysisunitlocation10(String haemodialysisunitlocation10) {
        this.haemodialysisunitlocation10 = haemodialysisunitlocation10;
    }

    public String getHaemodialysisuniturl10() {
        return haemodialysisuniturl10;
    }

    public void setHaemodialysisuniturl10(String haemodialysisuniturl10) {
        this.haemodialysisuniturl10 = haemodialysisuniturl10;
    }

    public String getHaemodialysisunitname11() {
        return haemodialysisunitname11;
    }

    public void setHaemodialysisunitname11(String haemodialysisunitname11) {
        this.haemodialysisunitname11 = haemodialysisunitname11;
    }

    public String getHaemodialysisunitphone11() {
        return haemodialysisunitphone11;
    }

    public void setHaemodialysisunitphone11(String haemodialysisunitphone11) {
        this.haemodialysisunitphone11 = haemodialysisunitphone11;
    }

    public String getHaemodialysisunitlocation11() {
        return haemodialysisunitlocation11;
    }

    public void setHaemodialysisunitlocation11(String haemodialysisunitlocation11) {
        this.haemodialysisunitlocation11 = haemodialysisunitlocation11;
    }

    public String getHaemodialysisuniturl11() {
        return haemodialysisuniturl11;
    }

    public void setHaemodialysisuniturl11(String haemodialysisuniturl11) {
        this.haemodialysisuniturl11 = haemodialysisuniturl11;
    }

    public String getHaemodialysisunitname12() {
        return haemodialysisunitname12;
    }

    public void setHaemodialysisunitname12(String haemodialysisunitname12) {
        this.haemodialysisunitname12 = haemodialysisunitname12;
    }

    public String getHaemodialysisunitphone12() {
        return haemodialysisunitphone12;
    }

    public void setHaemodialysisunitphone12(String haemodialysisunitphone12) {
        this.haemodialysisunitphone12 = haemodialysisunitphone12;
    }

    public String getHaemodialysisunitlocation12() {
        return haemodialysisunitlocation12;
    }

    public void setHaemodialysisunitlocation12(String haemodialysisunitlocation12) {
        this.haemodialysisunitlocation12 = haemodialysisunitlocation12;
    }

    public String getHaemodialysisuniturl12() {
        return haemodialysisuniturl12;
    }

    public void setHaemodialysisuniturl12(String haemodialysisuniturl12) {
        this.haemodialysisuniturl12 = haemodialysisuniturl12;
    }

    public String getUnitNamePlusCode() {
        return name + " - " + unitcode;
    }
}
