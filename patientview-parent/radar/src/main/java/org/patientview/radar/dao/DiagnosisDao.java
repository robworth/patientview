package org.patientview.radar.dao;

import org.patientview.radar.model.ClinicalPresentation;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;

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
