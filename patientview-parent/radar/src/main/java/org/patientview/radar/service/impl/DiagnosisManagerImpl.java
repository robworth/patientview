/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.ClinicalPresentation;
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

    public String getDiagnosisName(Patient patient) {
        // some demographics will have a generic diagnosis from phase 2 check for this first
        if (patient.getGenericDiagnosis() != null
                && patient.getGenericDiagnosisModel().getId().length() > 0) {
            // disease groups have sub diagnosis but just use the main disease group name as the diagnosis
            return patient.getDiseaseGroup().getShortName();
        } else {
            Diagnosis diagnosis = getDiagnosisByRadarNumber(patient.getId());

            if (diagnosis != null && diagnosis.hasValidId() && diagnosis.getDiagnosisCode() != null) {
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
