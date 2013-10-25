package org.patientview.repository.radar.service.alport.impl;

import org.patientview.model.Patient;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.repository.radar.dao.alport.MedicineDao;
import org.patientview.model.radar.alport.Medicine;
import org.patientview.repository.radar.service.alport.MedicineManager;

import java.util.List;

public class MedicineManagerImpl implements MedicineManager {

    private MedicineDao medicineDao;

    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }

    public void delete(Medicine medicine) {
        medicineDao.delete(medicine);
    }

    public Medicine get(Long id) {
        return medicineDao.get(id);
    }

    public List<Medicine> getMedicines(Patient patient) {
        if (patient.getNhsno() != null) {
            return medicineDao.getMedicinesByNhsNo(patient.getNhsno());
        }

        return null;
    }

    public List<Medicine> getMedicines(Patient patient, DiseaseGroup diseaseGroup) {
        if (patient.getNhsno() != null) {
            return medicineDao.getMedicinesByNhsNoAndDiseaseGroup(patient.getNhsno(), diseaseGroup);
        }

        return null;
    }

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }
}
