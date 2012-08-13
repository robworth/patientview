package com.solidstategroup.radar.service.alport.impl;

import com.solidstategroup.radar.dao.alport.MedicineDao;
import com.solidstategroup.radar.model.alport.Medicine;
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

    public List<Medicine> getMedicines(String nhsNo) {
        return medicineDao.getMedicines(nhsNo);
    }

    public void setMedicineDao(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }
}
