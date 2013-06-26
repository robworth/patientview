package org.patientview.radar.service.generic;

import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;

import java.util.List;

public interface GenericDiagnosisManager {

    List<GenericDiagnosis> getAll();

    List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup);

    GenericDiagnosis get(String prdCode, String workingGroup);

}
