package com.worthsoln.specialty;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.utils.LegacySpringUtils;


/**
 *      todo spring bean?
 */
public class SpecialtyUtils {

    public static String rewriteSpecialtyUriRemoveContext(String uri) {

        String result = uri;

        // if there is no Specialty then skip
        if (LegacySpringUtils.getSecurityUserManager().isLoggedInToSpecialty() && uri != null) {

            // if there is a specialty then check for the specialty context and strip off
            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            String specialtyContext = "/" + specialty.getContext();

            if (uri.startsWith(specialtyContext)) {
                // remove and forward
                result = uri.substring(specialtyContext.length());
            }
        }

        return result;
    }

    public static String rewriteSpecialtyUrlAddContext(String uri) {

        if (uri != null) {

            // check for leading double slash
            if (uri.startsWith("//")) {
                uri = uri.substring(1);
            }

            Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();

            String context = specialty != null ? "/" + specialty.getContext() : "";

            return context + uri;
        } else {
            return "";
        }
    }
}
