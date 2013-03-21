package com.solidstategroup.radar.service.alport.impl;

import com.solidstategroup.radar.dao.alport.MedicineDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.alport.Medicine;
import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.service.alport.MedicineManager;

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
