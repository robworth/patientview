package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.ImmunosuppressionDao;
import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import com.solidstategroup.radar.service.ImmunosuppressionManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;


public class ImmunosuppressionManagerImpl implements ImmunosuppressionManager {

    ImmunosuppressionDao immunosuppressionDao;

    public void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) {
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppressionTreatment);
    }

    public void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) {
         immunosuppressionDao.deleteImmunosuppressionTreatment(immunosuppressionTreatment);
    }

    public ImmunosuppressionTreatment getImmunosuppressionTreatment(long id) {
        return immunosuppressionDao.getImmunosuppressionTreatment(id);
    }

    public List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber) {
        return immunosuppressionDao.getImmunosuppressionTreatmentByRadarNumber(radarNumber);
    }

    public List<Immunosuppression> getImmunosuppressions() {
        return immunosuppressionDao.getImmunosuppressions();
    }

    public Immunosuppression getImmunosuppression(long id) {
        return immunosuppressionDao.getImmunosuppression(id);
    }

    public ImmunosuppressionDao getImmunosuppressionDao() {
        return immunosuppressionDao;
    }

    public void setImmunosuppressionDao(ImmunosuppressionDao immunosuppressionDao) {
        this.immunosuppressionDao = immunosuppressionDao;
    }
}
