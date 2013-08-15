package org.patientview.radar.service.hnf1b.impl;

import org.patientview.radar.dao.hnf1b.HNF1BMiscDao;
import org.patientview.radar.model.hnf1b.HNF1BMisc;
import org.patientview.radar.service.hnf1b.HNF1BMiscManager;

public class HNF1BMiscManagerImpl implements HNF1BMiscManager {

    private HNF1BMiscDao hnf1BMiscDao;

    public void save(HNF1BMisc hnf1BMisc) {
        hnf1BMiscDao.save(hnf1BMisc);
    }

    public HNF1BMisc get(Long radarNo) {
        return hnf1BMiscDao.get(radarNo);
    }

    public void setHnf1BMiscDao(HNF1BMiscDao hnf1BMiscDao) {
        this.hnf1BMiscDao = hnf1BMiscDao;
    }
}
