package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;

import java.util.List;

public interface PlasmapheresisDao {

    void savePlasmapheresis(Plasmapheresis plasmapheresis);

    void deletePlasmaPheresis(Plasmapheresis plasmapheresis);

    Plasmapheresis getPlasmapheresis(long id);

    List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber);

    PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id);

    List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits();
}
