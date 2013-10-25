package org.patientview.repository.radar.service.impl;

import org.patientview.repository.radar.dao.ClinicalDataDao;
import org.patientview.model.radar.Phenotype;
import org.patientview.model.radar.sequenced.ClinicalData;
import org.patientview.repository.radar.service.ClinicalDataManager;

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
