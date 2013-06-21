package com.solidstategroup.radar.dao.generic;

import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.model.generic.GenericDiagnosis;

import java.util.List;

public interface GenericDiagnosisDao {

    List<GenericDiagnosis> getAll();

    List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup);

    GenericDiagnosis get(String prdCode, String workingGroup);

}
