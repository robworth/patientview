package com.worthsoln.service;

import com.worthsoln.patientview.model.Centre;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface CentreManager {

    void save(Centre centre);

    void delete(Long id);

    void delete(String centreCode);

    List<Centre> getAll();
}
