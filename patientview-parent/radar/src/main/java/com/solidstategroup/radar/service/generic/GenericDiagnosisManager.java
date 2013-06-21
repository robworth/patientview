package com.solidstategroup.radar.service.generic;

import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.model.generic.GenericDiagnosis;

import java.util.List;

public interface GenericDiagnosisManager {

    List<GenericDiagnosis> getAll();

    List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup);

    GenericDiagnosis get(String prdCode, String workingGroup);

}
