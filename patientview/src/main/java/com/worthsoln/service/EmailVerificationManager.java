package com.worthsoln.service;

import com.worthsoln.patientview.model.EmailVerification;

import java.util.List;

/**
 *
 */
public interface EmailVerificationManager {

    List<EmailVerification> get(String verificationCode);

    void save(EmailVerification emailVerification);

    void delete(String username);

    boolean verify(String verificationCode);
}
