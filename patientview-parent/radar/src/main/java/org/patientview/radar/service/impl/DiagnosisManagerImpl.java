package org.patientview.radar.service.impl;

import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.ClinicalPresentation;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;
import org.patientview.radar.service.DiagnosisManager;

import java.util.List;


public class DiagnosisManagerImpl implements DiagnosisManager {

    private DiagnosisDao diagnosisDao;

    public void saveDiagnosis(Diagnosis diagnosis) {
        diagnosisDao.saveDiagnosis(diagnosis);
    }

    public Diagnosis getDiagnosis(long id) {
        return diagnosisDao.getDiagnosis(id);
    }

    public Diagnosis getDiagnosisByRadarNumber(long radarNumber) {
        return diagnosisDao.getDiagnosisByRadarNumber(radarNumber);
    }

    public String getDiagnosisName(Demographics demographics) {
        // some demographics will have a generic diagnosis from phase 2 check for this first
        if (demographics.getGenericDiagnosis() != null
                && demographics.getGenericDiagnosis().getId().length() > 0) {
            // disease groups have sub diagnosis but just use the main disease group name as the diagnosis
            return demographics.getDiseaseGroup().getShortName();
        } else {
            Diagnosis diagnosis = getDiagnosisByRadarNumber(demographics.getId());

            if (diagnosis != null && diagnosis.hasValidId()) {
                return diagnosis.getDiagnosisCode().getAbbreviation();
            }
        }

        return "";
    }

    public DiagnosisCode getDiagnosisCode(long id) {
        return diagnosisDao.getDiagnosisCode(id);
    }

    public List<DiagnosisCode> getDiagnosisCodes() {
        return diagnosisDao.getDiagnosisCodes();
    }

    public ClinicalPresentation getClinicalPresentation(long id) {
        return diagnosisDao.getClinicalPresentation(id);
    }

    public List<ClinicalPresentation> getClinicalPresentations() {
        return diagnosisDao.getClinicalPresentations();
    }

    public Karotype getKarotype(long id) {
        return diagnosisDao.getKarotype(id);
    }

    public List<Karotype> getKarotypes() {
        return diagnosisDao.getKarotypes();
    }

    public DiagnosisDao getDiagnosisDao() {
        return diagnosisDao;
    }

    public void setDiagnosisDao(DiagnosisDao diagnosisDao) {
        this.diagnosisDao = diagnosisDao;
    }
}
