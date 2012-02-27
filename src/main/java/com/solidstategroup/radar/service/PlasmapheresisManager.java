package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.exception.InvalidModelException;

import java.util.List;

public interface PlasmapheresisManager {

    public static final String PREVIOUS_TREATMENT_NOT_STOPPED_ERROR = "Cannot start a new treatment whilst a " +
            "previous " + "treament has not been stopped";
    public static final String OVERLAPPING_ERROR = "Cannot add plasmapheresis overlapping with a previous entry";

    void savePlasmapheresis(Plasmapheresis plasmapheresis) throws InvalidModelException;

    void deletePlasmaPheresis(Plasmapheresis plasmapheresis);

    Plasmapheresis getPlasmapheresis(long id);

    List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber);

    PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id);

    List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits();
}
