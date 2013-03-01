package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.Allergy;

public interface AllergyDao {

    Allergy get(Long id);

    void save(Allergy allergy);

    Allergy getAllergy(String nhsno);

}
