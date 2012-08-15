package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.symptoms.Colitis;
import com.worthsoln.ibd.model.symptoms.Colitis_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.ColitisDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "colitisDao")
public class ColitisDaoImpl extends AbstractHibernateDAO<Colitis> implements ColitisDao {

    @Override
    public List<Colitis> getAllColitis(String nhsno, Date fromDate, Date toDate) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Colitis> criteria = builder.createQuery(Colitis.class);
        Root<Colitis> colitisRoot = criteria.from(Colitis.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(colitisRoot.get(Colitis_.nhsno), nhsno));

        if (fromDate != null && toDate != null) {
            wherePredicates.add(builder.between(colitisRoot.get(Colitis_.symptomDate), fromDate, toDate));
        } else if (fromDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(Colitis_.symptomDate), fromDate));
        } else if (toDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(Colitis_.symptomDate), toDate));
        }

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
