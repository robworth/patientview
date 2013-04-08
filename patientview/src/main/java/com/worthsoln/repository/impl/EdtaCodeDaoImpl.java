package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.EdtaCode_;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.EdtaCodeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "edtaCodeDao")
public class EdtaCodeDaoImpl extends AbstractHibernateDAO<EdtaCode> implements EdtaCodeDao {

    @Override
    public EdtaCode getEdtaCode(String edtaCode, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.edtaCode), edtaCode));
        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(String edtaCode, Specialty specialty) {
        EdtaCode edtaCodeToRemove = getEdtaCode(edtaCode, specialty);

        if (edtaCodeToRemove != null) {
            delete(edtaCodeToRemove);
        }
    }

    @Override
    public List<EdtaCode> get(String linkType, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.linkType), linkType));

        // if not tenancy show all codes for all tenancies (i.e. the user is not logged in
        // this may need changing in future
        if (specialty != null) {
            wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(edtaCodeRoot.get(EdtaCode_.edtaCode)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
