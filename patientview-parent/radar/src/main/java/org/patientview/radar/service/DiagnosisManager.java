package org.patientview.radar.service;

import org.patientview.radar.model.ClinicalPresentation;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;

import java.util.List;

public interface DiagnosisManager {

    void saveDiagnosis(Diagnosis diagnosis);

    Diagnosis getDiagnosis(long id);

    Diagnosis getDiagnosisByRadarNumber(long radarNumber);

    String getDiagnosisName(Demographics demographics);

    DiagnosisCode getDiagnosisCode(long id);

    List<DiagnosisCode> getDiagnosisCodes();

    ClinicalPresentation getClinicalPresentation(long id);

    List<ClinicalPresentation> getClinicalPresentations();

    Karotype getKarotype(long id);

    List<Karotype> getKarotypes();
}
