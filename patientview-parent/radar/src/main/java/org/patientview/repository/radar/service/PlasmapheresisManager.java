package org.patientview.repository.radar.service;

import org.patientview.model.radar.Plasmapheresis;
import org.patientview.model.radar.PlasmapheresisExchangeUnit;
import org.patientview.model.radar.exception.InvalidModelException;

import java.util.List;

public interface PlasmapheresisManager {

    void savePlasmapheresis(Plasmapheresis plasmapheresis) throws InvalidModelException;

    void deletePlasmaPheresis(Plasmapheresis plasmapheresis);

    Plasmapheresis getPlasmapheresis(long id);

    List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber);

    PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id);

    List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits();
}
