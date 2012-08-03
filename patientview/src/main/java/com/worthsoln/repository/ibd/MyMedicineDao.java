package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.MyMedicine;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyMedicineDao {

    MyMedicine get(Long id);

    void save(MyMedicine myMedicine);
}
