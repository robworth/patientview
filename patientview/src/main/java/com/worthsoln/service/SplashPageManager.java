package com.worthsoln.service;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface SplashPageManager {

    SplashPage get(Long id);

    void save(SplashPage splashPage);

    void save(SplashPageUserSeen splashPageUserSeen);

    void delete(SplashPage splashPage);

    List<SplashPage> getAll();

    List<SplashPage> getAllForPatient(User user);

    List<SplashPageUserSeen> getSeenForPatient(User user);

    void removeSeenSplashPage(Long id);
}
