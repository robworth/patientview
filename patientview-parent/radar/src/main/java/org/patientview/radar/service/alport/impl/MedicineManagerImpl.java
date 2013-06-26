package org.patientview.radar.service.alport.impl;

import org.patientview.radar.dao.alport.MedicineDao;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.service.alport.MedicineManager;

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

    public List<Medicine> getMedicines(Demographics demographics) {
        if (demographics.getNhsNumber() != null) {
            return medicineDao.getMedicinesByNhsNo(demographics.getNhsNumber());
        }

        return null;
    }

    public List<Medicine> getMedicines(Demographics demographics, DiseaseGroup diseaseGroup) {
        if (demographics.getNhsNumber() != null) {
            return medicineDao.getMedicinesByNhsNoAndDiseaseGroup(demographics.getNhsNumber(), diseaseGroup);
        }

        return null;
    }

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }
}
