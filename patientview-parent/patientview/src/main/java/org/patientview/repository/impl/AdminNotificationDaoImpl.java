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

package org.patientview.repository.impl;

import org.patientview.ibd.model.AdminNotification;
import org.patientview.ibd.model.AdminNotification_;
import org.patientview.patientview.model.enums.XmlImportNotification;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.AdminNotificationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "adminNotificationDao")
public class AdminNotificationDaoImpl extends AbstractHibernateDAO<AdminNotification> implements AdminNotificationDao {

    @Override
    public List<String> getEmailAddresses(XmlImportNotification xmlImportNotification) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AdminNotification> criteria = builder.createQuery(AdminNotification.class);
        Root<AdminNotification> adminNotificationRoot = criteria.from(AdminNotification.class);

        criteria.where(builder.equal(adminNotificationRoot.get(AdminNotification_.xmlImportNotificationId),
                xmlImportNotification.getId()));

        List<String> emailAddresses = new ArrayList<String>();
        for (AdminNotification adminNotification : getEntityManager().createQuery(criteria).getResultList()) {
            emailAddresses.add(adminNotification.getEmail());
        }

        return emailAddresses;
    }

    @Override
    public String getSupportEmail() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AdminNotification> criteria = builder.createQuery(AdminNotification.class);
        Root<AdminNotification> adminNotificationRoot = criteria.from(AdminNotification.class);

        criteria.where(builder.equal(adminNotificationRoot.get(AdminNotification_.xmlImportNotificationId),
                XmlImportNotification.DEFAULT.getId()));

        try {
            AdminNotification adminNotification = getEntityManager().createQuery(criteria).getSingleResult();

            if (adminNotification != null) {
                return adminNotification.getEmail();
            }
        } catch (NoResultException e) {
            return null;
        }

        return null;
    }
}
