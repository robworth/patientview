package com.worthsoln.repository;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.Tenancy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface EdtaCodeDao {

    EdtaCode getEdtaCode(String edtaCode, Tenancy tenancy);

    void save(EdtaCode edtaCode);

    void delete(String edtaCode, Tenancy tenancy);

    List<EdtaCode> get(String linkType, Tenancy tenancy);
}
