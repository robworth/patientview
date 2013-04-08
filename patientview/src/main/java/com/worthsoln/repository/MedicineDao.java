package com.worthsoln.repository;

import com.worthsoln.patientview.model.Medicine;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MedicineDao {

    Medicine get(Long id);

    List<Medicine> getMedicines(String nhsno);

    void save(Medicine medicine);

    List<Medicine> getAll();

    void delete(String nhsno, String unitcode);
}
