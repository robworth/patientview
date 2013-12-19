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

    private static final String LINKED_PATIENT_MARKUP = "<span class=\"patientLinked\" "
            + "title=\"Linked Patient\"></span>";

    private PatientDao patientDao;


    public List<Patient> getPatientByNhsNumber(String nhsNo) {
        return patientDao.getPatientsByNhsNumber(nhsNo);
    }

    public Patient getById(Long id) {
        return resolveLinkRecord(patientDao.getById(id));
    }

    public Patient getPatientByRadarNumber(Long radarNumber) {

        Patient patient = patientDao.getByRadarNumber(radarNumber);
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
            overRideLinkRecord(patientDao.getById(patient.getPatientLinkId()), patient);

        }

    }

    public List<Patient> getPatientsByUnitCode(List<String> unitCodes) {

        List<Patient> patients = patientDao.getPatientsByUnitCode(unitCodes);

        for (Patient patient : patients) {
            // Need to rewrite linked patient with populated version
            if (patient.isLinked()) {
                Patient sourcePatient = patientDao.getById(patient.getPatientLinkId());
                overRideLinkRecord(sourcePatient, patient);
                patient.setNhsno(LINKED_PATIENT_MARKUP
                        + sourcePatient.getNhsno());
            }
        }

        return patients;
    }

    /**
     * Method to create a Patient record linked to the original that is ready for registration
     *
     * @param source
     * @return
     * @throws PatientLinkException
     */
    public Patient createLinkPatient(Patient source) throws PatientLinkException {

        // Merge a new patient record with the source to create the new link record
        if (source == null || !source.hasValidId()) {
            throw new PatientLinkException("This has to be a valid source record");
        }

        Patient target = new Patient();

        target.setNhsno(source.getNhsno());
        target.setPatientLinkId(source.getId());
        target.setPatientLinkUnitCode(source.getUnitcode());
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
     * Replace linked fields on the link patient from the source patient
     * @param source the original PV record that is linked to
     * @param link the radar create record that linked to the source
     */
    public void overRideLinkRecord(Patient source, Patient link) {
        link.setForename(source.getForename());
        link.setSurname(source.getSurname());
        link.setDob(source.getDob());
        link.setAddress1(source.getAddress1());
        link.setAddress2(source.getAddress2());
        link.setAddress3(source.getAddress3());
        link.setAddress4(source.getAddress4());
        link.setPostcode(source.getPostcode());
        link.setSex(source.getSex());
        link.setTelephone1(source.getTelephone1());
        link.setHospitalnumber(source.getHospitalnumber());
        link.setPatientLinkUnitCode(source.getUnitcode());
    }


    /**
     * Resolve a patient link.
     *
     * 1) If it's a link record - get the source linked fields
     *
     * Otherwise just return the patient object
     *
     * @param patient a potentially linked patient that may need resolving
     * @return the same patient, but override the linked fields if it's a linked patient
     */
    private Patient resolveLinkRecord(final Patient patient) {

        if (patient != null && patient.isLinked()) {
            Patient source = patientDao.getById(patient.getPatientLinkId());
            if (source != null) {
                overRideLinkRecord(source, patient);
                return patient;
            } else {
                throw new RuntimeException("Source patient does not exist when trying to resolveLinkRecord, id: "
                        + patient.getPatientLinkId());
            }
        }

        return patient;
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
