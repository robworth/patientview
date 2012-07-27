package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.EmailVerification;
import com.worthsoln.repository.EmailVerificationDao;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EmailVerificationDaoTest extends BaseDaoTest {

    @Inject
    private EmailVerificationDao emailVerificationDao;

    @Test
    public void testAddGetEmailVerification() throws Exception {
        EmailVerification emailVerification = getTestObject("test", "test@test.com",
                "InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx8g", Calendar.getInstance());

        emailVerificationDao.save(emailVerification);

        assertTrue("Invalid id for new email verification", emailVerification.getId() > 0);

        EmailVerification checkEmailVerification = emailVerificationDao.get(emailVerification.getId());

        assertNotNull(checkEmailVerification);
        assertEquals("Username not stored", emailVerification.getUsername(), checkEmailVerification.getUsername());
        assertEquals("Email not stored", emailVerification.getEmail(), checkEmailVerification.getEmail());
        assertEquals("Verification code not stored", emailVerification.getVerificationcode(),
                checkEmailVerification.getVerificationcode());
        assertEquals("Expiry date stamp not stored", emailVerification.getExpirydatestamp(),
                checkEmailVerification.getExpirydatestamp());
    }

    @Test
    public void testGetEmailVerificationByVerificationCode() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5); // need the expiry to be in the future

        EmailVerification emailVerification1 = getTestObject("test", "test@test.com",
                "InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx81", calendar);
        emailVerificationDao.save(emailVerification1);
        assertTrue("Invalid id for email verification 1", emailVerification1.getId() > 0);

        // create a 2nd just to make sure it doesnt get pulled back
        EmailVerification emailVerification2 = getTestObject("test", "test@test.com",
                "InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx82", Calendar.getInstance());
        emailVerificationDao.save(emailVerification2);
        assertTrue("Invalid id for email verification 2", emailVerification2.getId() > 0);

        List<EmailVerification> checkEmailVerifications =
                emailVerificationDao.get(emailVerification1.getVerificationcode());

        assertNotNull(checkEmailVerifications);
        assertTrue("No email verifications found", !checkEmailVerifications.isEmpty()
                && checkEmailVerifications.size() > 0);
        assertTrue("To many email verifications found", checkEmailVerifications.size() == 1);
    }

    @Test
    public void testNonReturnOfExpiredVerificationCodes() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5); // need the expiry to be in the past

        EmailVerification emailVerification = getTestObject("test", "test@test.com",
                "InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx81", calendar);
        emailVerificationDao.save(emailVerification);
        assertTrue("Invalid id for email verification", emailVerification.getId() > 0);

        List<EmailVerification> checkEmailVerifications =
                emailVerificationDao.get(emailVerification.getVerificationcode());

        assertNotNull(checkEmailVerifications);
        assertTrue("Email verifications found", checkEmailVerifications.isEmpty());
    }

    @Test
    public void testDeleteEmailVerificationByUsername() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);

        EmailVerification emailVerification = getTestObject("test", "test@test.com",
                "InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx81", calendar);
        emailVerificationDao.save(emailVerification);
        assertTrue("Invalid id for email verification", emailVerification.getId() > 0);

        emailVerificationDao.delete(emailVerification.getUsername());

        List<EmailVerification> checkEmailVerifications = emailVerificationDao.get(
                emailVerification.getVerificationcode());

        assertTrue(checkEmailVerifications.isEmpty());
    }

    private EmailVerification getTestObject(String username, String email, String verificationCode,
                                            Calendar expiryDate) {
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setUsername(username);
        emailVerification.setEmail(email);
        emailVerification.setVerificationcode(verificationCode);
        emailVerification.setExpirydatestamp(expiryDate);
        return emailVerification;
    }
}
