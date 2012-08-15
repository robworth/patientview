package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.symptoms.ColitisSymptoms;

import java.util.Date;
import java.util.List;

public interface ColitisSymptomsDao {

    ColitisSymptoms get(Long id);

    void save(ColitisSymptoms colitisSymptoms);

    List<ColitisSymptoms> getAllColitis(String nhsno, Date fromDate, Date toDate);

}
