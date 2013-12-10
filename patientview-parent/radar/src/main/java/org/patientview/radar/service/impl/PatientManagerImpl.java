package org.patientview.radar.service.impl;

import org.patientview.model.Ethnicity;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.patientview.radar.dao.PatientDao;
import org.patientview.radar.exception.PatientLinkException;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.util.RadarUtility;

import java.util.List;

/**
 * Created for the new functionality with using just the on patient table. Going forward this can be then merged with
 * the class of the same name in Patient View
 *
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 11:11
 */
public class PatientManagerImpl implements PatientManager {

    private PatientDao patientDao;


    public List<Patient> getPatientByNhsNumber(String nhsNo) {
        return patientDao.getPatientsByNhsNumber(nhsNo);
    }

    public Patient getById(Long id) {
        return resolveLinkRecord(patientDao.getById(id));
    }

    public Patient getPatientByRadarNumber(Long radarNumber) {

        Patient patient = patientDao.getById(radarNumber);
        patient = resolveLinkRecord(patient);
        return patient;
    }

    /**
     * This manages four saves. On create it will populate the radar number with the patient id field
     *
     * 1) A linked patient save where we strip fields out of the update statement and then re populate
     * 2) A linked patient create
     * 3) A unlinked patient save
     * 4) A unlinked patient create
     *
     * @param patient
     */
    public void save(final Patient patient){

        // If this is a link record then we need to stop any duplicated data being saved
        if (patient.isLinked()) {
            RadarUtility.cleanLinkRecord(patient);
        }

        patientDao.save(patient);

        // We have to re-populate fields after they are cleaned from the save, only for link patients
        if (patient.isLinked()) {
            RadarUtility.overRideLinkRecord(patientDao.getById(patient.getPatientLinkId()), patient);

        }

    }

    public List<Patient> getPatientsByUnitCode(List<String> unitCodes) {

        List<Patient> patients = patientDao.getPatientsByUnitCode(unitCodes);

        for (Patient patient : patients) {
            // Need to rewrite linked patient with populated version
            if (patient.isLinked()) {
                Patient sourcePatient = patientDao.getById(patient.getPatientLinkId());
                RadarUtility.overRideLinkRecord(sourcePatient, patient);
                patient.setSurname("(LINKED) " + sourcePatient.getSurname());
            }
        }

        return patients;
    }

    /**
     * Method to create a Patient record linked to the original thats is ready for registration
     *
     * @param source
     * @return
     * @throws PatientLinkException
     */
    public Patient createLinkPatient(Patient source) throws PatientLinkException {

        // Merge a new patient record with the source to create the new link record
        if (!source.hasValidId()) {
            throw new PatientLinkException("This has to be a valid source record");
        }

        Patient target = new Patient();

        target.setNhsno(source.getNhsno());
        target.setPatientLinkId(source.getId());
        target.setForename(source.getForename());
        target.setSurname(source.getSurname());
        target.setDob(source.getDob());
        target.setAddress1(source.getAddress1());
        target.setAddress2(source.getAddress2());
        target.setAddress3(source.getAddress3());
        target.setAddress4(source.getAddress4());
        target.setPostcode(source.getPostcode());
        target.setSex(source.getSex());
        target.setTelephone1(source.getTelephone1());
        target.setHospitalnumber(source.getHospitalnumber());


        return target;


    }


    /**
     * Resolve a patient link.
     *
     * 1) If it's a link record - get the source linked fields
     *
     * @param patient
     * @return
     */
    private Patient resolveLinkRecord(final Patient patient) {

        if (patient != null && patient.isLinked()) {
            Patient source = patientDao.getById(patient.getPatientLinkId());
            if (source != null) {
                RadarUtility.overRideLinkRecord(source, patient);
                return patient;
            } else {
                throw new RuntimeException("Source patient does not exist when trying to resolveLinkRecord, id: "
                        + patient.getPatientLinkId());
            }
        }

        return null;
    }


    public List<Sex> getSexes() {
        return patientDao.getSexes();
    }

    public List<Status> getStatuses() {
        return patientDao.getStatuses();
    }

    public List<DiseaseGroup> getDiseaseGroups() {
        return patientDao.getDiseaseGroups();
    }

    public List<GenericDiagnosis> getGenericDiagnoses() {
        return patientDao.getGenericDiagnoses();
    }

    public List<Ethnicity> getEthnicities() {
        return patientDao.getEthnicities();
    }


    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }


}
