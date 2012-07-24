package com.worthsoln.repository;

import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;

import java.util.List;

/**
 *
 */
public interface SplashPageUserSeenDao {

    void save(SplashPageUserSeen splashPageUserSeen);

    void delete(Long id);

    List<SplashPageUserSeen> getSeenForPatient(User user);
}
