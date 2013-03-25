package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.SharedThought;
import com.worthsoln.patientview.model.SharedThought_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SharedThoughtDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "sharedThoughtDao")
public class SharedThoughtDaoImpl extends AbstractHibernateDAO<SharedThought> implements SharedThoughtDao {

    @Override
    public SharedThought get(Long id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SharedThought> criteria = builder.createQuery(SharedThought.class);

        Root<SharedThought> root = criteria.from(SharedThought.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(SharedThought_.id), id));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<SharedThought> getUsersThoughts(Long userId, boolean isSubmitted) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SharedThought> criteria = builder.createQuery(SharedThought.class);

        Root<SharedThought> root = criteria.from(SharedThought.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(SharedThought_.user), userId));
        wherePredicates.add(builder.equal(root.get(SharedThought_.isSubmitted), isSubmitted));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
