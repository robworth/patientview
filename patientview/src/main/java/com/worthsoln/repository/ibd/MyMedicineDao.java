package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.medication.MyMedicine;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyMedicineDao {

    MyMedicine get(Long id);

    void save(MyMedicine myMedicine);

    List<MyMedicine> getAllMedicines(String nhsno);

    List<MyMedicine> getCurrentMedicines(String nhsno);

    List<MyMedicine> getStoppedMedicines(String nhsno);
}
