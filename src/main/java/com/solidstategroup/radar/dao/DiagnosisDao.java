package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.ClinicalPresentation;
import com.solidstategroup.radar.model.Diagnosis;

import java.util.List;

public interface DiagnosisDao {

    Diagnosis getDiagnosis(long id);

    ClinicalPresentation getClinicalPresentation(long id);

    List<ClinicalPresentation> getClinicalPresentations();

}
