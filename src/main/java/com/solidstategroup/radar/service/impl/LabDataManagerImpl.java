package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.LabDataDao;
import com.solidstategroup.radar.model.sequenced.LabData;
import com.solidstategroup.radar.service.LabDataManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class LabDataManagerImpl implements LabDataManager {

    @SpringBean
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
}
