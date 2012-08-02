package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.CarePlan;

public interface CarePlanDao {

    CarePlan get(Long id);

    CarePlan get(String nhsno);

    void save(CarePlan carePlan);

}
