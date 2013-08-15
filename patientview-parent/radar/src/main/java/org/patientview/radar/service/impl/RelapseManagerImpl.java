package org.patientview.radar.service.impl;

import org.patientview.radar.dao.RelapseDao;
import org.patientview.radar.model.sequenced.Relapse;
import org.patientview.radar.service.RelapseManager;

import java.util.List;

public class RelapseManagerImpl implements RelapseManager {

    RelapseDao relapseDao;

    public void saveRelapse(Relapse relapse) {
        relapseDao.saveRelapse(relapse);
    }

    public void deleteRelapse(Relapse relapse) {
         relapseDao.deleteRelapse(relapse);
    }

    public Relapse getRelapse(long id) {
        return relapseDao.getRelapse(id);
    }

    public List<Relapse> getRelapsesByRadarNumber(long radarNumber) {
        return relapseDao.getRelapsesByRadarNumber(radarNumber);
    }

    public RelapseDao getRelapseDao() {
        return relapseDao;
    }

    public void setRelapseDao(RelapseDao relapseDao) {
        this.relapseDao = relapseDao;
    }
}
