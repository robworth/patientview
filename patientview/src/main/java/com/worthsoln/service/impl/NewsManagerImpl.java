package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.News;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.NewsDao;
import com.worthsoln.service.NewsManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
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
    private UnitManager unitManager;

    @Inject
    private UserManager userManager;

    @Override
    public News get(Long id) {
        return newsDao.get(id);
    }

    @Override
    public void save(News news) {
        newsDao.save(news);
    }

    @Override
    public void delete(News news) {
        newsDao.delete(news);
    }

    @Override
    public List<News> getNewsForViewing() {

        List<News> news = new ArrayList<News>();
        User user  = userManager.getLoggedInUser();

        if (user == null) {

            news = newsDao.getNewsForEveryone();

        } else {

            String userType = user.getRole();

            if ("superadmin".equals(userType)) {

                news = newsDao.getAll();

            } else if ("unitadmin".equals(userType) || "unitstaff".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes();
                news = newsDao.getAdminNewsForUnitCodes(unitCodes);

            } else if ("patient".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes();
                news = newsDao.getPatientNewsForUnitCodes(unitCodes);
            }
        }

        return news;
    }

    @Override
    public List<News> getNewsForEditing() {

        List<News> news = new ArrayList<News>();
        User user  = userManager.getLoggedInUser();

        if (user == null) {

            news = newsDao.getNewsForEveryone();

        } else {

            String userType = user.getRole();

            if ("superadmin".equals(userType)) {

                news = newsDao.getAll();

            } else if ("unitadmin".equals(userType)) {

                List<String> unitCodes = unitManager.getUsersUnitCodes();
                news = newsDao.getAdminEditNewsForUnitCodes(unitCodes);
            }
        }

        return news;
    }
}
