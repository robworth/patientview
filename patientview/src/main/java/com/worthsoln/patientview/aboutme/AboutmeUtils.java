package com.worthsoln.patientview.aboutme;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;

public class AboutmeUtils {

    static Aboutme fetchAboutmeForPatient(User user) throws Exception {
        return LegacySpringUtils.getAboutmeManager().getForPatient(user);
    }
}
