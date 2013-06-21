package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.Karotype;

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
