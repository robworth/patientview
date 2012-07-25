package com.worthsoln.repository;

import com.worthsoln.patientview.model.Medicine;

public interface MedicineDao {

    Medicine get(Long id);

    void save(Medicine medicine);
}
