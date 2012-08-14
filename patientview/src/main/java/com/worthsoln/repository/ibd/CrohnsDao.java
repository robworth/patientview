package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.Crohns;

import java.util.List;

public interface CrohnsDao {

    Crohns get(Long id);

    void save(Crohns crohns);

    List<Crohns> getAllCrohns(String nhsno);

}
