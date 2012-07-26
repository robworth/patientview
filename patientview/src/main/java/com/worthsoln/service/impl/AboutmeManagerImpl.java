package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.AboutmeDao;
import com.worthsoln.service.AboutmeManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 *
 */
@Service(value = "aboutmeManager")
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
