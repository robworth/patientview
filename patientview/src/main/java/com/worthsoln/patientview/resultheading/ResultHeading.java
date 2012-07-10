package com.worthsoln.patientview.resultheading;

public class ResultHeading {

    private String headingcode;
    private String heading;
    private String rollover;
    private String link;
    private int panel;
    private int panelorder;

    public ResultHeading() {
    }

    public ResultHeading(String headingcode) {
        this.headingcode = headingcode;
    }

    public ResultHeading(String heading, String rollover, String headingcode, String link, int panel, int panelorder) {
        this.heading = heading;
        this.rollover = rollover;
        this.headingcode = headingcode;
        this.link = link;
        this.panel = panel;
        this.panelorder = panelorder;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getRollover() {
        return rollover;
    }

    public void setRollover(String rollover) {
        this.rollover = rollover;
    }

    public String getHeadingcode() {
        return headingcode;
    }

    public void setHeadingcode(String headingcode) {
        this.headingcode = headingcode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPanel() {
        return panel;
    }

    public void setPanel(int panel) {
        this.panel = panel;
    }

    public int getPanelorder() {
        return panelorder;
    }

    public void setPanelorder(int panelorder) {
        this.panelorder = panelorder;
    }
}
