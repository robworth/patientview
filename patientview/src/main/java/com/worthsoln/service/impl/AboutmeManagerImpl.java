package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AboutmeDao;
import com.worthsoln.service.AboutmeManager;
import com.worthsoln.service.UserManager;

import javax.inject.Inject;

/**
 *
 */
public class AboutmeManagerImpl implements AboutmeManager {

    @Inject
    private AboutmeDao aboutmeDao;

    @Inject
    private UserManager userManager;

    @Override
    public Aboutme getForPatient(User user) {

        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);
        Aboutme aboutme = null;

        if (userMapping != null) {
            String nhsno = userMapping.getNhsno();
            aboutme = aboutmeDao.get(nhsno);
        }

        return aboutme;
    }

    @Override
    public void save(Aboutme aboutme) {
        aboutmeDao.save(aboutme);
    }
}
