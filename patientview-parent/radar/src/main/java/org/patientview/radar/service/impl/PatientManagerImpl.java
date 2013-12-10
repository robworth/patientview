package org.patientview.radar.service.impl;

import org.patientview.model.Ethnicity;
import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.enums.SourceType;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.patientview.radar.dao.PatientDao;
import org.patientview.radar.exception.PatientLinkException;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.util.RadarUtility;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

        Patient patient = patientDao.getPatientsByRadarNumber(radarNumber);

        if (patient == null) {
            patient = patientDao.getById(radarNumber);
        }

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
        Map<Long, Patient> linkedPatients = new HashMap<Long, Patient>();


        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();

            // Need to override linked patients
            if (patient.getPatientLinkId() != null && patient.getPatientLinkId() > 0) {
                Patient linkedPatient = RadarUtility.overRideLinkRecord(patient,
                        patientDao.getById(patient.getPatientLinkId()));
                linkedPatient.setSurname("(LINKED)" + linkedPatient.getSurname());
                linkedPatients.put(patient.getPatientLinkId(), linkedPatient);
                iterator.remove();
            }
        }

        resolveLinkedPatients(patients, linkedPatients);

        return patients;
    }

    // This removes the patient view patients and replaces the linked patient with fully loaded merged ones.
    private void resolveLinkedPatients(List<Patient> patients, Map<Long, Patient> linkedPatients) {

        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();

            if (!patient.getSourceType().equals(SourceType.RADAR.getName())) {
                iterator.remove();
            }

            // There is a better way
            if (StringUtils.isEmpty(patient.getSurname())) {
                    iterator.remove();
            }

        }

        patients.addAll(linkedPatients.values());

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
     * Resolve a two way link.
     *
     * 1) If it's a source record them merge the link record on top of it
     * 2) If it's a link record over write the standard patient fields.
     *
     * @param patient
     * @return
     */
    private Patient resolveLinkRecord(final Patient patient){

        if (patient != null) {

            Long patientLinkId = patient.getPatientLinkId();

            if (patientLinkId == null || patientLinkId == 0) {

                Patient source = patientDao.getByPatientLinkId(patient.getId());
                if (source != null) {
                    return RadarUtility.overRideLinkRecord(source, patient);
                } else {
                    return patient;
                }
            } else {

                Patient linkPatient = patientDao.getById(patientLinkId);
                if (linkPatient == null) {
                    return RadarUtility.mergePatientRecords(patient, linkPatient);
                } else {
                    return patient;
                }

            }
        } else {
            return null;
        }
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
