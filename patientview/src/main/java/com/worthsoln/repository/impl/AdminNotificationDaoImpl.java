package com.worthsoln.repository.impl;

import com.worthsoln.ibd.model.AdminNotification;
import com.worthsoln.ibd.model.AdminNotification_;
import com.worthsoln.ibd.model.enums.XmlImportNotification;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.AdminNotificationDao;
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
