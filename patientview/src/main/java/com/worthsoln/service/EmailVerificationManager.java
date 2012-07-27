package com.worthsoln.service;

import com.worthsoln.patientview.model.EmailVerification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface EmailVerificationManager {

    List<EmailVerification> get(String verificationCode);

    void save(EmailVerification emailVerification);

    void delete(String username);

    boolean verify(String verificationCode);
}
