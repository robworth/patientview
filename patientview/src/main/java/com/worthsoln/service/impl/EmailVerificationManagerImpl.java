package com.worthsoln.service.impl;

import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.EmailVerification;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.EmailVerificationDao;
import com.worthsoln.service.EmailVerificationManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "emailVerificationManager")
public class EmailVerificationManagerImpl implements EmailVerificationManager {

    @Inject
    private EmailVerificationDao emailVerificationDao;

    @Inject
    private UserManager userManager;

    @Override
    public List<EmailVerification> get(String verificationCode) {
        return emailVerificationDao.get(verificationCode);
    }

    @Override
    public void save(EmailVerification emailVerification) {
        emailVerificationDao.save(emailVerification);
    }

    @Override
    public void delete(String username) {
        emailVerificationDao.delete(username);
    }

    @Override
    public boolean verify(String verificationCode) {

        List<EmailVerification> emailVerifications = get(verificationCode);

        if (!emailVerifications.isEmpty()) {
            EmailVerification emailVerification = emailVerifications.get(0);

            if (null != emailVerification) {
                User user = userManager.get(emailVerification.getUsername());
                if (null != user) {
                    if (emailVerification.getEmail().equals(user.getEmail())) {
                        user.setEmailverified(true);
                        userManager.save(user);
                        emailVerificationDao.delete(emailVerification);

                        AddLog.addLog(emailVerification.getUsername(),
                                AddLog.EMAIL_VERIFY, emailVerification.getUsername(),
                                userManager.getUsersRealNhsNoBestGuess(emailVerification.getUsername()),
                                userManager.getUsersRealNhsNoBestGuess(emailVerification.getUsername()),
                                emailVerification.getEmail());
                        // success
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
