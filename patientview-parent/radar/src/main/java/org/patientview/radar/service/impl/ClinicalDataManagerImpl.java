package org.patientview.radar.service.impl;

import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.model.Phenotype;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.service.ClinicalDataManager;

import java.util.List;

public class ClinicalDataManagerImpl implements ClinicalDataManager {

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

    public ClinicalDataDao getClinicalDataDao() {
        return clinicalDataDao;
    }

    public void setClinicalDataDao(ClinicalDataDao clinicalDataDao) {
        this.clinicalDataDao = clinicalDataDao;
    }
}
