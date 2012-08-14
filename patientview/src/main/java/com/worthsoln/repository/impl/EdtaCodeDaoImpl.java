package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.EdtaCode_;
import com.worthsoln.patientview.model.Tenancy;
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
    public EdtaCode getEdtaCode(String edtaCode, Tenancy tenancy) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.edtaCode), edtaCode));
        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.tenancy), tenancy));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(String edtaCode, Tenancy tenancy) {
        EdtaCode edtaCodeToRemove = getEdtaCode(edtaCode, tenancy);

        if (edtaCodeToRemove != null) {
            delete(edtaCodeToRemove);
        }
    }

    @Override
    public List<EdtaCode> get(String linkType, Tenancy tenancy) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.linkType), linkType));
        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.tenancy), tenancy));

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(edtaCodeRoot.get(EdtaCode_.edtaCode)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
