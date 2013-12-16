package org.patientview.radar.dao.generic;

import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;

import java.util.List;

public interface GenericDiagnosisDao {

    List<GenericDiagnosis> getAll();

    List<GenericDiagnosis> getByDiseaseGroup(DiseaseGroup diseaseGroup);

    GenericDiagnosis get(String prdCode, String workingGroup);

}
