package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.patientview.model.JoinRequest_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.JoinRequestDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "joinRequestDao")
public class JoinRequestDaoImpl extends AbstractHibernateDAO<JoinRequest> implements JoinRequestDao {

    @Override
    public List<JoinRequest> getJoinRequestsForUnitCodes(List<String> unitcodes) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<JoinRequest> criteria = builder.createQuery(JoinRequest.class);
        Root<JoinRequest> from = criteria.from(JoinRequest.class);

        Predicate unitCodePredicate = from.get(JoinRequest_.unitcode).in(unitcodes.toArray(new String[unitcodes.size
                ()]));

        criteria.where(unitCodePredicate);

        criteria.orderBy(builder.desc(from.get(JoinRequest_.dateOfRequest)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
