package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.PathologyDao;
import com.solidstategroup.radar.model.sequenced.Pathology;
import com.solidstategroup.radar.service.PathologyManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class PathologyManagerImpl implements PathologyManager {

    private PathologyDao pathologyDao;

    public void savePathology(Pathology pathology) {
        pathologyDao.savePathology(pathology);
    }

    public Pathology getPathology(long id) {
        return pathologyDao.getPathology(id);
    }

    public List<Pathology> getPathologyByRadarNumber(long radarNumber) {
        return pathologyDao.getPathologyByRadarNumber(radarNumber);
    }

    public PathologyDao getPathologyDao() {
        return pathologyDao;
    }

    public void setPathologyDao(PathologyDao pathologyDao) {
        this.pathologyDao = pathologyDao;
    }
}
