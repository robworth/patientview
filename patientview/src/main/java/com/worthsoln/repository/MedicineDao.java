package com.worthsoln.repository;

import com.worthsoln.patientview.model.Medicine;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MedicineDao {

    Medicine get(Long id);

    void save(Medicine medicine);
}
