package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.IbdDiagnostic;

public interface IbdDiagnosticDao {

    IbdDiagnostic get(Long id);

    void save(IbdDiagnostic ibdDiagnostic);

    IbdDiagnostic getDiagnostic(String nhsno);

}
