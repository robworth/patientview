package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.TransplantDao;
import com.solidstategroup.radar.model.Transplant;
import com.solidstategroup.radar.service.TransplantManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class TransplantManagerImpl implements TransplantManager {

    TransplantDao transplantDao;

    public void saveTransplant(Transplant transplant) {
        transplantDao.saveTransplant(transplant);
    }

    public void deleteTransplant(Transplant transplant) {
        transplantDao.deleteTransplant(transplant);
    }

    public Transplant getTransplant(long id) {
        return transplantDao.getTransplant(id);
    }

    public List<Transplant> getTransplantsByRadarNumber(long radarNumber) {
        return transplantDao.getTransplantsByRadarNumber(radarNumber);
    }

    public List<Transplant.Modality> getTransplantModalitites() {
        return transplantDao.getTransplantModalitites();
    }

    public Transplant.Modality getTransplantModality(long id) {
        return transplantDao.getTransplantModality(id);
    }

    public void saveRejectData(Transplant.RejectData rejectData) {
        transplantDao.saveRejectData(rejectData);
    }

    public void deleteRejectData(Transplant.RejectData rejectData) {
        transplantDao.deleteRejectData(rejectData);
    }

    public List<Transplant.RejectData> getRejectDataByTransplantNumber(Long transplantNumber) {
        return transplantDao.getRejectDataByTransplantNumber(transplantNumber);
    }

    public Transplant.RejectData getRejectData(Long id) {
        return transplantDao.getRejectData(id);
    }

    public TransplantDao getTransplantDao() {
        return transplantDao;
    }

    public void setTransplantDao(TransplantDao transplantDao) {
        this.transplantDao = transplantDao;
    }
}
