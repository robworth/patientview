package org.patientview.radar.model;

import org.patientview.radar.model.enums.RemissionAchieved;

import java.util.Date;

public class Plasmapheresis extends RadarModel {

    private Date startDate, endDate;
    private PlasmapheresisExchangeUnit plasmapheresisExchanges;
    private RemissionAchieved response;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "Plasmapheresis{" +
                "id=" + getId() +
                ", radarNumber=" + getRadarNumber() +
                '}';
    }
}
