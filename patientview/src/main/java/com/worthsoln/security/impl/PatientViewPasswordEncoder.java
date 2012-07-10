package com.worthsoln.security.impl;

import com.worthsoln.patientview.logon.LogonUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *      Allow spring security to encode and match password to the patient view standard
 */
public class PatientViewPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return LogonUtils.hashPassword(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
