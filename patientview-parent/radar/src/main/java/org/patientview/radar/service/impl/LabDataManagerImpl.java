package org.patientview.radar.service.impl;

import org.patientview.radar.dao.LabDataDao;
import org.patientview.radar.model.sequenced.LabData;
import org.patientview.radar.service.LabDataManager;

import java.util.List;


public class LabDataManagerImpl implements LabDataManager {

    private LabDataDao labDataDao;

    public void saveLabData(LabData labData) {
       labDataDao.saveLabData(labData);
    }

    public LabData getLabData(long id) {
        return labDataDao.getLabData(id);
    }

    public List<LabData> getLabDataByRadarNumber(long id) {
        return labDataDao.getLabDataByRadarNumber(id);
    }

    public LabData getFirstLabDataByRadarNumber(Long id) {
        return labDataDao.getFirstLabDataByRadarNumber(id);
    }

    public LabDataDao getLabDataDao() {
        return labDataDao;
    }

    public void setLabDataDao(LabDataDao labDataDao) {
        this.labDataDao = labDataDao;
    }
}
