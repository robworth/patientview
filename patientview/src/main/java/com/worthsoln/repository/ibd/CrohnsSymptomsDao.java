package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;

import java.util.Date;
import java.util.List;

public interface CrohnsSymptomsDao {

    CrohnsSymptoms get(Long id);

    void save(CrohnsSymptoms crohnsSymptoms);

    List<CrohnsSymptoms> getAllCrohns(String nhsno, Date fromDate, Date toDate);
}
