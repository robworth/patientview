/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.service.impl;

import org.patientview.patientview.model.News;
import org.patientview.patientview.model.User;
import org.patientview.repository.NewsDao;
import org.patientview.service.NewsManager;
import org.patientview.service.SecurityUserManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.patientview.utils.LegacySpringUtils;
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
