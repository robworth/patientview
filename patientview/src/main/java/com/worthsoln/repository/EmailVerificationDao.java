package com.worthsoln.repository;

import com.worthsoln.patientview.model.EmailVerification;

import java.util.List;

/**
 *
 */
public interface EmailVerificationDao {

    List<EmailVerification> get(String verificationCode);

    void save(EmailVerification emailVerification);

    void delete(String username);

    void delete(EmailVerification emailVerification);
}
