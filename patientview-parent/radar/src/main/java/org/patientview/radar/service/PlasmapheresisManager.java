package org.patientview.radar.service;

import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.List;

public interface PlasmapheresisManager {

    void savePlasmapheresis(Plasmapheresis plasmapheresis) throws InvalidModelException;

    void deletePlasmaPheresis(Plasmapheresis plasmapheresis);

    Plasmapheresis getPlasmapheresis(long id);

    List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber);

    PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id);

    List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits();
}
