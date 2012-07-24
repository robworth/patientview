package com.worthsoln.patientview.model;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SplashPage extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean live;

    @Column(nullable = false)
    private String headline;

    @Column(nullable = false)
    private String bodytext;

    @Column(nullable = false)
    private String unitcode;

    private static String IDENTIFIER = "splashPage";

    public SplashPage() {
    }

    public SplashPage(Long id) {
        this.setId(id);
    }

    public SplashPage(Long id, String name, boolean live, String headline, String bodytext, String unitcode) {
        this.setId(id);
        this.name = name;
        this.live = live;
        this.headline = headline;
        this.bodytext = bodytext;
        this.unitcode = unitcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public static final String getIdentifier() {
        return IDENTIFIER;
    }
}