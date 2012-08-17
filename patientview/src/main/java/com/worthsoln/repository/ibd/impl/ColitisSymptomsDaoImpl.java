package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.symptoms.ColitisSymptoms;
import com.worthsoln.ibd.model.symptoms.ColitisSymptoms_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.ColitisSymptomsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "colitisDao")
public class ColitisSymptomsDaoImpl extends AbstractHibernateDAO<ColitisSymptoms> implements ColitisSymptomsDao {

    @Override
    public void save(ColitisSymptoms colitisSymptoms) {
        colitisSymptoms.setScore(colitisSymptoms.calculateScore());
        super.save(colitisSymptoms);
    }

    @Override
    public List<ColitisSymptoms> getAllColitis(String nhsno, Date fromDate, Date toDate) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ColitisSymptoms> criteria = builder.createQuery(ColitisSymptoms.class);
        Root<ColitisSymptoms> colitisRoot = criteria.from(ColitisSymptoms.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(colitisRoot.get(ColitisSymptoms_.nhsno), nhsno));

        if (fromDate != null && toDate != null) {
            wherePredicates.add(builder.between(colitisRoot.get(ColitisSymptoms_.symptomDate), fromDate, toDate));
        } else if (fromDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(ColitisSymptoms_.symptomDate), fromDate));
        } else if (toDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(ColitisSymptoms_.symptomDate), toDate));
        }

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(colitisRoot.get(ColitisSymptoms_.symptomDate)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
