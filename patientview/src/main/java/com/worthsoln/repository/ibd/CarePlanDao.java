package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.CarePlan;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface CarePlanDao {

    CarePlan get(Long id);

    CarePlan get(String nhsno);

    void save(CarePlan carePlan);
}
