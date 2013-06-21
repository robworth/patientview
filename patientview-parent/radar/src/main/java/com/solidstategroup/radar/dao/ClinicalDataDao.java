package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Phenotype;
import com.solidstategroup.radar.model.sequenced.ClinicalData;

import java.util.List;

public interface ClinicalDataDao {

    void saveClinicalDate(ClinicalData clinicalData);

    ClinicalData getClinicalData(long id);

    List<ClinicalData> getClinicalDataByRadarNumber(long radarNumber);

    ClinicalData getFirstClinicalDataByRadarNumber(long radarNumber);

    Phenotype getPhenotype(long id);
    
    List<Phenotype> getPhenotypes();

}
