package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.repository.MedicineDao;
import com.worthsoln.service.MedicineManager;

import javax.inject.Inject;

/**
 *
 */
public class MedicineManagerImpl implements MedicineManager {

    @Inject
    private MedicineDao medicineDao;

    @Override
    public void save(Medicine medicine) {
        medicineDao.save(medicine);
    }
}
