package org.patientview.repository.radar.service;

import org.patientview.model.Patient;
import org.patientview.model.radar.ClinicalPresentation;
import org.patientview.model.radar.Diagnosis;
import org.patientview.model.radar.DiagnosisCode;
import org.patientview.model.radar.Karotype;


import java.util.List;

public interface DiagnosisManager {

    void saveDiagnosis(Diagnosis diagnosis);

    Diagnosis getDiagnosis(long id);

    Diagnosis getDiagnosisByRadarNumber(long radarNumber);

    String getDiagnosisName(Patient patient);

    DiagnosisCode getDiagnosisCode(long id);

    List<DiagnosisCode> getDiagnosisCodes();

    ClinicalPresentation getClinicalPresentation(long id);

    List<ClinicalPresentation> getClinicalPresentations();

    Karotype getKarotype(long id);

    List<Karotype> getKarotypes();
}
