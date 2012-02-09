package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.PlasmapheresisDao;
import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.service.PlasmapheresisManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class PlasmapheresisManagerImpl implements PlasmapheresisManager {

    private PlasmapheresisDao plasmapheresisDao;

    public void savePlasmapheresis(Plasmapheresis plasmapheresis) {
        plasmapheresisDao.savePlasmapheresis(plasmapheresis);
    }

    public void deletePlasmaPheresis(Plasmapheresis plasmapheresis) {
        plasmapheresisDao.deletePlasmaPheresis(plasmapheresis);
    }

    public Plasmapheresis getPlasmapheresis(long id) {
        return plasmapheresisDao.getPlasmapheresis(id);
    }

    public List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber) {
        return plasmapheresisDao.getPlasmapheresisByRadarNumber(radarNumber);
    }

    public PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id) {
        return plasmapheresisDao.getPlasmapheresisExchangeUnit(id);
    }

    public List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits() {
        return plasmapheresisDao.getPlasmapheresisExchangeUnits();
    }

    public PlasmapheresisDao getPlasmapheresisDao() {
        return plasmapheresisDao;
    }

    public void setPlasmapheresisDao(PlasmapheresisDao plasmapheresisDao) {
        this.plasmapheresisDao = plasmapheresisDao;
    }
}
