package com.solidstategroup.radar.model;

import com.solidstategroup.radar.model.enums.RemissionAchieved;

import java.util.Date;

public class Plasmapheresis extends BaseModel {

    private Date dateStarted, dateStopped;
    private PlasmapheresisExchangeUnit plasmapheresisExchanges;
    private RemissionAchieved response;

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateStopped() {
        return dateStopped;
    }

    public void setDateStopped(Date dateStopped) {
        this.dateStopped = dateStopped;
    }

    public PlasmapheresisExchangeUnit getPlasmapheresisExchanges() {
        return plasmapheresisExchanges;
    }

    public void setPlasmapheresisExchanges(PlasmapheresisExchangeUnit plasmapheresisExchanges) {
        this.plasmapheresisExchanges = plasmapheresisExchanges;
    }

    public RemissionAchieved getResponse() {
        return response;
    }

    public void setResponse(RemissionAchieved response) {
        this.response = response;
    }
}
