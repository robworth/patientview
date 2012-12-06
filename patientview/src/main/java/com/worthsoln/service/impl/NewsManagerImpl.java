package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.News;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.NewsDao;
import com.worthsoln.service.NewsManager;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.utils.LegacySpringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service(value = "newsManager")
public class NewsManagerImpl implements NewsManager {

    @Inject
    private NewsDao newsDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Override
    public News get(Long id) {
        return newsDao.get(id);
    }

    @Override
    public void save(News news) {

        // apply the Specialty to the news if not set
        if (news.getSpecialty() == null) {
            news.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        newsDao.save(news);
    }

    @Override
    public void delete(Long id) {
        News news = get(id);
        if (news != null) {
            newsDao.delete(get(id));
        }
    }

    @Override
    public List<News> getNewsForViewing() {

        List<News> news = new ArrayList<News>();
        User user  = userManager.getLoggedInUser();

        if (user == null) {

            news = newsDao.getNewsForEveryone(securityUserManager.getLoggedInSpecialty());

        } else {

            String userType = LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(user);

            if ("superadmin".equals(userType)) {

                news = newsDao.getAll(securityUserManager.getLoggedInSpecialty());

            } else if ("unitadmin".equals(userType) || "unitstaff".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes(user);
                news = newsDao.getAdminNewsForUnitCodes(unitCodes, securityUserManager.getLoggedInSpecialty());

            } else if ("patient".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes(user);
                news = newsDao.getPatientNewsForUnitCodes(unitCodes, securityUserManager.getLoggedInSpecialty());
            }
        }

        return news;
    }

    @Override
    public List<News> getNewsForEditing() {

        List<News> news = new ArrayList<News>();
        User user  = userManager.getLoggedInUser();

        if (user == null) {

            news = newsDao.getNewsForEveryone(securityUserManager.getLoggedInSpecialty());

        } else {

            String userType = LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(user);

            if ("superadmin".equals(userType)) {

                news = newsDao.getAll(securityUserManager.getLoggedInSpecialty());

            } else if ("unitadmin".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes(user);
                news = newsDao.getAdminEditNewsForUnitCodes(unitCodes, securityUserManager.getLoggedInSpecialty());
            }
        }

        return news;
    }
}
