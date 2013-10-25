package org.patientview.repository.radar.dao;

import org.patientview.model.radar.Plasmapheresis;
import org.patientview.model.radar.PlasmapheresisExchangeUnit;

import java.util.List;

public interface PlasmapheresisDao {

    void savePlasmapheresis(Plasmapheresis plasmapheresis);

    void deletePlasmaPheresis(Plasmapheresis plasmapheresis);

    Plasmapheresis getPlasmapheresis(long id);

    List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber);

    PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id);

    List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits();
}
