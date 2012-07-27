package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.repository.MedicineDao;
import com.worthsoln.service.MedicineManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 *
 */
@Service(value = "medicineManager")
public class MedicineManagerImpl implements MedicineManager {

    @Inject
    private MedicineDao medicineDao;

    @Override
    public Medicine get(Long id) {
        return medicineDao.get(id);
    }

    @Override
    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }
}
