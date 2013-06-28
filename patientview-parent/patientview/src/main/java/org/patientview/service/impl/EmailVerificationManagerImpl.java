/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.service.impl;

import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.EmailVerification;
import org.patientview.patientview.model.User;
import org.patientview.repository.EmailVerificationDao;
import org.patientview.service.EmailVerificationManager;
import org.patientview.service.UserManager;
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
