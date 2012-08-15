package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.symptoms.Crohns;
import com.worthsoln.ibd.model.symptoms.Crohns_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.CrohnsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "crohnsDao")
public class CrohnsDaoImpl extends AbstractHibernateDAO<Crohns> implements CrohnsDao {

    @Override
    public List<Crohns> getAllCrohns(String nhsno, Date fromDate, Date toDate) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Crohns> criteria = builder.createQuery(Crohns.class);
        Root<Crohns> crohnsRoot = criteria.from(Crohns.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(crohnsRoot.get(Crohns_.nhsno), nhsno));

        if (fromDate != null && toDate != null) {
            wherePredicates.add(builder.between(crohnsRoot.get(Crohns_.symptomDate), fromDate, toDate));
        } else if (fromDate != null) {
            wherePredicates.add(builder.greaterThan(crohnsRoot.get(Crohns_.symptomDate), fromDate));
        } else if (toDate != null) {
            wherePredicates.add(builder.greaterThan(crohnsRoot.get(Crohns_.symptomDate), toDate));
        }

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
