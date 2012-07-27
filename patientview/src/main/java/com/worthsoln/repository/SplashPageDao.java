package com.worthsoln.repository;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.SplashPage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface SplashPageDao {

    SplashPage get(Long id);

    void save(SplashPage splashPage);

    void delete(SplashPage splashPage);

    List<SplashPage> getAll(List<String> unitcodes);
}
