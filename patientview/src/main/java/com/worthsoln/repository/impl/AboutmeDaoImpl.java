package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.patientview.model.Aboutme_;
import com.worthsoln.repository.AboutmeDao;
import com.worthsoln.repository.AbstractHibernateDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "aboutmeDao")
public class AboutmeDaoImpl extends AbstractHibernateDAO<Aboutme> implements AboutmeDao {

    @Override
    public Aboutme get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Aboutme> criteria = builder.createQuery(Aboutme.class);
        Root<Aboutme> aboutmeRoot = criteria.from(Aboutme.class);

        criteria.where(builder.equal(aboutmeRoot.get(Aboutme_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
