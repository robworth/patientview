package com.worthsoln.service;

import com.worthsoln.patientview.model.Medicine;

/**
 *
 */
public interface MedicineManager {

    Medicine get(Long id);

    void save(Medicine medicine);
}
