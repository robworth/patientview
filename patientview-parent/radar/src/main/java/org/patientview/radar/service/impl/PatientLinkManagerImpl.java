package org.patientview.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.PatientLinkDao;
import org.patientview.radar.exception.PatientLinkException;
import org.patientview.radar.service.PatientLinkManager;
import org.patientview.radar.util.RadarUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 17:04
 */
public class PatientLinkManagerImpl implements PatientLinkManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(PatientLinkManagerImpl.class);

    private DemographicsDao demographicsDao;

    private PatientLinkDao patientLinkDao;


    // create the new patient record and link entity
    public Patient createLinkPatientRecord(Patient patient) throws PatientLinkException {

       Patient newPatient = new Patient();
       newPatient.setNhsno(patient.getNhsno());
       newPatient.setDiagnosisDate(patient.getDiagnosisDate());
       if (patient.getDiseaseGroup() != null) {
            newPatient.setUnitcode(patient.getDiseaseGroup().getId());
       }

       return RadarUtility.mergePatientRecords(patient, newPatient);
    }


    // Create bare patient record for Radar - reset the Id and remove fields that shouldn't be used
    public Patient createLinkRecord(Patient patient) {

        patient.setId(0L);
        RadarUtility.mergePatientRecords(patient, new Patient());
        patient.setUnitcode(patient.getDiseaseGroup().getId());
        return patient;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }

}
