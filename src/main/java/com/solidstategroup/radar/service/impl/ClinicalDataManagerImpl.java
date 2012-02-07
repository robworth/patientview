package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.model.Phenotype;
import com.solidstategroup.radar.model.sequenced.ClinicalData;
import com.solidstategroup.radar.service.ClinicalDataManager;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ClinicalDataManagerImpl implements ClinicalDataManager {
    @SpringBean
    private ClinicalDataDao clinicalDataDao;
    public void saveClinicalDate(ClinicalData clinicalData) {
        clinicalDataDao.saveClinicalDate(clinicalData);
    }

    public ClinicalData getClinicalData(long id) {
        return clinicalDataDao.getClinicalData(id);
    }

    public List<ClinicalData> getClinicalDataByRadarNumber(long radarNumber) {
        return clinicalDataDao.getClinicalDataByRadarNumber(radarNumber);
    }

    public ClinicalData getFirstClinicalDataByRadarNumber(long radarNumber) {
        return clinicalDataDao.getFirstClinicalDataByRadarNumber(radarNumber);
    }

    public Phenotype getPhenotype(long id) {
        return clinicalDataDao.getPhenotype(id);
    }

    public List<Phenotype> getPhenotypes() {
        return clinicalDataDao.getPhenotypes();
    }
}
