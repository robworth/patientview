package com.worthsoln.service;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.User;

import java.util.List;

/**
 *
 */
public interface SplashPageManager {

    SplashPage get(Long id);

    void save(SplashPage splashPage);

    void delete(SplashPage splashPage);

    List<SplashPage> getAll();

    List<SplashPage> getAllForPatient(User user);

    List<SplashPage> getSeenForPatient(User user);

    void removeSeenSplashPage(Long id);
}
