package com.worthsoln.service;

import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.enums.DiagnosticType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface DiagnosticManager {

    void save(Diagnostic diagnostic);

    List<Diagnostic> getForLoggedInUser(DiagnosticType diagnosticType);
}
