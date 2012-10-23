package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SplashPageDao;
import com.worthsoln.repository.SplashPageUserSeenDao;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.SplashPageManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "splashPageManager")
public class SplashPageManagerImpl implements SplashPageManager {

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private SplashPageDao splashPageDao;

    @Inject
    private SplashPageUserSeenDao splashPageUserSeenDao;


    @Override
    public SplashPage get(Long id) {
        return splashPageDao.get(id);
    }

    @Override
    public void save(SplashPage splashPage) {

        // apply tenancy to splash page if not set
        if (splashPage.getTenancy() == null) {
            splashPage.setTenancy(securityUserManager.getLoggedInTenancy());
        }

        splashPageDao.save(splashPage);
    }

    @Override
    public void save(SplashPageUserSeen splashPageUserSeen) {
        splashPageUserSeenDao.save(splashPageUserSeen);
    }

    @Override
    public void delete(Long id) {
        SplashPage splashPage = get(id);
        if (splashPage != null) {
            splashPageDao.delete(splashPage);
        }
    }

    @Override
    public List<SplashPage> getAll() {

        List<String> unitCodes = null;

        if (userManager.getLoggedInUserRole().equals("unitadmin")) {
            unitCodes = unitManager.getUsersUnitCodes(userManager.getLoggedInUser());
        }

        return splashPageDao.getAll(unitCodes, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<SplashPage> getAllForPatient(User user) {

        List<String> unitCodes = unitManager.getUsersUnitCodes(user);
        unitCodes.add("ALL");

        return splashPageDao.getAll(unitCodes, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<SplashPageUserSeen> getSeenForPatient(User user) {
        return splashPageUserSeenDao.getSeenForPatient(user);
    }

    @Override
    public void removeSeenSplashPage(Long id) {
        splashPageUserSeenDao.delete(id);
    }
}
