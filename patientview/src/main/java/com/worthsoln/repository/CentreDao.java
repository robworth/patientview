package com.worthsoln.repository;

import com.worthsoln.patientview.model.Centre;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface CentreDao {

    Centre get(String centreCode);

    void save(Centre centre);

    void delete(Long id);

    void delete(String centreCode);

    List<Centre> getAll();
}
