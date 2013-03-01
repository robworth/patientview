package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.Procedure;

public interface ProcedureDao {

    Procedure get(Long id);

    void save(Procedure procedure);

    Procedure getProcedure(String nhsno);

}
