package com.worthsoln.repository;

import com.worthsoln.patientview.model.EmailVerification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface EmailVerificationDao {

    EmailVerification get(Long id);

    List<EmailVerification> get(String verificationCode);

    void save(EmailVerification emailVerification);

    void delete(String username);

    void delete(EmailVerification emailVerification);
}
