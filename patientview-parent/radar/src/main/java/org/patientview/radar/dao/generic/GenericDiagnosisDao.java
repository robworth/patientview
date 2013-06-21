package org.patientview.radar.dao.generic;

import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;

import java.util.List;

public interface GenericDiagnosisDao {

    List<GenericDiagnosis> getAll();

    List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup);

    GenericDiagnosis get(String prdCode, String workingGroup);

}
