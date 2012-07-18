package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SplashPageDao;
import com.worthsoln.service.SplashPageManager;
import com.worthsoln.service.UserManager;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
public class SplashPageManagerImpl implements SplashPageManager {

    @Inject
    private UserManager userManager;

    @Inject
    private SplashPageDao splashPageDao;

    @Override
    public SplashPage get(Long id) {
        return splashPageDao.get(id);
    }

    @Override
    public void save(SplashPage splashPage) {
        splashPageDao.save(splashPage);
    }

    @Override
    public void delete(SplashPage splashPage) {
        splashPageDao.delete(splashPage);
    }

    @Override
    public List<SplashPage> getAll() {
        return splashPageDao.getAll(userManager.getLoggedInUser());
    }

    @Override
    public List<SplashPage> getAllForPatient(User user) {
        return splashPageDao.getAllForPatient(user);
    }

    @Override
    public List<SplashPage> getSeenForPatient(User user) {
        return splashPageDao.getSeenForPatient(user);
    }

    @Override
    public void removeSeenSplashPage(Long id) {
        splashPageDao.removeSeenSplashPage(id);
    }
}
