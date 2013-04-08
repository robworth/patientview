package com.worthsoln.repository;

import com.worthsoln.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface SpecialtyDao {

    Specialty get(Long id);

    List<Specialty> getAll();

    void save(Specialty specialty);
}
