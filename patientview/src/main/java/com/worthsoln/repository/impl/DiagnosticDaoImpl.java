package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.Diagnostic_;
import com.worthsoln.patientview.model.enums.DiagnosticType;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.DiagnosticDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Repository(value = "diagnosticDao")
public class DiagnosticDaoImpl extends AbstractHibernateDAO<Diagnostic> implements DiagnosticDao {

    @Override
    public Diagnostic get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnostic> criteria = builder.createQuery(Diagnostic.class);
        Root<Diagnostic> diagnosticRoot = criteria.from(Diagnostic.class);

        criteria.where(builder.equal(diagnosticRoot.get(Diagnostic_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            return null;
        }
    }

    @Override
    public List<Diagnostic> get(Set<String> nhsNos, DiagnosticType diagnosticType) {

        // nhsNos and diagnosticType are required params
        if (nhsNos == null || nhsNos.size() == 0 || diagnosticType == null) {
            return new ArrayList<Diagnostic>();
        }

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnostic> criteria = builder.createQuery(Diagnostic.class);
        Root<Diagnostic> from = criteria.from(Diagnostic.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(from.get(Diagnostic_.nhsno).in(nhsNos));
        wherePredicates.add(builder.equal(from.get(Diagnostic_.diagnosticTypeId), diagnosticType.getId()));

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(from.get(Diagnostic_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
