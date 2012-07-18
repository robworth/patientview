package com.worthsoln.repository;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.SplashPage;

import java.util.List;

/**
 *
 */
public interface SplashPageDao {

    SplashPage get(Long id);

    void save(SplashPage splashPage);

    void delete(SplashPage splashPage);

    List<SplashPage> getAll(User user);

    List<SplashPage> getAllForPatient(User user);

    List<SplashPage> getSeenForPatient(User user);

    void removeSeenSplashPage(Long id);
}
