package com.worthsoln.repository;

import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.enums.DiagnosticType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface DiagnosticDao {

    Diagnostic get(Long id);

    Diagnostic get(String nhsno);

    void save(Diagnostic diagnostic);

    List<Diagnostic> get(Set<String> nhsNos, DiagnosticType diagnosticType);
}
