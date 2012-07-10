package com.worthsoln.patientview.splashpage;

public class SplashPage {

    private int id;
    private String name;
    private boolean live;
    private String headline;
    private String bodytext;
    private static String IDENTIFIER = "splashPage";


    public SplashPage() {
    }

    public SplashPage(int id) {
        this.id = id;
    }

    public SplashPage(int id, String name, boolean live, String headline, String bodytext) {
        this.id = id;
        this.name = name;
        this.live = live;
        this.headline = headline;
        this.bodytext = bodytext;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static final String getIdentifier() {
        return IDENTIFIER;
    }
}
