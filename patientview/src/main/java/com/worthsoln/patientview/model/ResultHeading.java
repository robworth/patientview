package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "result_heading")
public class ResultHeading extends BaseModel {

    @Column(nullable = false, unique = true)
    private String headingcode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tenancy_id")
    private Tenancy tenancy;

    @Column(nullable = false)
    private String heading;

    @Column(nullable = false)
    private String rollover;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int panel;

    @Column(nullable = false)
    private int panelorder;

    public ResultHeading() {
    }

    public ResultHeading(String headingcode) {
        this.headingcode = headingcode;
    }

    public ResultHeading(String heading, Tenancy tenancy, String rollover, String headingcode, String link, int panel, int panelorder) {
        this.heading = heading;
        this.tenancy = tenancy;
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

    public Tenancy getTenancy() {
        return tenancy;
    }

    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
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
