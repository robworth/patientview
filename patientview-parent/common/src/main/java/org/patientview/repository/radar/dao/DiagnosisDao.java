package org.patientview.repository.radar.dao;

import org.patientview.model.radar.ClinicalPresentation;
import org.patientview.model.radar.Diagnosis;
import org.patientview.model.radar.DiagnosisCode;
import org.patientview.model.radar.Karotype;

import java.util.List;

public interface DiagnosisDao {

    void saveDiagnosis(Diagnosis diagnosis);

    Diagnosis getDiagnosis(long id);

    Diagnosis getDiagnosisByRadarNumber(long radarNumber);

    DiagnosisCode getDiagnosisCode(long id);

    List<DiagnosisCode> getDiagnosisCodes();

    ClinicalPresentation getClinicalPresentation(long id);

    List<ClinicalPresentation> getClinicalPresentations();

    Karotype getKarotype(long id);

    List<Karotype> getKarotypes();
}
