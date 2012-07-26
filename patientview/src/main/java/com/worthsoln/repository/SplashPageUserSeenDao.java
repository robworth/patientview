package com.worthsoln.repository;

import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface SplashPageUserSeenDao {

    void save(SplashPageUserSeen splashPageUserSeen);

    void delete(Long id);

    List<SplashPageUserSeen> getSeenForPatient(User user);
}
