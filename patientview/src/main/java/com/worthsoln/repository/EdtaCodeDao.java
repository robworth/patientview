package com.worthsoln.repository;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface EdtaCodeDao {

    EdtaCode getEdtaCode(String edtaCode, Specialty specialty);

    void save(EdtaCode edtaCode);

    void delete(String edtaCode, Specialty specialty);

    List<EdtaCode> get(String linkType, Specialty specialty);
}
