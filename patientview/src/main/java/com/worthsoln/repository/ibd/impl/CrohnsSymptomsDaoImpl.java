package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.CrohnsSymptomsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "crohnsSymptomsDao")
public class CrohnsSymptomsDaoImpl extends AbstractHibernateDAO<CrohnsSymptoms> implements CrohnsSymptomsDao {

    @Override
    public void save(CrohnsSymptoms crohnsSymptoms) {
        crohnsSymptoms.setScore(crohnsSymptoms.calculateScore());
        super.save(crohnsSymptoms);
    }

    @Override
    public List<CrohnsSymptoms> getAllCrohns(String nhsno, Date fromDate, Date toDate) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CrohnsSymptoms> criteria = builder.createQuery(CrohnsSymptoms.class);
        Root<CrohnsSymptoms> crohnsRoot = criteria.from(CrohnsSymptoms.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(crohnsRoot.get(CrohnsSymptoms_.nhsno), nhsno));

        if (fromDate != null && toDate != null) {
            wherePredicates.add(builder.between(crohnsRoot.get(CrohnsSymptoms_.symptomDate), fromDate, toDate));
        } else if (fromDate != null) {
            wherePredicates.add(builder.greaterThan(crohnsRoot.get(CrohnsSymptoms_.symptomDate), fromDate));
        } else if (toDate != null) {
            wherePredicates.add(builder.greaterThan(crohnsRoot.get(CrohnsSymptoms_.symptomDate), toDate));
        }

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(crohnsRoot.get(CrohnsSymptoms_.symptomDate)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
