package org.patientview.repository.radar.service;

import org.patientview.model.radar.Phenotype;
import org.patientview.model.radar.sequenced.ClinicalData;

import java.util.List;

public interface ClinicalDataManager {

    void saveClinicalDate(ClinicalData clinicalData);

    ClinicalData getClinicalData(long id);

    List<ClinicalData> getClinicalDataByRadarNumber(long radarNumber);

    ClinicalData getFirstClinicalDataByRadarNumber(long radarNumber);

    Phenotype getPhenotype(long id);
    
    List<Phenotype> getPhenotypes();

}
